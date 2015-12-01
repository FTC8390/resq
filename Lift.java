package ftc8390.resq;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class Lift {
  DcMotor motor;

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

  }

  public void lower() {

  }
}
