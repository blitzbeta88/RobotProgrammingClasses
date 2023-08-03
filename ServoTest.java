package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp (name = "ServoTest")
public class ServoTest extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello!");
        telemetry.update();

        waitForStart();

        int position = 0;
        boolean pressinga = false;
        boolean pressingb = false;

        while (opModeIsActive()) {

            robot.demoServo.setPosition(position);

            if (gamepad1.a && !pressinga) {
                position += 0.05;
                pressinga = true;
            } else if (!gamepad1.a) {
                pressinga = false;
            }

            if (gamepad1.b && !pressingb) {
                position -= 0.05;
                pressingb = true;
            } else if (!gamepad1.b) {
                pressingb = false;
            }

            telemetry.addData("Position", position);
            telemetry.addData("ActualServoPosition", robot.demoServo.getPosition());
            telemetry.update();
        }

    }
}
