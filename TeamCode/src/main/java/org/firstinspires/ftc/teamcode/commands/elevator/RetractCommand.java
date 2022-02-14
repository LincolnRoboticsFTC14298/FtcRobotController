package org.firstinspires.ftc.teamcode.commands.elevator;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class RetractCommand extends SequentialCommandGroup {

    public RetractCommand(ElevatorSubsystem subsystem) {

        addCommands(
                new ParallelCommandGroup(
                        new InstantCommand(subsystem::retract, subsystem),
                        new ElevatorHeightCommand(ElevatorSubsystem.Height.BOTTOM, subsystem)
                )
        );

        addRequirements(subsystem);
    }

}
