package org.firstinspires.ftc.teamcode.MechanicalGeniuses.MechanicalGeniuses3839;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

public class CHardware {

    public BNO055IMU gyro;
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;
    public DcMotor iM;
    public DcMotor wGg;
    public Servo wGa;
    public DcMotor oS;
    public Servo sF;
    private static Hardware myInstance = null;

    public double maxSpeed = 1;

    public static Hardware getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
        /* Return motor */
        try {
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setPower(0);
        } catch (Exception p_exception) {
            rf = null;
        }
        try {
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb.setPower(0);
        } catch (Exception p_exception) {
            rb = null;
        }
        try {
            lf = hwMap.get(DcMotor.class, "lf");
            lf.setDirection(DcMotorSimple.Direction.REVERSE);
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lf.setPower(0);
        } catch (Exception p_exception) {
            lf = null;
        }
        try {
            lb = hwMap.get(DcMotor.class, "lb");
            lb.setDirection(DcMotorSimple.Direction.REVERSE);
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setPower(0);
        } catch (Exception p_exception) {
            lb = null;
        }
        try {
            iM = hwMap.get(DcMotor.class, "iM");
            iM.setDirection(DcMotorSimple.Direction.REVERSE);
            iM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            iM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            iM.setPower(0);
        } catch (Exception p_exception) {
            iM = null;
        }
        try {
            oS = hwMap.get(DcMotor.class, "oS");
            //oS.setDirection(DcMotorSimple.Direction.REVERSE);
            oS.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            oS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            oS.setPower(0);
        } catch (Exception p_exception) {
            oS = null;
        }
        try {
            wGg = hwMap.get(DcMotor.class, "wGg");
            wGg.setDirection(DcMotorSimple.Direction.REVERSE);
            wGg.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wGg.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wGg.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wGg.setPower(0);
        } catch (Exception p_exception) {
            wGg = null;
        }
        try {
            wGa = hwMap.get(Servo.class, "wGa");
        } catch (Exception p_exception) {
            wGa = null;
        }
        try {
            sF = hwMap.get(Servo.class, "sF");
        } catch (Exception p_exception) {
            sF = null;
        }
        try {
            gyro = hwMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            gyro.initialize(parameters);
        } catch (Exception p_exception) {
            gyro = null;
        }

    }

    public void setPower(double fr, double br, double fl, double bl) {
        if (rf != null) {
            rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        }
        if (rb != null) {
            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }
        if (lf != null) {
            lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));
        }
        if (lb != null) {
            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }
    }

    public void powerSet(double Mi) {
        if (iM != null) {
            iM.setPower(Range.clip(Mi, -maxSpeed, maxSpeed));
        }
    }

    //never put number less than 0 or more than 1
    public void wobbleArm(double aGw) {
        if (wGa != null) {
            wGa.setPosition(aGw);
        }
    }

    public void shooterFlicker(double Fs) {
        if (sF != null) {
            sF.setPosition(Fs);
        }
    }

    public void armWobble(double gGw) {
        if (wGg != null) {
            wGg.setPower(Range.clip(gGw, -maxSpeed, maxSpeed));
        }
    }

    public void shootingWheel(double sO) {
        if (oS != null) {
            oS.setPower(Range.clip(sO, -maxSpeed, maxSpeed));
        }
    }
}

