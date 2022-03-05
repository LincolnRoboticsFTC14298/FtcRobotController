package org.firstinspires.ftc.teamcode.opmodes.tuning.elevator;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.elevator.ElevatorJoystickCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

@TeleOp
@Disabled
public class ElevatorFeedforwardTuning extends CommandOpMode {

    private ElevatorSubsystem subsystem;

    @Override
    public void initialize() {
        subsystem = new ElevatorSubsystem(hardwareMap);
        subsystem.setDefaultCommand(new ElevatorJoystickCommand(subsystem,() -> -gamepad1.left_stick_y));
    }
}
