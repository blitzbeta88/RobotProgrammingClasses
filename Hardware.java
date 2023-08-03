package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Hardware {

    public DcMotor demoWheel1;
    public DcMotor demoWheel2;
    public DcMotor demoWheel3;
    public DcMotor demoWheel4;
    public DcMotor demoMotor;

    public Servo demoServo;

    public BNO055IMU gyro;

    public RevColorSensorV3 demoColorSensor;

    public static double maxSpeed = 1;

    private static Hardware myInstance = null;

    public static Hardware getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {

        try {
            demoWheel1 = hwMap.get(DcMotor.class, "demoWheel1");
            demoWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel1.setPower(0);
        } catch (Exception p_exception) {
            demoWheel1 = null;
        }

        try {
            demoWheel2 = hwMap.get(DcMotor.class, "demoWheel2");
            demoWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel2.setPower(0);
        } catch (Exception p_exception) {
            demoWheel2 = null;
        }

        try {
            demoWheel3 = hwMap.get(DcMotor.class, "demoWheel3");
            demoWheel3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel3.setPower(0);
        } catch (Exception p_exception) {
            demoWheel3 = null;
        }

        try {
            demoWheel4 = hwMap.get(DcMotor.class, "demoWheel4");
            demoWheel4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel4.setPower(0);
        } catch (Exception p_exception) {
            demoWheel4 = null;
        }

        try {
            demoMotor = hwMap.get(DcMotor.class, "demoMotor");
            demoMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoMotor.setPower(0);
        } catch (Exception p_exception) {
            demoMotor = null;
        }

        try {
            demoServo = hwMap.get(Servo.class, "demoServo");
        } catch (Exception p_exception) {
            demoServo = null;
        }

        try {
            gyro = hwMap.get(BNO055IMU.class, "gyro");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = true;
            parameters.loggingTag = "gyro";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            gyro.initialize(parameters);
        } catch (Exception p_exception) {
            gyro = null;
        }

        try {
            demoColorSensor = hwMap.get(RevColorSensorV3.class, "demoColorSensor");
        } catch (Exception p_exception) {
            demoColorSensor = null;
        }

    }

    public void setPower(double wheelDemo1, double wheelDemo2, double wheelDemo3, double wheelDemo4) {
        if (demoWheel1 != null) {
            demoWheel1.setPower(Range.clip(wheelDemo1, -maxSpeed, maxSpeed));
        }
        if (demoWheel2 != null) {
            demoWheel2.setPower(Range.clip(wheelDemo2, -maxSpeed, maxSpeed));
        }
        if (demoWheel3 != null) {
            demoWheel3.setPower(Range.clip(wheelDemo3, -maxSpeed, maxSpeed));
        }
        if (demoWheel4 != null) {
            demoWheel4.setPower(Range.clip(wheelDemo4, -maxSpeed, maxSpeed));
        }

    }

}
