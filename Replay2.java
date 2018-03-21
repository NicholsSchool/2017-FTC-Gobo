

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Scanner;
import java.io.*;

@Autonomous(name="Replay", group="Iterative Opmode")

public class Replay2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor1 = null;
    private DcMotor elevatorMotor2 = null;
    private DcMotor leftGrabber = null;
    private DcMotor rightGrabber = null;
    private Scanner scanner;
    private boolean onTime;
    private long startTimestamp, nextTimestamp, deltaTimestamp;
    private boolean isReplaying;
    //public int i = 0;

    public void setup() throws FileNotFoundException {
        scanner = new Scanner(new File("replayFilePath.txt"));
        scanner.useDelimiter(",|\\n");
        onTime = true;
        isReplaying = true;
        startTimestamp = System.currentTimeMillis();
    }


    @Override
    public void runOpMode() {

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

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();
        runtime.reset();
        onTime = true;
        startTimestamp = System.currentTimeMillis();
        isReplaying = true;
        while ((scanner != null) && scanner.hasNext()) {
            if (onTime) {
                nextTimestamp = scanner.nextLong();
            }
            deltaTimestamp = nextTimestamp - (System.currentTimeMillis() - startTimestamp);
            if (deltaTimestamp <= 0) {
                leftFrontMotor.setPower(scanner.nextDouble());
                leftBackMotor.setPower(scanner.nextDouble());
                rightFrontMotor.setPower(scanner.nextDouble());
                rightBackMotor.setPower(scanner.nextDouble());
                // elevatorMotor1
                elevatorMotor1.setPower(scanner.nextDouble());
                // elevatorMotor2
                elevatorMotor2.setPower(scanner.nextDouble());
                leftGrabber.setPower(scanner.nextDouble());
                rightGrabber.setPower(scanner.nextDouble());
                // currently on time
                onTime = true;
            } else {
                onTime = false;
            }
        }
        isReplaying = false;
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        // elevatorMotor1
        elevatorMotor1.setPower(0);
        // elevatorMotor2
        elevatorMotor2.setPower(0);
        leftGrabber.setPower(0);
        rightGrabber.setPower(0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
