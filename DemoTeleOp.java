package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvCamera;

@TeleOp (name = "TeleOp")
public class DemoTeleOp extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Hello, drivers!");
        telemetry.update();

        if (robot.demoWheel1 != null) {
            robot.demoWheel1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (robot.demoWheel2 != null) {
            robot.demoWheel2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (robot.demoWheel3 != null) {
            robot.demoWheel3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (robot.demoWheel4 != null) {
            robot.demoWheel4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        boolean pressingb = false;
        boolean tryOne = false;
        boolean pressingrt = false;

        waitForStart();

        while (opModeIsActive()) {

            double forward;
            double strafing;
            double turning;

            forward = gamepad1.right_stick_x;
            strafing = gamepad1.left_stick_y;
            turning = -gamepad1.left_stick_x;

            double max = Math.max(Math.abs(forward - strafing - turning), Math.max(Math.abs(forward + strafing - turning), Math.max(Math.abs(forward + strafing + turning), Math.abs(forward + turning - strafing))));
            if (max < robot.maxSpeed) {
                robot.setPower(forward - strafing - turning, forward + strafing - turning, forward + strafing + turning, forward + turning - strafing);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower((forward - strafing - turning) * scaleFactor, (forward + strafing - turning) * scaleFactor, (forward + strafing + turning) * scaleFactor, (forward + turning - strafing) * scaleFactor);
            }

            //To start gamepad1 press Start and A at the same time
            //To start gamepad2 press Start and B at the same time

            //Hold button for action to happen
            if (gamepad2.a) {
                //Intake turns on
            }

            //Press one button for multiple results
            if (gamepad2.b && !pressingb && !tryOne) {
                //Claw opens
                tryOne = true;
                pressingb = true;
            } else if (gamepad2.b && !pressingb && tryOne) {
                //Claw closes
                tryOne = false;
                pressingb = true;
            } else if (!gamepad2.b) {
                pressingb = false;
            }


            if ((gamepad2.right_trigger > 0.1) && !pressingrt) {
                if (!tryOne) {
                    //Claw opens
                    pressingrt = true;
                } else {
                    //Claw closes
                    pressingrt = false;
                }
                pressingrt = true;
            } else if (!(gamepad2.right_trigger > 0.1)) {
                pressingrt = false;
            }





        }


    }



}
