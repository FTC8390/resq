package ftc8390.resq;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class Lift {
    public DcMotor motor;

    public void init(HardwareMap hwMap) {
        motor = hwMap.dcMotor.get("lift");
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

    public void raise() {
        motor.setPower(-0.75);
    }

    public void raiseToTop() {
        if (motor.getCurrentPosition() > -13700) {
            raise(); // do with encoder limits to not break lift!
        } else {
            stop();
        }
    }

    public void lower() {
        motor.setPower(0.75);
    }
    
    public void lowerToBottom() {
        if (motor.getCurrentPosition() <= 0) {
            lower(); // do with encoder limits to not break lift!
        } else {
            stop();
        }
    }

    public boolean isUp() {
        if (motor.getCurrentPosition() < -6500) { // value high enough to drop hook
            return true;
        } else {
            return false;
        }
    }

    public boolean isDown() {
        if (motor.getCurrentPosition() > -50) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHookDropped() {
        if (motor.getCurrentPosition() > -3000) {
            return true;
        } else {
            return false;
        }
    }

}
