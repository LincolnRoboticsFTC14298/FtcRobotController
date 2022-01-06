package org.firstinspires.ftc.teamcode.commands.tank;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.tank.TankDriveSubsystem;

import java.util.function.DoubleSupplier;

public class TankDriveCommand extends CommandBase {

    private final TankDriveSubsystem drive;
    private final DoubleSupplier leftY, leftX, rightX;

    public TankDriveCommand(TankDriveSubsystem drive, DoubleSupplier leftY,
                               DoubleSupplier leftX, DoubleSupplier rightX) {
        this.drive = drive;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(leftY.getAsDouble(), leftX.getAsDouble(), rightX.getAsDouble());
    }

}