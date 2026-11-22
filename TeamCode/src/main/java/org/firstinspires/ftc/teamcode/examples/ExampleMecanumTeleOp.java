package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SharedConfig;

/**
 * 강의 Part 4 예제: 메카넘 (로봇 중심) + POLLEN 런처 (로더 · 슈터).
 *
 * 하드웨어 이름, 변수 이름, 조작은 대회 코드(HOYA_2026의 HoyaFieldCentricDrive / PollenLauncher)와 같습니다.
 * 대회 코드는 여기에 필드 중심 주행(Pinpoint), 슬루 레이트, 횡이동 보정이 더해진 버전입니다.
 *
 * 조작 (Gamepad 1 하나로)
 *   왼쪽 스틱        : 앞뒤 / 좌우 평행이동
 *   오른쪽 스틱 X    : 회전
 *   RT (누른 만큼)   : 정밀 감속 (최대 50%)
 *   LT (누른 만큼)   : 로더 정방향 (수거 / 이송, 최대 0.8)
 *   D-pad 아래 (홀드): 로더 역방향 -0.8 (자동 발사 취소, LT보다 우선)
 *   A                : 슈터 정지
 *   B / X / Y        : 슈터 프리셋 (롤러 최대 3240rpm 대비 90 / 90 / 85 %), 켜진 버튼 다시 누르면 정지
 *   RB (홀드)        : 자동 발사 — 슈터 READY → 로더 짧게 급송 → 쉬고 → 반복
 *
 * Configuration
 *   Control Hub   (좌) : LeftFront, LeftBack
 *   Expansion Hub (우) : RightFront, RightBack, PollenLoader (포트 2), PollenShooter (포트 3)
 */
