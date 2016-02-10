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
  boolean isDumpSmall;

  double position=0.5;

  double dumpValue, collectValue;

  public void init(HardwareMap hwMap, boolean isRedIn) {
    isRed = isRedIn;
    if (isRed) {
      servo = hwMap.servo.get("reddd");
      dumpValue = 0.062;
      isDumpSmall=true;
      collectValue = 0.88;
    } else {
      servo = hwMap.servo.get("bluedd");
      dumpValue = 0.928;
      isDumpSmall=false;
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

  public void blueHighDump(){
    position = .713;
    servo.setPosition(position);
  }
  public void redHighDump(){
    position = .277;
    servo.setPosition(position);
  }

  public void dump() {
    position = dumpValue;
    servo.setPosition(position);
  }
  public void dumpSlowly() {
    double dumpChange=0.085;
    if(isDumpSmall){
      changePositionBy(-dumpChange);
    }
    else{
      changePositionBy(dumpChange);
    }
  }

  public boolean isDumped(){
    if(isDumpSmall){
      if(position<=dumpValue)
        return true;
      else
        return false;
    }else{
      if(position>=dumpValue)
        return true;
      else
        return false;
    }

  }

  public void collect() {
    position = collectValue;
    servo.setPosition(position);
  }
  
}
