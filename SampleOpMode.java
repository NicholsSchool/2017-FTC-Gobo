

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Drive glyph bot", group="Iterative Opmode")

public class AutonomousDrive extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor1 = null;
    private DcMotor elevatorMotor2 = null;
    private DcMotor leftGrabber = null;
    private DcMotor rightGrabber = null;
    private Servo ser = null;
    private ColorSensor colorSensor;

    @Override
    public void runOpMode() {

        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        elevatorMotor1 = hardwareMap.get(DcMotor.class, "elevatorMotor1");
        elevatorMotor2 = hardwareMap.get(DcMotor.class, "elevatorMotor2");
        leftGrabber = hardwareMap.get(DcMotor.class, "leftGrabber" );
        rightGrabber = hardwareMap.get(DcMotor.class, "rightGrabber" );
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        ser = hardwareMap.get(Servo.class, "ser");

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        ser.setPosition(.8);
        while (opModeIsActive() && (runtime.seconds() < 5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        int color = colorSensor.red();
        if(color < 40){
            rightFrontMotor.setPower(.2);
            rightBackMotor.setPower(.2);
        }
        else if(color > 40){
            rightFrontMotor.setPower(-.2);
            rightBackMotor.setPower(-.2);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        if(color < 40){
            rightFrontMotor.setPower(-.2);
            rightBackMotor.setPower(-.2);
        }
        else if(color > 40){
            rightFrontMotor.setPower(.2);
            rightBackMotor.setPower(.2);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftGrabber.setPower(-.5);
        rightGrabber.setPower(-.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftGrabber.setPower(-.2);
        rightGrabber.setPower(-.2);
        rightFrontMotor.setPower(.2);
        rightBackMotor.setPower(.2);
        leftBackMotor.setPower(-.2);
        leftFrontMotor.setPower(-.2);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        rightFrontMotor.setPower(-.2);
        rightBackMotor.setPower(-.2);
        leftBackMotor.setPower(-.2);
        leftFrontMotor.setPower(-.2);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.7)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        rightFrontMotor.setPower(.2);
        rightBackMotor.setPower(.2);
        leftBackMotor.setPower(-.2);
        leftFrontMotor.setPower(-.2);
        leftGrabber.setPower(.5);
        rightGrabber.setPower(.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .8)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        leftBackMotor.setPower(0);
        leftFrontMotor.setPower(0);
        leftGrabber.setPower(0);
        rightGrabber.setPower(0);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
