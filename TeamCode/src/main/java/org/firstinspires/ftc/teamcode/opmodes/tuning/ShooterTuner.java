package org.firstinspires.ftc.teamcode.opmodes.tuning;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotlib.hardware.gamepad.RadicalGamepad;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Field;

@Config
@TeleOp(name="Shooter Tuner", group="Tuner")
@Disabled
public class ShooterTuner extends OpMode {
    private Robot robot;
    private RadicalGamepad gamepad;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        gamepad = new RadicalGamepad(gamepad1);
    }

    @Override
    public void start() {
        robot.start();
        robot.setPoseEstimate(new Pose2d(0,0,0));
        robot.setTarget(Field.Target.HIGH_GOAL);
    }

    @Override
    public void loop() {
        gamepad.update();

        Pose2d input = new Pose2d(-gamepad.left_stick_y, -gamepad.left_stick_x, gamepad.right_stick_x);
        robot.drive.teleopControl(input, true, true);

        robot.shooter.aimAsync();

        if (gamepad.a) {
            robot.shooter.launch();
        }

        robot.update();

        telemetry.addData("Is extended: ", robot.shooter.isExtended());
        telemetry.addData("Is retracted: ", robot.shooter.isRetracted());
        telemetry.addData("Launch status: ", robot.shooter.getLaunchStatus().toString());
        telemetry.addData("Is done aiming: ", robot.shooter.doneAiming());
        telemetry.addData("Is ready to launch: ", robot.shooter.readyToLaunch());
        telemetry.update();
    }
}
