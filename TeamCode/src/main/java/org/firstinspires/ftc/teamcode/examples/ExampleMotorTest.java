package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * 모터를 하나씩 돌려 보는 테스트 OpMode.
 * 반드시 로봇을 거치대에 올려 바퀴가 바닥에 닿지 않게 하고 실행하세요.
 * 슈터 롤러 근처에 손을 두지 마세요. Pollen은 빼 두세요.
 *
 *   X = LeftFront  (Control Hub)      Y = RightFront (Expansion Hub)
 *   A = LeftBack   (Control Hub)      B = RightBack  (Expansion Hub)
 *   D-pad 위 = PollenLoader    D-pad 아래 = PollenShooter
 *
 * 버튼을 누르는 동안 그 모터만 0.3 파워로 돕니다.
 * - 모든 바퀴가 "앞으로" 돌고, 엔코더 숫자가 + 로 늘어나면 정상입니다.
 * - 다른 위치의 바퀴가 돌면 → Configuration 이름 확인
 * - 뒤로 돌면 → 아래 setDirection 을 반대로 (TeleOp 파일도 똑같이)
 * - 오른쪽 바퀴 · 로더 · 슈터가 모두 안 돌면 → Expansion Hub 연결 케이블 / 전원 확인
 */
@TeleOp(name = "Example Motor Test", group = "Examples")
public class ExampleMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor frontLeftMotor  = hardwareMap.get(DcMotor.class, "LeftFront");
        DcMotor backLeftMotor   = hardwareMap.get(DcMotor.class, "LeftBack");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "RightFront");
        DcMotor backRightMotor  = hardwareMap.get(DcMotor.class, "RightBack");
        DcMotor loaderMotor     = hardwareMap.get(DcMotor.class, "PollenLoader");
        DcMotor shooterMotor    = hardwareMap.get(DcMotor.class, "PollenShooter");

        // ExampleMecanumTeleOp / 대회 코드와 같은 방향 설정
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        loaderMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.REVERSE);

        for (DcMotor m : new DcMotor[]{frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor,
                                       loaderMotor, shooterMotor}) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        telemetry.addLine("로봇을 거치대에 올리세요! 슈터에 손 대지 않기");
        telemetry.addLine("X=LeftFront  Y=RightFront  A=LeftBack  B=RightBack");
        telemetry.addLine("D-pad 위=Loader  D-pad 아래=Shooter");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double p = 0.3;
            frontLeftMotor.setPower(gamepad1.x ? p : 0);
            frontRightMotor.setPower(gamepad1.y ? p : 0);
            backLeftMotor.setPower(gamepad1.a ? p : 0);
            backRightMotor.setPower(gamepad1.b ? p : 0);
            loaderMotor.setPower(gamepad1.dpad_up ? p : 0);
            shooterMotor.setPower(gamepad1.dpad_down ? p : 0);

            telemetry.addLine("앞으로 돌면 엔코더 숫자가 + 로 늘어나야 정상 (바퀴 1회전 약 384.5)");
            telemetry.addData("LeftFront  (X)", frontLeftMotor.getCurrentPosition());
            telemetry.addData("RightFront (Y)", frontRightMotor.getCurrentPosition());
            telemetry.addData("LeftBack   (A)", backLeftMotor.getCurrentPosition());
            telemetry.addData("RightBack  (B)", backRightMotor.getCurrentPosition());
            telemetry.addData("Loader  (D-pad 위)", loaderMotor.getCurrentPosition());
            telemetry.addData("Shooter (D-pad 아래)", shooterMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
