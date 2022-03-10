package org.firstinspires.ftc.teamcode.commands.mecanum;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.mecanum.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class TrajectorySequenceFollowerCommand extends CommandBase {

    private final MecanumDriveSubsystem drive;
    private final TrajectorySequence trajectorySequence;

    public TrajectorySequenceFollowerCommand(MecanumDriveSubsystem drive, TrajectorySequence trajectorySequence) {
        this.drive = drive;
        this.trajectorySequence = trajectorySequence;

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.followTrajectorySequence(trajectorySequence);
    }

    @Override
    public void execute() {
        drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !drive.isBusy();
    }


}
