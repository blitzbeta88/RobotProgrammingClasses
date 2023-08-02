package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous (name = "DemoAuto")
public class DemoAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    OpenCvCamera webCam;
    private String position = "Level 1";
    private Pipeline detector;

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, drivers!");
        telemetry.update();

        int cameraMonitorViewID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorVideID", "id",hardwareMap.appContext.getPackageName());
        detector = new Pipeline();
        webCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "demoCam"), cameraMonitorViewID);
        webCam.openCameraDevice();
        FtcDashboard.getInstance().startCameraStream(webCam, 0);
        webCam.startStreaming(640,480, OpenCvCameraRotation.UPSIDE_DOWN);
        webCam.setPipeline(detector);

        while (!isStarted() && !isStopRequested()) {

            position = detector.position;
            telemetry.addData("Positon", position);
            telemetry.addData("totalA", detector.totalA);
            telemetry.update();

            dashboardTelemetry.addData("position", position);
            dashboardTelemetry.addData("totalA", detector.totalA);
            dashboardTelemetry.update();

        }


        waitForStart();

        move(62,0.5);

        runtime.reset();
        while (runtime.seconds() < 5) {
            robot.setPower(1,1,1,1);
        }
        robot.setPower(0,0,0,0);


    }

    public void move(double distanceMoving, double speedMoving) {

        double wheelCircumfrance = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumfrance));

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

        robot.setPower(speedMoving,speedMoving,speedMoving,speedMoving);

        while (opModeIsActive() && robot.demoWheel1.getCurrentPosition() + 10 < ticks || robot.demoWheel1.getCurrentPosition() - 10 > ticks) {

        }

        robot.setPower(0,0,0,0);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void turning (double degrees) {

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentAngle = robot.gyro.getAngularOrientation().firstAngle;
        double finalAngle = currentAngle + degrees;

        if (finalAngle > 180) {
            finalAngle -= 360;
        } else if (finalAngle < -180) {
            finalAngle += 360;
        }

        if (degrees >= 0) {
            double errorOfDegree = degrees;
            while (Math.abs(errorOfDegree) > 5) {
                robot.setPower(-0.0055 * errorOfDegree, -0.0055 * errorOfDegree, 0.0055 * errorOfDegree, 0.0055 * errorOfDegree);
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
            while (Math.abs(errorOfDegree) > 5) {
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
    }

}
