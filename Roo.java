

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Roo", group="Linear Opmode")


public class Roo extends LinearOpMode {

    private ElapsedTime     runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftDriver = null; 
    private DcMotor rightDriver = null;
    private DcMotor ernie_ducky = null;


    @Override
    public void runOpMode() {

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDriver  = hardwareMap.get(DcMotor.class, "left_driver");
        rightDriver = hardwareMap.get(DcMotor.class, "right_driver");
        ernie_ducky = hardwareMap.get(DcMotor.class, "ernies_ducky");
        
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD); 
        leftDriver.setDirection(DcMotor.Direction.REVERSE);
        rightDriver.setDirection(DcMotor.Direction.FORWARD);
        
        // Send telemetry message to signify robot waiting;
        
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        leftDrive.setPower(-0.25);
        rightDrive.setPower(-0.25);
        leftDriver.setPower(-0.25);
        rightDriver.setPower(-0.25);        
        while ((opModeIsActive()) && (runtime.seconds() < 1.0)) {
            telemetry.addData("Status", "Runtime", runtime.seconds());
            telemetry.update();
        }
        
        
        
        ernie_ducky.setPower(1);
        runtime.reset();
        while ((opModeIsActive()) && (runtime.seconds() < 10.0)) {
            telemetry.addData("Status", "Runtime", runtime.seconds());
            telemetry.update();
        }
        
        leftDrive.setPower(1);
        rightDrive.setPower(1);
        leftDriver.setPower(1);
        rightDriver.setPower(1);
        
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Status", "Runtime", runtime.seconds());
            telemetry.update();
            
        }
    }
}

