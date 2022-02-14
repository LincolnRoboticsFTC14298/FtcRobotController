package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class TouchSensorTesting extends CommandOpMode {

    DigitalChannel digitalTouch;  // Hardware Device Object

    public void initialize() {

        // get a reference to our digitalTouch object.
        digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");

        // set the digital channel to input.
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.
            if (digitalTouch.getState() == true) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Pressed");
            }

            telemetry.update();
        }
    }

}
