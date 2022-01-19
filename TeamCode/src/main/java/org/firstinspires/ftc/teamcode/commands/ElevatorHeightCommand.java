package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class ElevatorHeightCommand extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final ElevatorSubsystem.Height m_height;

    public ElevatorHeightCommand(ElevatorSubsystem.Height height, ElevatorSubsystem subsystem) {
        m_height = height;
        m_elevatorSubsystem = subsystem;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void execute() {
        m_elevatorSubsystem.lift(m_height);
    }

    @Override
    public void end(boolean interrupted) {
        m_elevatorSubsystem.defaultPower();
    }

    @Override
    public boolean isFinished() {
        return m_elevatorSubsystem.atPosition();
    }

}