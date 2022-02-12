package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class LiftAndHalfDumpCommand extends ParallelCommandGroup {

    public LiftAndHalfDumpCommand(ElevatorSubsystem subsystem, ElevatorSubsystem.Height height) {

        addCommands(
          new ElevatorHeightCommand(height,subsystem),
          new InstantCommand(subsystem::halfDump, subsystem)

        );

        addRequirements(subsystem);

    }

}
