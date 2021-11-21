package org.firstinspires.ftc.teamcode.example;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Mecanum Bot", group="Linear Opmode")
@Disabled
public class MecanumBot extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor bottomLeftMotor, bottomRightMotor, topLeftMotor, topRightMotor;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        topLeftMotor  = hardwareMap.get(DcMotor.class, "topLeft");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRight");
        bottomLeftMotor = hardwareMap.get(DcMotor.class,"bottomLeft" );
        bottomRightMotor =  hardwareMap.get(DcMotor.class,"bottomRightMotor" );

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        topLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        topRightMotor.setDirection(DcMotor.Direction.REVERSE);
        bottomLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        bottomRightMotor.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double topLeftPower;
            double topRightPower;
            double bottomLeftPower;
            double bottomRightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double y = -gamepad1.left_stick_y;
            double x  =  gamepad1.left_stick_x;
            double t = gamepad1.right_stick_x;
            topLeftPower    = y + x + t;
            topRightPower   = y - x - t;
            bottomLeftPower = y - x + t;
            bottomRightPower = y - x - t;

            double largest = max(1, abs(topLeftPower));
            largest = max(largest, abs(topRightPower));
            largest = max(largest, abs(bottomLeftPower));
            largest = max(largest, abs(bottomRightPower));

            if (largest > 1){
                topRightPower /= largest;
                topLeftPower /= largest;
                bottomRightPower /= largest;
                bottomLeftPower /= largest;

            }

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // bottomLeftPower  = -gamepad1.left_stick_y ;
            // bottomRightPower = -gamepad1.right_stick_y ;
            // topLeftPower = -gamepad1.right_stick_x ;
            // topRightPower = -gamepad1.right_stick_x ;

            // Send calculated power to wheels
            bottomLeftMotor.setPower(bottomLeftPower);
            bottomRightMotor.setPower(bottomRightPower);
            topLeftMotor.setPower(topLeftPower);
            topRightMotor.setPower(topRightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "top left (%.2f), top right (%.2f), bottom left (%.2f), bottom right (%.2f)", topLeftPower, topRightPower, bottomLeftPower, bottomRightPower);
            telemetry.update();


        }
    }
}

