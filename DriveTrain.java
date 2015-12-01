package ftc8390.resq;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class DriveTrain {
  DcMotor leftDrive, rightDrive;
  boolean isFacingForward;
  double speed;

  public void init(HardwareMap hwMap) {
    isFacingForward=true;
    speed=1.0;

    leftDrive = hwMap.dcMotor.get("leftdrive");
    rightDrive = hwMap.dcMotor.get("rightdrive");

    leftDrive.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    rightDrive.setMode(DcMotorController.RunMode.RESET_ENCODERS);
  }

  public void start() {
    leftDrive.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    rightDrive.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
  }

  public void runLeftAtPower(double power) {
    leftDrive.setPower(Range.clip(power, -1, 1));
  }

  public void runRightAtPower(double power) {
    rightDrive.setPower(Range.clip(power, -1, 1));
  }
  public void stop() {
    leftDrive.setPower(0);
    rightDrive.setPower(0);
  }

  public void tankDrive(double left, double right) {

  }
  
  public void faceForward() {
      
  }
  
  public void faceBackward() {
      
  }
  
  public void highSpeed() {
      
  }
  
  public void lowSpeed() {
      
  }
}
