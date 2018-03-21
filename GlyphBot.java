

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Glyph Bot", group="Iterative Opmode")
//@Disabled
public class GlyphBot extends OpMode
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
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // step (using the FTC Robot Controller app on the phone).
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        elevatorMotor1 = hardwareMap.get(DcMotor.class, "elevatorMotor1");
        elevatorMotor2 = hardwareMap.get(DcMotor.class, "elevatorMotor2");
        leftGrabber = hardwareMap.get(DcMotor.class, "leftGrabber" );
        rightGrabber = hardwareMap.get(DcMotor.class, "rightGrabber" );


        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        //elevatorMotor.setMode(DcMotor.ZeroPowerBehavior.BRAKE);



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

    public static boolean fast = true;
    public static double speed = 1.0;

    @Override
    public void loop() {
        double leftPower;
        double rightPower;

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

        if(gamepad1.a){
            fast = !fast;
        }

       // if(fast ){\\




            speed = .3;
            leftPower = gamepad1.left_stick_y/3;
            rightPower = gamepad1.right_stick_y/3;
        //}
        //else{
        //    speed = 1.0;
        //leftPower = gamepad1.left_stick_y;
        //    rightPower = gamepad1.right_stick_y;
        //}
        //runtime.
        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);

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

        leftGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("position",rightGrabber.getTargetPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
