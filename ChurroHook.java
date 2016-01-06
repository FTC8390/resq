package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class ChurroHook {

  public Servo servo;

  public double position = 0.256;

  public void init(HardwareMap hwMap) {
    servo = hwMap.servo.get("churro");
    servo.setPosition(position);
  }

  public void start() {
  }

  public double changePositionBy(double amount) {
    position = Range.clip(position + amount, 0, 1);
    servo.setPosition(position);
    return position;
  }

  public void raise() {
    position = 0.6;
    servo.setPosition(position);
  }

  public void lower() {
    position = 0.256;
    servo.setPosition(position);
  }

  public void prepareForRamp() {
    position = 0.508;
    servo.setPosition(position);
  }
}
