package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;
import java.io.*;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Record", group="Iterative Opmode")
//@Disabled
public class Record extends OpMode
{
    // Declare OpMode members.
    private static ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor1 = null;
    private DcMotor elevatorMotor2 = null;
    private DcMotor leftGrabber = null;
    private DcMotor rightGrabber = null;
    private FileWriter writer;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor"); // done
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");// done
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");// done
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");// done
        elevatorMotor1 = hardwareMap.get(DcMotor.class, "elevatorMotor1");
        elevatorMotor2 = hardwareMap.get(DcMotor.class, "elevatorMotor2");
        leftGrabber = hardwareMap.get(DcMotor.class, "leftGrabber" );
        rightGrabber = hardwareMap.get(DcMotor.class, "rightGrabber" );

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public static ArrayList <Double> Values = new ArrayList<Double>();
    public static ArrayList <DcMotor> Motor = new ArrayList<DcMotor>();
    public static ArrayList <Long> NextTimeStamp = new ArrayList<Long>();
    public static long startTimestamp = System.currentTimeMillis();
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.

        leftPower = gamepad1.left_stick_y;
        rightPower = -gamepad1.right_stick_y;
        // Send calculated power to wheels
        leftFrontMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        leftBackMotor.setPower(leftPower);
        rightBackMotor.setPower(rightPower);
        double speed = .7;
        if (gamepad1.left_bumper){
            leftFrontMotor.setPower(speed);
            leftBackMotor.setPower(-speed);
            rightFrontMotor.setPower(-speed);
            rightBackMotor.setPower(speed);
        }
        else if (gamepad1.right_bumper){
            leftFrontMotor.setPower(-speed);
            leftBackMotor.setPower(speed);
            rightFrontMotor.setPower(speed);
            rightBackMotor.setPower(-speed);
        }

        if(gamepad2.dpad_down) {
            elevatorMotor1.setPower(.8);
            elevatorMotor2.setPower(.8);
        }
        else if(gamepad2.dpad_up) {
            elevatorMotor1.setPower(-.8);
            elevatorMotor2.setPower(-.8);
        }
        else{
            elevatorMotor1.setPower(0);
            elevatorMotor2.setPower(0);
        }

        elevatorMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //if(gamepad2.right_trigger>0 || gamepad2.left_trigger>0){

        leftGrabber.setPower(gamepad2.right_trigger);
        rightGrabber.setPower(gamepad2.right_trigger);
        leftGrabber.setPower(-gamepad2.left_trigger);
        rightGrabber.setPower(-gamepad2.left_trigger);

        Motor.add(leftFrontMotor);
        Motor.add(leftBackMotor);
        Motor.add(rightFrontMotor);
        Motor.add(rightBackMotor);
        Motor.add(elevatorMotor1);
        Motor.add(elevatorMotor2);
        Motor.add(leftGrabber);
        Motor.add(rightGrabber);
        Values.add(leftFrontMotor.getPower());
        Values.add(leftBackMotor.getPower());
        Values.add(rightFrontMotor.getPower());
        Values.add(rightBackMotor.getPower());
        Values.add(elevatorMotor1.getPower());
        Values.add(elevatorMotor2.getPower());
        Values.add(leftGrabber.getPower());
        Values.add(rightGrabber.getPower());
        NextTimeStamp.add(System.currentTimeMillis() - startTimestamp);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
