package org.firstinspires.ftc.teamcode.opmodes.tuning.intake;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeJoystickCommand;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

@TeleOp
@Disabled
public class IntakeFeedforwardTuning extends CommandOpMode {

    private IntakeSubsystem subsystem;

    @Override
    public void initialize() {
        subsystem = new IntakeSubsystem(hardwareMap);
        subsystem.setDefaultCommand(new IntakeJoystickCommand(subsystem,() -> -gamepad1.left_stick_y));
    }
}
