package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;

public class ElevatorJoystickCommand extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final DoubleSupplier m_joystickYAxis;

    public ElevatorJoystickCommand(ElevatorSubsystem subsystem, DoubleSupplier joystickY) {
        m_elevatorSubsystem = subsystem;
        m_joystickYAxis = joystickY;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void execute() {
        m_elevatorSubsystem.liftJoystick(m_joystickYAxis.getAsDouble());
    }

}