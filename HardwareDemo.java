package org.firstinspires.ftc.teamcode.RobotProgrammingClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareDemo {

    public DcMotor demoWheel1;

    public DcMotor demoWheel2;

    public DcMotor demoWheel3;

    public DcMotor demoWheel4;

    public Servo demoServo;


    public static double maxSpeed = 1;

    private static HardwareDemo myInstance = null;
    public static HardwareDemo getInstance() {
        if (myInstance == null) {
            myInstance = new HardwareDemo();
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
           demoServo = hwMap.get(Servo.class, "demoServo")
        } catch(Exception p_exception) {
            demoServo = null;
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
