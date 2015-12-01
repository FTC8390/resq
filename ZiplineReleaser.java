package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class ZiplineReleaser {

  Servo servo;
  boolean isRed;

  double position=0.5;

  double extendValue, retractValue;

  public void init(HardwareMap hwMap, boolean isRedIn) {
    isRed = isRedIn;
    if (isRed) {
      servo = hwMap.servo.get("redzip");
      extendValue = 0.25;
      retractValue = 0.75;
    } else {
      servo = hwMap.servo.get("bluezip");
      extendValue = 0.75;
      retractValue = 0.25;
    }

    position = retractValue;
    servo.setPosition(position);
  }

  public void start() {
  }

  public double changePositionBy(double amount) {
    position = Range.clip(position + amount, 0, 1);
    servo.setPosition(position);
    return position;
  }

  public void extend() {

  }

  public void retract() {

  }
}
