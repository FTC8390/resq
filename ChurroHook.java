package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class ChurroHook {

  public Servo servoLeft;
  public Servo servoRight;

  public double positionLeft = 0.256;
  public double positionRight = 0.256;

  public void init(HardwareMap hwMap) {
    servoLeft = hwMap.servo.get("churroL");
    servoRight = hwMap.servo.get("churroR");
    raise();
  }

  public void start() {
  }

  public void changePositionBy(double amount) {
    positionLeft = Range.clip(positionLeft + amount, 0, 1);
    servoLeft.setPosition(positionLeft);
    positionRight = Range.clip(positionRight + amount, 0, 1);
    servoRight.setPosition(positionRight);
  }

  public void raise() {
    positionLeft = 0.66;
    servoLeft.setPosition(positionLeft);
    positionRight = 0.332;
    servoRight.setPosition(positionRight);
  }

  public void lower() {
    positionLeft = 0.332;
    servoLeft.setPosition(positionLeft);
    positionRight = 0.66;
    servoRight.setPosition(positionRight);
  }

  public void prepareForRamp() {
    raise();
  }
}
