package org.firstinspires.ftc.teamcode.opmodes.tuning.elevator;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class ElevatorGravityTuning extends CommandOpMode {

    private ElevatorSubsystem subsystem;

    @Override
    public void initialize() {
        subsystem = new ElevatorSubsystem(hardwareMap);
        // TODO: Make sure it works
        subsystem.setDefaultCommand(new RunCommand(subsystem::defaultPower, subsystem));
    }

}