package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SharedConfig;

/**
 * 강의 Part 4 예제: 4륜 메카넘 TeleOp (goBILDA 435rpm × 4) + 집게 서보.
 *
 * 조작
 *   왼쪽 스틱      : 앞뒤 / 좌우 평행이동
 *   오른쪽 스틱 X  : 회전
 *   오른쪽 범퍼    : 누르고 있는 동안 느린 모드
 *   A 버튼         : 집게 열기 / 닫기 (누를 때마다 전환)
 *
 * Configuration 이름: frontLeft, backLeft, frontRight, backRight, claw
 */
@TeleOp(name = "Example Mecanum TeleOp", group = "Examples")
public class ExampleMecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        // ---------- ① 초기화 (INIT) ----------
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor backLeft   = hardwareMap.get(DcMotor.class, "backLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight  = hardwareMap.get(DcMotor.class, "backRight");
        Servo claw = hardwareMap.get(Servo.class, "claw");

        // 왼쪽과 오른쪽 모터는 거울처럼 반대로 달려 있으므로 한쪽을 뒤집는다.
        // 로봇이 이상하게 움직이면 바퀴를 하나씩 돌려 보고 여기를 고칠 것.
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        // 조이스틱을 놓으면 바로 멈추도록
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        boolean clawOpen = false;
        boolean prevA = false;

        telemetry.addData("Status", "Ready");
        telemetry.update();

        // ---------- ② PLAY 기다리기 ----------
        waitForStart();

        // ---------- ③ 반복 ----------
        while (opModeIsActive()) {
            // 스틱을 위로 밀면 y가 음수이므로 부호를 뒤집는다.
            double axial   = -gamepad1.left_stick_y;  // 앞뒤
            double lateral =  gamepad1.left_stick_x;  // 좌우
            double yaw     =  gamepad1.right_stick_x; // 회전

            double fl = axial + lateral + yaw;
            double fr = axial - lateral - yaw;
            double bl = axial - lateral + yaw;
            double br = axial + lateral - yaw;

            // 1.0을 넘는 값이 있으면 가장 큰 값으로 나눠 비율을 유지한다.
            double max = Math.max(1.0, Math.max(Math.max(Math.abs(fl), Math.abs(fr)),
                                                Math.max(Math.abs(bl), Math.abs(br))));

            double speed = SharedConfig.SPEED;
            if (gamepad1.right_bumper) {
                speed = speed * SharedConfig.SLOW_MODE_SCALE;
            }

            frontLeft.setPower(fl / max * speed);
            frontRight.setPower(fr / max * speed);
            backLeft.setPower(bl / max * speed);
            backRight.setPower(br / max * speed);

            // A 버튼: 누른 순간에만 집게 상태를 뒤집는다 (엣지 검출).
            if (gamepad1.a && !prevA) {
                clawOpen = !clawOpen;
            }
            prevA = gamepad1.a;
            claw.setPosition(clawOpen ? SharedConfig.CLAW_OPEN : SharedConfig.CLAW_CLOSED);

            telemetry.addData("Speed", "%.2f%s", speed, gamepad1.right_bumper ? " (slow)" : "");
            telemetry.addData("Front L/R", "%.2f / %.2f", fl / max * speed, fr / max * speed);
            telemetry.addData("Back  L/R", "%.2f / %.2f", bl / max * speed, br / max * speed);
            telemetry.addData("Claw", clawOpen ? "OPEN" : "CLOSED");
            telemetry.update();
        }
    }
}
