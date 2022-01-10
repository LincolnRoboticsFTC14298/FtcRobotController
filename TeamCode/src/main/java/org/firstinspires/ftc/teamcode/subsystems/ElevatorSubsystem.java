package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

    private ServoEx dumperServo;
    private Motor liftMotor;

    private static double MIN_ANGLE = 0;
    private static double MAX_ANGLE = 90;

    public static double kG = 0.1;

    public static double distancePerPulse = 0.015;

    public static double positionTolerance = 1.0;

    public static SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0, 0);
    public static PIDFController pidf = new PIDFController(0, 0, 0, 0);

    public ElevatorSubsystem(HardwareMap hardwareMap) {
        dumperServo = new SimpleServo(hardwareMap,"servo",MIN_ANGLE,MAX_ANGLE) {
        };
        liftMotor = new MotorEx(hardwareMap, "lift");
        liftMotor.setDistancePerPulse(distancePerPulse);

        liftMotor.setPositionTolerance(positionTolerance);
    }

    /**
     * Dumps elements from container.
      */
    public void dump() {
        dumperServo.setPosition(MAX_ANGLE);
    }

    /**
     * Retracts dumper servo.
     */
    public void retract() {
        dumperServo.setPosition(MIN_ANGLE);
    }

    /**
     * Lifts elevator to preset heights.
     * @param height Height in inches
     */
    public void lift(double height) {
        double currentDistance = liftMotor.getCurrentPosition();
        double output = pidf.calculate(
                currentDistance, height
        ) + kG;
        liftMotor.set(output);
    }

    /**
     * Lifts elevator to preset heights.
     * @param height Preset height values.
     * @see Height
     */
    public void lift(Height height) {
        lift(height.getHeight());
    }

    public void liftJoystick(double y) {
        double output = feedforward.calculate(y, 0) + kG;
        liftMotor.set(output);
    }

    /**
    *Sets lift to default power (gravity)
     */
    public void defaultPower() {
        liftMotor.set(kG);
    }

    /**
     * Returns whether lift is close enough to desired position.
     */
    public boolean atPosition() {
        return pidf.atSetPoint();
    }

}
