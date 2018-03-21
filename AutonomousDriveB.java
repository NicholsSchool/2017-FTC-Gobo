

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autoModeJustPark", group="Iterative Opmode")

public class AutonomousDriveB extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor = null;
    private DcMotor extenderMotor = null;
    private Servo leftGrabber = null;
    private Servo rightGrabber = null;
    private AnalogInput colorPot = null;
    private AnalogInput positionPot = null;
    private CRServoImpl CarbonFiberArm = null;
    private ColorSensor colorSensor;

    @Override
    public void runOpMode() {

        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");
        extenderMotor = hardwareMap.get(DcMotor.class, "extenderMotor");
        leftGrabber = hardwareMap.get(Servo.class, "leftGrabber" );
        rightGrabber = hardwareMap.get(Servo.class, "rightGrabber" );
        CarbonFiberArm = hardwareMap.get(CRServoImpl.class, "CFArm" );
        colorPot = hardwareMap.get(AnalogInput.class, "colorPot" );
        positionPot = hardwareMap.get(AnalogInput.class, "positionPot" );
        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        telemetry.addData("value", colorSensor.argb());

        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("pot value", colorPot.getVoltage());
            telemetry.addData("pot value", positionPot.getVoltage());
            telemetry.update();
        }
        if(positionPot.getVoltage() > 6) {
            rightFrontMotor.setPower(-.2);
            rightBackMotor.setPower(-.2);
        }
        else if(positionPot.getVoltage() < 6){
            rightFrontMotor.setPower(.2);
            rightBackMotor.setPower(.2);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
