package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Example subsystem that will be used on the robot.
 *
 * @author Jared
 */
public class ExampleIntakeSubsystem extends SubsystemBase {

    private MotorEx intakeMotor; // define the motor
    // name of the motor as a fixed string (it never changes)
    private static final String intakeMotorName = "intakeMotor";

    // define the power of the intake as a variable we can later tune
    public static double powerOn = .7;
    private boolean active = false;

    public ExampleIntakeSubsystem(HardwareMap hardwareMap) {
        // this.register(); Only needed when there will be a default command or it has a periodic
        intakeMotor = new MotorEx(hardwareMap, intakeMotorName);
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
    }

    /**
     * Turns on the intake.
     */
    public void turnOn() {
        intakeMotor.set(powerOn);
        active = true;
    }

    /**
     * Turns off the intake.
     */
    public void turnOff() {
        intakeMotor.stopMotor();
        active = false;
    }

    /**
     * Gets if intake is active.
     */
    public boolean isActive() {
        return active;
    }

}
