package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.elevator.LiftAndHalfDumpCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeJoystickCommand;
import org.firstinspires.ftc.teamcode.commands.mecanum.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheelSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.mecanum.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.mecanum.SampleMecanumDrive;

@TeleOp
public class MainTeleOp extends CommandOpMode {

    // public ExampleIntakeSubsystem intakeSubsystem;

    public static double intakeActivationYPosition = 0;

    public SampleMecanumDrive drive;
    public MecanumDriveSubsystem mecanumDriveSubsystem;
    public DuckWheelSubsystem duckWheelSubsystem;
    public ElevatorSubsystem elevatorSubsystem;
    public IntakeSubsystem intakeSubsystem;

    private GamepadEx gamepad_1;
    private GamepadEx gamepad_2;


    /**
    * Returns whether the robot is within the range to activate the intake.
     */
    public boolean inIntakeActivationRange() {
        //TODO: Find constant intakeActivationYPosition
        return drive.getPoseEstimate().getX() > intakeActivationYPosition;
    }

    public void initialize() {

        drive = new SampleMecanumDrive(hardwareMap);
        //TODO: make isFieldCentric toggle
        mecanumDriveSubsystem = new MecanumDriveSubsystem(drive,false);
        duckWheelSubsystem = new DuckWheelSubsystem(hardwareMap);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        gamepad_1 = new GamepadEx(gamepad1);
        gamepad_2 = new GamepadEx(gamepad2);

//        gamepad.getGamepadButton(GamepadKeys.Button.B)
//                .whenPressed(new ConditionalCommand(
//                        new InstantCommand(intake::turnOn, intake),
//                        new InstantCommand(intake::turnOff, intake),
//                        () -> !intake.isActive()
//                ));

        gamepad_1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(duckWheelSubsystem::turnOn, duckWheelSubsystem))
                .whenReleased(new InstantCommand(duckWheelSubsystem::turnOff, duckWheelSubsystem));


        elevatorSubsystem.setDefaultCommand(new InstantCommand(elevatorSubsystem::defaultPower, elevatorSubsystem));

        gamepad_1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(generateLiftRunnable(ElevatorSubsystem.Height.TOP));
        gamepad_1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(generateLiftRunnable(ElevatorSubsystem.Height.MIDDLE));
        gamepad_1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(generateLiftRunnable(ElevatorSubsystem.Height.LOW));

        mecanumDriveSubsystem.setDefaultCommand(new MecanumDriveCommand(mecanumDriveSubsystem, () -> gamepad_1.getLeftY(), () -> gamepad_1.getLeftX(), () -> gamepad_1.getRightX()));


        intakeSubsystem.setDefaultCommand(new ConditionalCommand(
                new IntakeJoystickCommand(intakeSubsystem,() -> gamepad_2.getLeftY()),
                new InstantCommand(intakeSubsystem::turnOff, intakeSubsystem),
                () -> inIntakeActivationRange()
        ));
    }

    public Runnable generateLiftRunnable(ElevatorSubsystem.Height height) {
        return () -> gamepad_1.getGamepadButton(GamepadKeys.Button.X).whenPressed(new LiftAndHalfDumpCommand(elevatorSubsystem,height));
    }


}