@TeleOp(name = "Example Mecanum TeleOp", group = "Examples")
public class ExampleMecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        // ---------- ① 초기화 (INIT) ----------
        DcMotor frontLeftMotor  = hardwareMap.get(DcMotor.class, "LeftFront");
        DcMotor backLeftMotor   = hardwareMap.get(DcMotor.class, "LeftBack");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "RightFront");
        DcMotor backRightMotor  = hardwareMap.get(DcMotor.class, "RightBack");
        DcMotor   loaderMotor   = hardwareMap.get(DcMotor.class, "PollenLoader");
        DcMotorEx shooterMotor  = hardwareMap.get(DcMotorEx.class, "PollenShooter"); // Ex: 속도 제어 가능

        // 대회 코드와 같은 방향 설정
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        loaderMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.REVERSE);

        // 조이스틱을 놓으면 바로 멈추도록
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        loaderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        loaderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // 슈터: 엔코더로 속도 제어(setVelocity), 끌 때는 서서히 멈춤(FLOAT)
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int activePreset = -1;          // 지금 슈터 프리셋 (-1 = 정지)
        double targetRpm = 0.0;         // 슈터 롤러 목표 RPM
        boolean rpmInBand = false;      // 목표 RPM 범위 안인지
        ElapsedTime stableTimer = new ElapsedTime();

        // 자동 발사 상태: 0 = 대기, 1 = 급송 중, 2 = 회복(쉬는) 중
        int fireStep = 0;
        ElapsedTime fireTimer = new ElapsedTime();

        // 엣지 검출용 이전 버튼 값 (A, B, X, Y). 값을 기억해야 하므로 루프 밖
        boolean[] prevPreset = new boolean[4];

        telemetry.addData("Status", "Ready");
        telemetry.update();

        // ---------- ② PLAY 기다리기 ----------
        waitForStart();

        // ---------- ③ 반복 ----------
        while (opModeIsActive()) {
            // ===== 드라이브 (로봇 중심 메카넘) =====
            double axial   = shape(-gamepad1.left_stick_y);  // 앞뒤 (위로 밀면 y가 음수)
            double lateral = shape( gamepad1.left_stick_x);  // 좌우
            double yaw     = shape( gamepad1.right_stick_x); // 회전

            double fl = axial + lateral + yaw;
            double fr = axial - lateral - yaw;
            double bl = axial - lateral + yaw;
            double br = axial + lateral - yaw;

            // 1.0을 넘는 값이 있으면 가장 큰 값으로 나눠 비율을 유지한다.
            double max = Math.max(1.0, Math.max(Math.max(Math.abs(fl), Math.abs(fr)),
                                                Math.max(Math.abs(bl), Math.abs(br))));

            // RT를 누른 만큼 감속 (끝까지 누르면 50%)
            double speed = SharedConfig.SPEED
                    * (1.0 - SharedConfig.PRECISION_MAX_REDUCTION * gamepad1.right_trigger);

            frontLeftMotor.setPower(fl / max * speed);
            frontRightMotor.setPower(fr / max * speed);
            backLeftMotor.setPower(bl / max * speed);
            backRightMotor.setPower(br / max * speed);

            // ===== 슈터: A = 정지, B/X/Y = 프리셋 (같은 버튼 다시 누르면 정지) =====
            boolean[] presetButtons = {gamepad1.a, gamepad1.b, gamepad1.x, gamepad1.y};
            for (int i = 0; i < 4; i++) {
                if (presetButtons[i] && !prevPreset[i]) {
                    if (i == 0 || activePreset == i) {
                        activePreset = -1;
                        targetRpm = 0.0;
                    } else {
                        activePreset = i;
                        targetRpm = SharedConfig.SHOOTER_PRESETS[i] * SharedConfig.SHOOTER_MAX_RPM;
                    }
                    rpmInBand = false;
                }
                prevPreset[i] = presetButtons[i];
            }

            if (targetRpm > 0) {
                // 목표 RPM → 초당 엔코더 틱으로 바꿔서 속도 제어
                shooterMotor.setVelocity(targetRpm * SharedConfig.SHOOTER_TICKS_PER_REV / 60.0);
            } else {
                shooterMotor.setPower(0.0); // FLOAT라서 서서히 멈춤
            }

            // 실제 롤러 RPM = 초당 틱 × 60 ÷ 롤러 1회전 틱
            double rollerRpm = Math.abs(shooterMotor.getVelocity()) * 60.0
                               / SharedConfig.SHOOTER_TICKS_PER_REV;

            // READY: 목표 ± 120rpm 안에 0.15초 이상 머무름
            boolean inTolerance = targetRpm > 0
                    && Math.abs(rollerRpm - targetRpm) <= SharedConfig.SHOOTER_READY_TOLERANCE_RPM;
            if (!inTolerance) {
                rpmInBand = false;
            } else if (!rpmInBand) {
                rpmInBand = true;
                stableTimer.reset();
            }
            boolean shooterReady = rpmInBand
                    && stableTimer.seconds() >= SharedConfig.SHOOTER_READY_STABLE_SEC;

            // ===== 로더: D-pad 아래 > RB 자동 발사 > LT 수동 =====
            double loaderPower = 0.0;
            if (gamepad1.dpad_down) {
                fireStep = 0;                                    // 자동 발사 즉시 취소
                loaderPower = -SharedConfig.LOADER_MAX_POWER;
            } else if (gamepad1.right_bumper && targetRpm > 0) {
                // RB 홀드 = READY 기다림 → 짧게 급송 → 쉬고 → 다시
                if (fireStep == 0 && shooterReady) {
                    fireStep = 1;
                    fireTimer.reset();
                } else if (fireStep == 1 && fireTimer.seconds() >= SharedConfig.FEED_PULSE_SEC) {
                    fireStep = 2;
                    fireTimer.reset();
                } else if (fireStep == 2 && fireTimer.seconds() >= SharedConfig.SHOT_SETTLE_SEC) {
                    fireStep = 0;
                }
                loaderPower = (fireStep == 1) ? SharedConfig.FEED_POWER : 0.0;
            } else {
                fireStep = 0;
                if (!gamepad1.right_bumper && gamepad1.left_trigger > SharedConfig.LOADER_TRIGGER_THRESHOLD) {
                    loaderPower = Math.min(gamepad1.left_trigger, SharedConfig.LOADER_MAX_POWER);
                }
            }
            loaderMotor.setPower(loaderPower);

            telemetry.addData("Speed", "%.2f", speed);
            telemetry.addData("Front L/R", "%.2f / %.2f", fl / max * speed, fr / max * speed);
            telemetry.addData("Back  L/R", "%.2f / %.2f", bl / max * speed, br / max * speed);
            telemetry.addData("Shooter", "target %.0f rpm | actual %.0f rpm | %s",
                    targetRpm, rollerRpm, shooterReady ? "READY" : "NOT READY");
            telemetry.addData("Fire step", fireStep == 0 ? "WAIT" : fireStep == 1 ? "FEEDING" : "RECOVERING");
            telemetry.addData("Loader", "%.2f", loaderPower);
            telemetry.addLine("[A] Off | [B][X][Y] Presets | [LT] Load | [Dpad Down] Reverse | [RB] Auto Fire");
            telemetry.update();
        }

        // 끝나면 런처 정지
        loaderMotor.setPower(0);
        shooterMotor.setPower(0);
    }

    /** 데드존 + 큐빅 입력 곡선 (대회 코드와 같은 식): 0.75 x³ + 0.25 x */
    private double shape(double v) {
        if (Math.abs(v) < SharedConfig.DEADBAND) return 0.0;
        return 0.75 * v * v * v + 0.25 * v;
    }
}
