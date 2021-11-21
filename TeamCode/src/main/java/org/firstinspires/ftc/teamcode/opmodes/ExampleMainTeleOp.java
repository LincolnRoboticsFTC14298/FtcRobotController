package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ExampleSlimBot;

@TeleOp
public class ExampleMainTeleOp extends CommandOpMode {

    ExampleSlimBot robot;

    @Override
    public void initialize() {
        robot = new ExampleSlimBot(hardwareMap, gamepad1);
    }

}
