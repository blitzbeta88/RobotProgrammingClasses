package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp (name = "Meet 1 TeleOp")
public class DemoTeleOp extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        waitForStart();

        boolean pressingb = false;
        boolean pressingx = false;
        boolean difference = false;
        boolean pressinglt = false;

        while (opModeIsActive()){
            double movement;
            double strafing;
            double turning;
            movement = -gamepad1.left_stick_y;
            strafing = gamepad1.left_stick_x;
            turning = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(movement - strafing - turning),
                    Math.max(Math.abs(movement + strafing - turning),
                            Math.max(Math.abs(movement + strafing+turning),
                                    Math.abs(movement - strafing + turning))));
            if (max < robot.maxSpeed) {
                robot.setPower(movement - strafing - turning,
                        movement + strafing - turning,
                        movement + strafing + turning,
                        movement - strafing + turning);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower((movement - strafing - turning) * scaleFactor,
                        (movement + strafing - turning) * scaleFactor,
                        (movement + strafing + turning) * scaleFactor,
                        (movement - strafing + turning) * scaleFactor;
            }

            if (gamepad2.a) {
                //Whatever you want A to do when pressed and held
                robot.demoWheel1.setPower(1);
            }
            if (gamepad2.b && !pressingb) {
                //Whatever you want to start when B is pressed
                pressingb = true;
            } else if (!gamepad2.b) {
                pressingb = false;
            }

            if (gamepad2.x && !pressingx && (difference == true)) {
                //claw opens
                difference = false;
                pressingx = true;
            } else if (gamepad2.x && !pressingx && (difference == false)) {
                //claw closes
                difference = true;
                pressingx = true;
            } else if (!gamepad2.x) {
                pressingx = false;
            }

            if (gamepad2.x && pressingx == false) {
                if (!difference) {
                    //claw opens
                    difference = true;
                } else {
                    //claw closes
                    difference = false;
                }
                pressingx = true;
            } else if (!gamepad2.x) {
                pressingx = false;
            }

            if ((gamepad2.left_trigger > 0.1) && !pressinglt) {
                pressinglt = true;
            } else if (!(gamepad2.left_trigger > 0.1)) {
                pressinglt = false;
            }



        }

    }

}
