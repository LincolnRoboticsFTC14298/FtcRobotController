package org.firstinspires.ftc.teamcode.opmodes.tuning.elevator;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.ElevatorHeightCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class ElevatorPulseTuning extends CommandOpMode {

    private ElevatorSubsystem subsystem;

    @Override
    public void initialize() {
        subsystem = new ElevatorSubsystem(hardwareMap);
        schedule(new ElevatorHeightCommand(ElevatorSubsystem.Height.MIDDLE, subsystem));
    }

}
