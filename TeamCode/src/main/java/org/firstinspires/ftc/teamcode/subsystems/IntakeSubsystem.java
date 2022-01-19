package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.HardwareDevice;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * I want the code to turn on and turn off the intake as well as extend the intake and allow
 * a joystick to change the distance the intake extends.
 * @author Alex
 */
public class IntakeSubsystem extends SubsystemBase {

    private Motor intakeMotor;
    private Motor liftMotor;

    public static double intakePowerOn = 0.6;

    public static SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0, 0);
    public static PIDFController pidf = new PIDFController(0, 0, 0, 0);

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = new MotorEx(hardwareMap, "intake");
        liftMotor = new MotorEx(hardwareMap, "lift");
        liftMotor.setDistancePerPulse(0.015);
    }
    /**
     * Turning on the intake.
     */
    public void turnOn() {
        intakeMotor.set(intakePowerOn);
    }

    /**
     * Turns off the intake.
     */
    public void  turnOff() {
        intakeMotor.set(0);
    }

    /**
     * Intake extending to a certain distance.
     */
    public void extendToDistance(double targetDist) {
        double currentDistance = liftMotor.getCurrentPosition();
        double output = pidf.calculate(
                currentDistance, targetDist
        );
        liftMotor.set(output);
    }

    /**
     * The range of the intake is controlled by a joystick.
     */
    public void extendJoystick(double y) {
        double output = feedforward.calculate(y, 0);
        liftMotor.set(output);
    }

    /**
     * intake distance command, joystick distance command.
     */

    public void stopMotor(){
        liftMotor.set(0);
    }

}