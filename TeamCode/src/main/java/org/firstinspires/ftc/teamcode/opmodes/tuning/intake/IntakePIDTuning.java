package org.firstinspires.ftc.teamcode.opmodes.tuning.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

@TeleOp
@Disabled
public class IntakePIDTuning extends CommandOpMode {

    private IntakeSubsystem subsystem;
    public static double period;
    public static double length1;
    public static double length2;

    @Override
    public void initialize() {
        subsystem = new IntakeSubsystem(hardwareMap);
        subsystem.setDefaultCommand(new FlipFlopCommand(subsystem,period, length1, length2));
    }

    private class FlipFlopCommand extends CommandBase {

        private final IntakeSubsystem m_subsystem;
        private double period;
        private ElapsedTime elapsedTime;
        private double length1;
        private double length2;

        public FlipFlopCommand(IntakeSubsystem subsystem, double period, double length1, double length2) {
           m_subsystem = subsystem;
           this.period = period;
           this.length1 = length1;
           this.length2 = length2;
        }

        @Override
        public void initialize() {
            elapsedTime.reset();
        }

        @Override
        public void execute() {
            if (elapsedTime.seconds() < period) {
                subsystem.extendToDistance(length1);
            } else {
                subsystem.extendToDistance(length2);
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
