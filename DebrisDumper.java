package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class DebrisDumper {
  public Servo servo;
  boolean isRed;

  double position=0.5;

  double dumpValue, collectValue;

  public void init(HardwareMap hwMap, boolean isRedIn) {
    isRed = isRedIn;
    if (isRed) {
      servo = hwMap.servo.get("reddd");
      dumpValue = 0.062;
      collectValue = 0.88;
    } else {
      servo = hwMap.servo.get("bluedd");
      dumpValue = 0.928;
      collectValue = 0.094;
    }

    position = collectValue;
    servo.setPosition(position);
  }

  public void start() {
  }

  public void changePositionBy(double amount) {
    position = Range.clip(position + amount, 0, 1);
    servo.setPosition(position);
  }

  public void dump() {
    position = dumpValue;
    servo.setPosition(position);
  }

  public void collect() {
    position = collectValue;
    servo.setPosition(position);
  }
  
}
