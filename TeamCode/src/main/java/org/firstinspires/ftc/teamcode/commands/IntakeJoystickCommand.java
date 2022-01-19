package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class IntakeJoystickCommand extends CommandBase {

    private final IntakeSubsystem m_intake;
    private final DoubleSupplier m_forward;

    public IntakeJoystickCommand(IntakeSubsystem subsystem, DoubleSupplier forward) {
        m_intake = subsystem;
        m_forward = forward;
        addRequirements(m_intake);
    }

    public void execute() {
        m_intake.extendJoystick(m_forward.getAsDouble());
    }

}

