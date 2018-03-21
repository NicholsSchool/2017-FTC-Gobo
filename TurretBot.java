

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;


@TeleOp(name="Turret Bot", group="Iterative Opmode")
//@Disabled
public class TurretBot extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor topRotationMotor = null;
    private DcMotor bottomRotationMotor = null;
    private DcMotor cam = null;
    private DcMotor outMotor1 = null;
    private DcMotor outMotor2 = null;
    private DcMotor outMotor3 = null;
    private DcMotor outMotor4 = null;
    private DcMotor wrist = null;
    private CRServoImpl CarbonFiberArm = null;
    private ColorSensor colorSensor = null;
    private DistanceSensor sensorDistance = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // step (using the FTC Robot Controller app on the phone).
        topRotationMotor = hardwareMap.get(DcMotor.class, "topRotationMotor");
        bottomRotationMotor = hardwareMap.get(DcMotor.class, "bottomRotationMotor");
        cam = hardwareMap.get(DcMotor.class, "cam");
        outMotor1 = hardwareMap.get(DcMotor.class, "outMotor1");
        outMotor2 = hardwareMap.get(DcMotor.class, "outMotor2");
        outMotor3 = hardwareMap.get(DcMotor.class, "outMotor3");
        outMotor4 = hardwareMap.get(DcMotor.class, "outMotor4");
        //CarbonFiberArm = hardwareMap.get(CRServoImpl.class, "CFArm" );
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");


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

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public static double position = 0.0;
    public static double power = 0.5;
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        if(gamepad1.dpad_right) {
            topRotationMotor.setPower(power);
            bottomRotationMotor.setPower(power);
        }
        if(gamepad1.dpad_left) {
            topRotationMotor.setPower(-power);
            bottomRotationMotor.setPower(-power);
        }
        else{
            topRotationMotor.setPower(0);
            bottomRotationMotor.setPower(0);
        }

        //int hi = colorSensor.get
        if(gamepad1.a && power < 1.0){
            power += .05;
        }
        if(gamepad1.b && power > 0.0){
            power -= .05;
        }
        if(gamepad1.dpad_up){
            cam.setPower(-.5);
        }
        if(gamepad1.dpad_down){
            cam.setPower(.5);
        }
        outMotor1.setPower(gamepad1.right_stick_y);
        outMotor2.setPower(gamepad1.right_stick_y);
        outMotor3.setPower(gamepad1.right_stick_y);
        outMotor4.setPower(gamepad1.right_stick_y);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        //telemetry.addData("Turret", "power " + power);
        //telemetry.addData("sensor", "color sensor = " + colorSensor.red());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
