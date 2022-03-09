package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Log;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

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
        LOW(0),
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

    private RevTouchSensor safetyButton;

    private static double MIN_ANGLE = 0;
    private static double HALF_ANGLE = 45;
    private static double MAX_ANGLE = 90;

    private static double MIN_HEIGHT = 0;
    private static double MAX_HEIGHT = 0;

    public static double kG = 0.1;

    public static double distancePerPulse = 0.015;

    public static double positionTolerance = 1.0;

    public static SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0,0,0);
    public static PIDFController pidf = new PIDFController(0,0,0,0);

    public ElevatorSubsystem(HardwareMap hardwareMap) {
        setName("Elevator");
        dumperServo = new SimpleServo(hardwareMap,"servo",MIN_ANGLE,MAX_ANGLE) {
        };
        liftMotor = new MotorEx(hardwareMap, "lift");
        liftMotor.setDistancePerPulse(distancePerPulse);
        liftMotor.setPositionTolerance(positionTolerance);
        safetyButton = hardwareMap.get(RevTouchSensor.class, "sensor_lift");
    }

    /**
     * Dumps elements from container.
      */
    public void dump() {
        Log.v(getName(),"Dumping");
        dumperServo.setPosition(MAX_ANGLE);
    }

    /**
     * Moves servo to a "half dump" position.
     */
    public void halfDump() {
        Log.v(getName(),"Half-dumping");
        dumperServo.setPosition(HALF_ANGLE);
    }

    /**
     * Retracts dumper servo.
     */
    public void retract() {
        Log.v(getName(),"Retracting dumper");
        dumperServo.setPosition(MIN_ANGLE);
    }

    /**
     * Checks if safety switch is pressed.
     */
    public void checkButton() {
        if (safetyButton.isPressed()) {
            liftMotor.set(kG - 0.05);
        }
    }

    /**
     * Lifts elevator to a height.
     * @param height Height in ticks.
     */
    public void lift(double height) {
        Log.v(getName(),"Lifting elevator to " + height);
        double output = pidf.calculate(
                liftMotor.getDistance(), Range.clip(height,MIN_HEIGHT,MAX_HEIGHT)
        ) + kG;
        liftMotor.set(output);
        checkButton();
    }

    /**
     * Lifts elevator to preset heights.
     * @param height Preset height values.
     * @see Height
     */
    public void lift(Height height) {
        lift(height.getHeight());
    }

    /**
     * Lifts elevator from joystick input
     * @param y Joystick Y input.
     */
    public void liftJoystick(double y) {
        if (liftMotor.getDistance() >= MAX_HEIGHT && y >= 0) {
            liftMotor.set(0);
        } else {
            double output = feedforward.calculate(y, 0) + kG;
            liftMotor.set(output);
            checkButton();
        }
    }

    /**
    *Sets lift to default power (gravity)
     */
    public void defaultPower() {
        Log.v(getName(),"Setting elevator to default power");
        liftMotor.set(kG);
    }

    /**
     * Returns whether lift is close enough to desired position.
     */
    public boolean atPosition() {
        return pidf.atSetPoint();
    }

}
