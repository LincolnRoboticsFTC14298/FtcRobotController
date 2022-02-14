package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.WaitCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.RetractCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.ElevatorHeightCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.LiftAndHalfDumpCommand;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

@TeleOp
@Disabled
public class ElevatorTesting extends CommandOpMode {

    private ElevatorSubsystem subsystem;

    public void initialize() {
        subsystem = new ElevatorSubsystem(hardwareMap);
        schedule(
                new ElevatorHeightCommand(ElevatorSubsystem.Height.LOW,subsystem),
                new WaitCommand(1),
                new ElevatorHeightCommand(ElevatorSubsystem.Height.MIDDLE,subsystem),
                new WaitCommand(1),
                new ElevatorHeightCommand(ElevatorSubsystem.Height.TOP,subsystem),
                new WaitCommand(1),
                new ElevatorHeightCommand(ElevatorSubsystem.Height.BOTTOM,subsystem),
                new WaitCommand(1),
                new LiftAndHalfDumpCommand(subsystem,ElevatorSubsystem.Height.TOP),
                new RetractCommand(subsystem)
        );
    }
}
