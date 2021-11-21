package org.firstinspires.ftc.teamcode.example;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic Linear Opmode", group="Linear Opmode")
public class PushBot extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    DcMotor leftMotor, rightMotor;

    @Override
    public void runOpMode() {
        telemetry.addData("Greeting", "Hello");
        telemetry.update();

        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");


        leftMotor.setPower(0);
        rightMotor.setPower(0);

        waitForStart();
        while(opModeIsActive());
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;

            double l = x + y;
            double r = -x + y;

            double m = Math.max(abs(l), abs(r));
            if (m > 1) {
                l /= m;
                l/= m;
            }

            leftMotor.setPower(l);
            rightMotor.setPower(r);

            telemetry.addData("Target left power:", l);
            telemetry.addData("Target right power:", r);
            telemetry.addData("Target left power:", leftMotor.getPower());
            telemetry.addData("Target right power:", rightMotor.getPower());
            telemetry.update();

    }
}
