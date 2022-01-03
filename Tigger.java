package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Tigger", group="Linear Opmode")

public class Tigger extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftDriver = null;
    private DcMotor rightDriver = null;
    private DcMotor armDriver = null;
    private DcMotor ernie_ducky = null;
    private Servo ernies_hand = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        //The reason I need a different name for every wheel is becasue of how the control hub works
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDriver  = hardwareMap.get(DcMotor.class, "left_driver");
        rightDriver = hardwareMap.get(DcMotor.class, "right_driver");
        armDriver = hardwareMap.get(DcMotor.class, "arm_driver");
        ernie_ducky = hardwareMap.get(DcMotor.class, "ernies_ducky"); //This is for the carousel
        ernies_hand = hardwareMap.get(Servo.class, "ernies_hand" ); //This is the claw
        
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDriver.setDirection(DcMotor.Direction.REVERSE);
        rightDriver.setDirection(DcMotor.Direction.FORWARD);
        
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double frontleftPower;
            double frontrightPower;
            double backleftPower;
            double backrightPower;

            double drive = gamepad1.right_trigger;
            double turn  =  gamepad1.left_stick_x;
            double reverse = -gamepad1.left_trigger;
            double armPower = gamepad1.right_stick_y;
            double arm = Range.clip(armPower, -0.5 , 0.09);
            
            //The reason for this is so it is easier to do controlled movements and put on some speed when we need it
            if (gamepad1.b == true) { 
                frontleftPower    = Range.clip(drive + reverse + turn, -1.0, 1.0);
                frontrightPower   = Range.clip(drive + reverse - turn, -1.0, 1.0);
                backleftPower    = Range.clip(drive + reverse + turn, -1.0, 1.0);
                backrightPower   = Range.clip(drive + reverse - turn, -1.0, 1.0);
            }
            else {
                frontleftPower    = Range.clip(drive + reverse + turn, -0.2, 0.2) ;
                frontrightPower   = Range.clip(drive + reverse - turn, -0.2, 0.2) ;
                backleftPower    = Range.clip(drive + reverse + turn, -0.2, 0.2) ;
                backrightPower   = Range.clip(drive + reverse - turn, -0.2, 0.2) ;
            }

            // Send calculated power to wheels
            leftDrive.setPower(frontleftPower);
            rightDrive.setPower(frontrightPower);
            leftDriver.setPower(backleftPower);
            rightDriver.setPower(backrightPower);            
            
            // this is the arm code, The motor cannot hold the arm in place without the second part code, I'm not sure why
             if ((gamepad1.right_stick_y > 0) || (gamepad1.right_stick_y < 0)) {
               armDriver.setPower(arm);
            }
            else {
                armDriver.setPower(- 0.001); 
            }
            
            //this is the 'claw' code
            if (gamepad1.dpad_left == true) {
                ernies_hand.setPosition(0);
            }
            if (gamepad1.dpad_right == true) {
                ernies_hand.setPosition(0.4);
                
            }
            
            // this is the ducky code, that is, the code to turn the carousel
            if (gamepad1.y == true) {
                ernie_ducky.setPower(1);
            }
            
            else if (gamepad1.a == true) {
                ernie_ducky.setPower(-1);
            }
            
            else {
                ernie_ducky.setPower(0);
            }
            
            // Show the elapsed game time and wheel power for each individua wheel
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front Motors", "left (%.2f), right (%.2f)", frontleftPower, frontrightPower);
            telemetry.addData("Back Motors" , "left (%.2f), right (%.2f)", backleftPower, backrightPower);
            telemetry.addData("Input", "left X (%.2f), right Y (%.2f)", gamepad1.left_stick_x, gamepad1.right_stick_y);
            telemetry.update();
        }
    }
}
