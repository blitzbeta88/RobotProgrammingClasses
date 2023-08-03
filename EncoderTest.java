package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "EncoderTest")
public class EncoderTest extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, person using this program!");
        telemetry.update();

        waitForStart();

        int position = 0;
        boolean pressinga = false;
        boolean pressingb = false;


        while (opModeIsActive()) {

            position = robot.demoMotor.getCurrentPosition();
            robot.demoMotor.setPower(1);
            robot.demoMotor.setTargetPosition(position);
            robot.demoMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (gamepad1.a && !pressinga) {
                position += 10;
                pressinga = true;
            } else if (!gamepad1.a) {
                pressinga = false;
            }

            if (gamepad1.b && !pressingb) {
                position -= 10;
                pressingb = true;
            } else if (!gamepad1.b) {
                pressingb = false;
            }

            telemetry.addData("Position", position);
            telemetry.addData("ActualMotorPosition", robot.demoMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
