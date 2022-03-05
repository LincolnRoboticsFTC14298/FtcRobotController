package org.firstinspires.ftc.teamcode.opmodes.tuning.intake;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

@TeleOp
@Disabled
public class IntakePulseTuning extends CommandOpMode {

    private IntakeSubsystem subsystem;

    @Override
    public void initialize() {
        subsystem = new IntakeSubsystem(hardwareMap);
        schedule(new IntakeDistanceCommand(0,subsystem));
    }

}
