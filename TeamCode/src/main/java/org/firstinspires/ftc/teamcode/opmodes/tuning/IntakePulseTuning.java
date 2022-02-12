package org.firstinspires.ftc.teamcode.opmodes.tuning;

import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.IntakeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakePulseTuning extends CommandOpMode {

    private IntakeSubsystem subsystem;

    @Override
    public void initialize(){
        subsystem = new IntakeSubsystem(hardwareMap);
        schedule(new IntakeDistanceCommand(1.0,subsystem));
    }

}
