package org.firstinspires.ftc.teamcode.MechanicalGeniuses.MechanicalGeniuses3839;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;

@Autonomous (name = "DemoAuto")
@Disabled
//Researh LinearOpMode vs OpMode
public class CDemoAuto extends LinearOpMode {
    Hardware robot = CHardware.getInstance();


    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        telemetry.addData("Status","Initialized");
        telemetry.update();
        //Waiting for driver to press start
        waitForStart();

        //Add instructions
        forward( 2, 1);



    }

    public void forward(double distanceMoving, double speedMoving) {

        double wheelCircumfrance = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor/wheelCircumfrance));


        robot.setPower(0,0,0,0);

        //ALWAYS set the Target Postition before setting to Run_To_Position
        robot.rf.setTargetPostion((int) Math.round(ticks));

        robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speedMoving, speedMoving, speedMoving, speedMoving);

        while( opModeIsActive() && (robot.rf.getCurrentPositon() + 10 < ticks || robot.rf.getCurrentPositon() + 10 > ticks  ) {

        }

        robot.setPower(0,0,0,0);

        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

}
