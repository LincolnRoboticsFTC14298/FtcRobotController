package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitCommand extends CommandBase {

    private double time;
    private ElapsedTime timer;

    public WaitCommand(double time) {
        this.time = time;
    }

    @Override
    public void initialize() {
        timer.reset();
    }

    @Override
    public boolean isFinished() {
        return time <= timer.seconds();
    }

}
