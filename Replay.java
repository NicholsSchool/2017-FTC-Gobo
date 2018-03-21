

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Replay", group="Iterative Opmode")

public class Replay extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor1 = null;
    private DcMotor elevatorMotor2 = null;
    private DcMotor leftGrabber = null;
    private DcMotor rightGrabber = null;
    private boolean onTime;
    private long startTimestamp, nextTimestamp, deltaTimestamp;
    private Record record = null;
    private boolean isReplaying;
    //public int i = 0;


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
        for(int i = 0; opModeIsActive() && Record.NextTimeStamp.size() > i; i++){
            nextTimestamp = Record.NextTimeStamp.get(i);
            deltaTimestamp = nextTimestamp - (System.currentTimeMillis() - startTimestamp);
            if(deltaTimestamp > 0){
                i--;
            }
            if(deltaTimestamp <= 0) {
                record.Motor.get(i*8).setPower(record.Values.get(i*8));
                record.Motor.get(i*8 + 1).setPower(record.Values.get(i*8 + 1));
                record.Motor.get(i*8 + 2).setPower(record.Values.get(i*8 + 2));
                record.Motor.get(i*8 + 3).setPower(record.Values.get(i*8 + 3));
                record.Motor.get(i*8 + 4).setPower(record.Values.get(i*8 + 4));
                record.Motor.get(i*8 + 5).setPower(record.Values.get(i*8 + 5));
                record.Motor.get(i*8 + 6).setPower(record.Values.get(i*8 + 6));
                record.Motor.get(i*8 + 7).setPower(record.Values.get(i*8 + 7));
            }
        }
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);
        elevatorMotor1.setPower(0);
        elevatorMotor2.setPower(0);;
        leftGrabber.setPower(0);;
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
