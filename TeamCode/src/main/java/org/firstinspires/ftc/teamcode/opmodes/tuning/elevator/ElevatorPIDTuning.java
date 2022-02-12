package org.firstinspires.ftc.teamcode.opmodes.tuning.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class ElevatorPIDTuning extends CommandOpMode {

    private ElevatorSubsystem subsystem;
    public static double period;
    public static ElevatorSubsystem.Height height1;
    public static ElevatorSubsystem.Height height2;

    @Override
    public void initialize() {
        subsystem = new ElevatorSubsystem(hardwareMap);
        subsystem.setDefaultCommand(new FlipFlopCommand(subsystem,period,height1,height2));
    }

    private class FlipFlopCommand extends CommandBase {

        private final ElevatorSubsystem m_subsystem;
        private double period;
        private ElapsedTime elapsedTime;
        private ElevatorSubsystem.Height height1;
        private ElevatorSubsystem.Height height2;

        public FlipFlopCommand(ElevatorSubsystem subsystem, double period, ElevatorSubsystem.Height height1, ElevatorSubsystem.Height height2) {
           m_subsystem = subsystem;
           this.period = period;
           this.height1 = height1;
           this.height2 = height2;
        }

        @Override
        public void initialize() {
            elapsedTime.reset();
        }

        @Override
        public void execute() {
            if (elapsedTime.seconds() < period) {
                subsystem.lift(height1);
            } else {
                subsystem.lift(height2);
            }
            if (elapsedTime.seconds() > 2 * period) {
                elapsedTime.reset();
            }
        }

        @Override
        public boolean isFinished() {
            return false;
        }

    }

}
