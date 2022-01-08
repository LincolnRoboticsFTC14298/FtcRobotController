package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Turns on and set the motor at a certain speed so is able to move the duck wheel
 * @author Bryan
 */
public class DuckWheelSubsystem extends SubsystemBase {

    public static double powerOn = 1;

    private Motor duckWheelMotor;

    public DuckWheelSubsystem(HardwareMap hardwareMap) {
        //create motor
        duckWheelMotor = new MotorEx(hardwareMap, "duckWheel");
        duckWheelMotor.setRunMode(Motor.RunMode.VelocityControl);
    }

    /**
     * Turns on the duck wheel
     */
    public void turnOn() {
        duckWheelMotor.set(powerOn);
    }
    /**
     * Turns off the duck wheel
     */
    public void turnOff() {
        duckWheelMotor.set(0);
    }

}
