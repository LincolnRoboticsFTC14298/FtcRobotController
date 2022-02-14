package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class DumpCommand extends SequentialCommandGroup {

    public static double seconds = .75;

    public DumpCommand(ElevatorSubsystem subsystem) {

        addCommands(
                new InstantCommand(subsystem::dump, subsystem),
                new WaitCommand(seconds)
        );

        addRequirements(subsystem);
    }

}
