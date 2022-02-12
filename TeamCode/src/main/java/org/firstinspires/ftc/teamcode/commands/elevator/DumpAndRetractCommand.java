package org.firstinspires.ftc.teamcode.commands.elevator;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class DumpAndRetractCommand extends SequentialCommandGroup {

    public static double seconds = 5;

    public DumpAndRetractCommand(ElevatorSubsystem subsystem) {

        addCommands(
                new InstantCommand(subsystem::dump, subsystem),
                new WaitCommand(seconds),
                new ParallelCommandGroup(
                        new InstantCommand(subsystem::retract, subsystem),
                        new ElevatorHeightCommand(ElevatorSubsystem.Height.BOTTOM, subsystem)
                )
        );

        addRequirements(subsystem);
    }

}
