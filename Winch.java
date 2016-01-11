package ftc8390.resq;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class Winch {
    public DcMotor motor;

    public void init(HardwareMap hwMap) {
        motor = hwMap.dcMotor.get("winch");
        motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void start() {
        motor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void runAtPower(double power) {
        motor.setPower(Range.clip(power, -1, 1));
    }

    public void stop() {
        motor.setPower(0);
    }

    public void wind() {
        motor.setPower(1.0);
    }

    public void windUpMountain() {
        wind();
    }

    public void unwind() {
        // do at low power, to not fry motor or break ratchet!
        motor.setPower(-0.15);

    }
}
