package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.FileWriter;
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
public class Record2 extends OpMode
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
    private boolean isRecording;

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

        try{
            setup();
        }
        catch (IOException ioe)
        {
            System.out.println("Exception!!!");
            ioe.printStackTrace();
        }

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
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
    public static long startTimestamp = System.currentTimeMillis();
    public void setup() throws IOException {
        writer = new FileWriter("replayFilePath.txt");
        startTimestamp = System.currentTimeMillis();
        isRecording = true;
    }
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
        try{
            record();
        }
        catch (IOException ioe)
        {
            System.out.println("Exception!!!");
            ioe.printStackTrace();
        }

        // Show the elapsed game time and wheel power.
        //telemetry.addData("Status", "Run Time: " + runtime.toString());
        //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }
    public void record() throws IOException {
        if (writer != null) {
            writer.append("" + (System.currentTimeMillis() - startTimestamp));
            // drive left Front
            writer.append("," + leftFrontMotor.getPower());
            // drive left Back
            writer.append("," + leftBackMotor.getPower());
            // drive right Front
            writer.append("," + rightFrontMotor.getPower());
            // drive right Back
            writer.append("," + rightBackMotor.getPower());
            // elevatorMotor1
            writer.append("," + elevatorMotor1.getPower());
            //elevatorMotor2
            writer.append("," + elevatorMotor2.getPower());
            //leftGrabber
            writer.append("," + leftGrabber.getPower());
            //rightGrabber
            writer.append("," + rightGrabber.getPower());
        }
    }
    public void end() throws IOException {
        isRecording = false;
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
