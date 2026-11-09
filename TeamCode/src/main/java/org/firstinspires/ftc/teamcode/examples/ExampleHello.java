package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * 강의 Part 3 예제: 게임패드 값을 Driver Station 화면에 띄웁니다.
 *
 * 실습할 때는 이 파일을 복사해서 teamcode 폴더에 "내이름Hello.java"로 만들고,
 * 클래스 이름과 @TeleOp의 name도 바꿔 주세요.
 */
@TeleOp(name = "Example Hello", group = "Examples")
public class ExampleHello extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Left X", gamepad1.left_stick_x);
            telemetry.addData("Left Y", gamepad1.left_stick_y);
            telemetry.addData("Right X", gamepad1.right_stick_x);
            telemetry.addData("A", gamepad1.a);
            telemetry.addData("Right Bumper", gamepad1.right_bumper);
            telemetry.update();
        }
    }
}
