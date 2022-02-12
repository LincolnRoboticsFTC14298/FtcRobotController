package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeDistanceCommand extends CommandBase {

    private final IntakeSubsystem m_intake;
    private final double m_distance;


    public IntakeDistanceCommand(double distance, IntakeSubsystem subsystem) {
        m_intake = subsystem;
        m_distance = distance;

        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.extendToDistance(m_distance);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return m_intake.atPosition();
    }

}
