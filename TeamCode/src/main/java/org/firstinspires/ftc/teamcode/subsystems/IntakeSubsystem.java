package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * I want the code to turn on and turn off the intake as well as extend the intake and allow
 * a joystick to change the distance the intake extends.
 * @author Alex
 */
public class IntakeSubsystem extends SubsystemBase {

    private Motor intakeMotor;
    private Motor liftMotor;

    public static double intakePowerOn = 0.6;
    public static double positionTolerance = 1.0;

    private static double MIN_LENGTH = 0;
    private static double MAX_LENGTH = 0;

    public static SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0, 0);
    public static PIDFController pidf = new PIDFController(0, 0, 0, 0);

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = new MotorEx(hardwareMap, "intake");
        liftMotor = new MotorEx(hardwareMap, "lift");
        liftMotor.setDistancePerPulse(0.015);
        liftMotor.setPositionTolerance(positionTolerance);
    }
    /**
     * Turning on the intake.
     */
    public void turnOn() {
        Log.v("IntakeSubsystem", "Turning on intake");
        intakeMotor.set(intakePowerOn);
    }

    /**
     * Turns off the intake.
     */
    public void  turnOff() {
        Log.v("IntakeSubsystem", "Turning off intake");
        intakeMotor.set(0);
    }

    /**
     * Intake extending to a certain distance.
     */
    public void extendToDistance(double targetDist) {
        Log.v("IntakeSubsystem", "Extending intake to " + targetDist);
        double currentDistance = liftMotor.getCurrentPosition();
        double output = pidf.calculate(
                currentDistance, Range.clip(targetDist, MIN_LENGTH, MAX_LENGTH)
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
        Log.v("IntakeSubsystem", "Stopping intake motor");
        liftMotor.set(0);
    }
    public boolean atPosition(){
        return pidf.atSetPoint();
    }

}