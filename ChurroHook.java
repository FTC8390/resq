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
    servoLeft.setPosition(positionLeft);
    servoRight = hwMap.servo.get("churroR");
    servoRight.setPosition(positionRight);
  }

  public void start() {
  }

  public double changePositionBy(double amount) {
    positionLeft = Range.clip(positionLeft + amount, 0, 1);
    servoLeft.setPosition(positionLeft);
    positionRight = Range.clip(positionRight + amount, 0, 1);
    servoRight.setPosition(positionRight);
    return positionRight;
  }

  public void raise() {
    positionLeft = 0.6;
    servoLeft.setPosition(positionLeft);
    positionRight = 0.256;
    servoRight.setPosition(positionRight);
  }

  public void lower() {
    positionLeft = 0.256;
    servoLeft.setPosition(positionLeft);
    positionRight = 0.6;
    servoRight.setPosition(positionRight);
  }

  public void prepareForRamp() {
    raise();
  }
}
