package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeDistanceCommand extends CommandBase {

    private final IntakeSubsystem m_intake;

    public IntakeDistanceCommand(IntakeSubsystem subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }
}
