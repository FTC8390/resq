package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class DebrisDumper {
  Servo servo;
  boolean isRed;

  double position=0.5;

  double dumpValue, collectValue;

  public void init(HardwareMap hwMap, boolean isRedIn) {
    isRed = isRedIn;
    if (isRed) {
      servo = hwMap.servo.get("reddd");
      dumpValue = 0.25;
      collectValue = 0.75;
    } else {
      servo = hwMap.servo.get("bluedd");
      dumpValue = 0.75;
      collectValue = 0.25;
    }

    position = collectValue;
    servo.setPosition(position);
  }

  public void start() {
  }

  public double changePositionBy(double amount) {
    position = Range.clip(position + amount, 0, 1);
    servo.setPosition(position);
    return position;
  }

  public void dumpleft() {

  }
  
  public void dumpright(){
    
  }

  public void collect() {

  }
  
  public void spit(){ //prevents balls from entering the whisk train
    
  }
}
