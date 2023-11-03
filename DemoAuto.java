package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Meet 1 Auto")
public class DemoAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, drivers!");
        telemetry.update();

        waitForStart();

        move(112, 0.7);

        runtime.reset();
        robot.demoWheel1.setPower(1);
        while (runtime.seconds() < 5) {

        }
        robot.demoWheel1.setPower(0);

    }

    //EncoderBased
    public void move(double distanceMoving, double speedMoving) {

        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor/wheelCircumference));

        robot.setPower(0,0,0,0);
        robot.demoWheel1.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel2.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel3.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel4.setTargetPosition((int) Math.round(ticks));

        robot.demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speedMoving, speedMoving, speedMoving, speedMoving);

        while (opModeIsActive() && (robot.demoWheel3.getCurrentPosition() + 10 < ticks || robot.demoWheel3.getCurrentPosition() - 10 > ticks)) {

        }

        robot.setPower(0,0,0,0);

        robot.demoWheel1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.demoWheel2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.demoWheel3.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.demoWheel4.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
    }

    public void turn(double degrees) {

        double currentAngle = robot.gyro.getAngularOrientation().firstAngle;
        double finalAngle = currentAngle + degrees;
        if (finalAngle > 180) {
            finalAngle -= 360;
        } else if (finalAngle < -180) {
            finalAngle += 360;
        }

        if (degrees >= 0) {
            double errorOfDegree = degrees;
            while (Math.abs(errorOfDegree) > 1) {
                robot.setPower(-0.0055 * errorOfDegree,-0.0055 * errorOfDegree,0.0055 * errorOfDegree,0.0055 * errorOfDegree);
                errorOfDegree = finalAngle - robot.gyro.getAngularOrientation().firstAngle;
                if (errorOfDegree > 180) {
                    errorOfDegree -= 360;
                } else if (errorOfDegree < -180) {
                    errorOfDegree += 360;
                }
                errorOfDegree = Math.abs(errorOfDegree);
            }
        } else {
            double errorOfDegree = degrees;
            while (Math.abs(errorOfDegree) > 1) {
                robot.setPower(0.0055 * errorOfDegree, 0.0055 * errorOfDegree, -0.0055 * errorOfDegree, -0.0055 * errorOfDegree);
                errorOfDegree = finalAngle - robot.gyro.getAngularOrientation().firstAngle;
                if (errorOfDegree > 180) {
                    errorOfDegree -= 360;
                } else if (errorOfDegree < -180) {
                    errorOfDegree += 360;
                }
                errorOfDegree = Math.abs(errorOfDegree);
            }
        }
        robot.setPower(0,0,0,0);
        sleep(500
        );


    }




}
