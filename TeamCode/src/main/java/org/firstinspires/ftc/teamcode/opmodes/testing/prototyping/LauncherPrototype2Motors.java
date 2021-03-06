package org.firstinspires.ftc.teamcode.opmodes.testing.prototyping;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.util.Range.clip;


@TeleOp(name="Launcher 2 Motors", group="Prototype")
@Disabled
public class LauncherPrototype2Motors extends LinearOpMode {
    public final double MOTOR_STEP = 0.05; // Motor speed step

    @Override
    public void runOpMode() {
        double mSpeed1 = 0;
        double mSpeed2 = 0;

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "motor2");

        // Uncomment one of these if you want to reverse one of the motors
        // May do this for the motors on opposite side
        // Forward may be clockwise but I forgot

        //motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        //motor2.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setPower(0);
        motor2.setPower(0);

        waitForStart();


        while (opModeIsActive()) {
            // Up and down updates the first motor speed
            // Left and right updates the second motor speed
            if (gamepad1.dpad_up) {
                mSpeed1 += MOTOR_STEP;
            } else if (gamepad1.dpad_down) {
                mSpeed1 -= MOTOR_STEP;
            } else if (gamepad1.dpad_right) {
                mSpeed2 += MOTOR_STEP;
            } else if (gamepad1.dpad_left) {
                mSpeed2 -= MOTOR_STEP;
            } else if (gamepad1.a) {
                mSpeed1 = 0;
            } else if (gamepad1.b) {
                mSpeed2 = 0;
            }

            mSpeed1 = clip(mSpeed1, -1, 1);
            mSpeed2 = clip(mSpeed2, -1, 1);

            motor1.setPower(mSpeed1);
            motor2.setPower(mSpeed2);

            // Telemetry
            telemetry.addData("Motor 1 power: ", mSpeed1);
            telemetry.addData("Motor 2 power: ", mSpeed2);
            telemetry.update();
        }

    }
}