package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class TouchSensorTesting extends LinearOpMode {

    RevTouchSensor digitalTouch;  // Hardware Device Object


    public void runOpMode() {

        // get a reference to our digitalTouch object.
        digitalTouch = hardwareMap.get(RevTouchSensor.class, "sensor_digital");

        waitForStart();

        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.
            if (digitalTouch.isPressed()) {
                telemetry.addData("Digital Touch", "Is Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            }

            telemetry.update();
        }
    }

}
