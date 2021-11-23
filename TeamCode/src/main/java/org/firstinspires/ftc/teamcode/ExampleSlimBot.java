package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.ExampleIntakeSubsystem;

public class ExampleSlimBot extends Robot {

    public ExampleIntakeSubsystem intake;

    private final GamepadEx gamepad;

    public ExampleSlimBot(HardwareMap hardwareMap, Gamepad gamepad1) {
        intake = new ExampleIntakeSubsystem(hardwareMap);

        gamepad = new GamepadEx(gamepad1);

        initialize();
    }

    public void initialize() {
        gamepad.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new ConditionalCommand(
                        new InstantCommand(intake::turnOn, intake),
                        new InstantCommand(intake::turnOff, intake),
                        () -> !intake.isActive()
                ));
    }
}
