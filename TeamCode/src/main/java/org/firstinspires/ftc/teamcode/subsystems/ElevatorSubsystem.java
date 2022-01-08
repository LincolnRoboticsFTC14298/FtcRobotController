package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

/**
 * This subsystem controls the elevator and dump.
 * @author Cole
 **/

public class ElevatorSubsystem extends SubsystemBase {

    /**
     * Predefined height levels for the elevator.
     */
    public enum Height {
        BOTTOM(0),
        MIDDLE(0),
        TOP(0);

        private double height;

        Height(double height) {
            this.height = height;
        }

        public double getHeight() {
            return height;
        }

    }

    /**
     * Dumps elements from container.
      */
    public void dump() {

    }

    /**
     * Retracts dumper servo.
     */
    public void retract() {

    }

    /**
     * Lifts elevator to preset heights.
     * @param height Height in inches
     */
    public void lift(double height) {

    }

    /**
     * Lifts elevator to preset heights.
     * @param height Preset height values.
     * @see Height
     */
    public void lift(Height height) {
        lift(height.getHeight());
    }
}
