package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.DuckWheelSubsystem;


public class ExampleSlimBot extends Robot {

    // public ExampleIntakeSubsystem intakeSubsystem;

    public DuckWheelSubsystem duckWheelSubsystem;
    private GamepadEx gamepad;

    public ExampleSlimBot(HardwareMap hardwareMap, Gamepad gamepad1) {
        // intakeSubsystem = new ExampleIntakeSubsystem(hardwareMap);
        duckWheelSubsystem = new DuckWheelSubsystem(hardwareMap);


        gamepad = new GamepadEx(gamepad1);

        initialize();
    }

    public void initialize() {
//        gamepad.getGamepadButton(GamepadKeys.Button.B)
//                .whenPressed(new ConditionalCommand(
//                        new InstantCommand(intake::turnOn, intake),
//                        new InstantCommand(intake::turnOff, intake),
//                        () -> !intake.isActive()
//                ));

        gamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(duckWheelSubsystem::turnOn, duckWheelSubsystem))
                .whenReleased(new InstantCommand(duckWheelSubsystem::turnOff, duckWheelSubsystem));



    }
}
