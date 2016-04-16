package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class ResqLinearOpMode extends LinearOpMode {

  public RobotResq moosalot;
  public double straighten = 1.01;

  public void initialize() throws InterruptedException{
    moosalot = new RobotResq();
    moosalot.init(hardwareMap);
    waitOneFullHardwareCycle();
    straighten = 1.01;
  }

  public void driveForwardAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {

    int target = moosalot.driveTrain.leftDrive.getCurrentPosition() + encoderCounts;
    double straighten = 1.01;
    moosalot.driveTrain.tankDrive(speed, speed * straighten);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() < target) {
      moosalot.driveTrain.tankDrive(speed, speed * straighten);
      waitOneFullHardwareCycle();
    }

  }

  public void turnForwardUsingLeftMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() + encoderCounts;
    moosalot.driveTrain.tankDrive(speed, 0);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() < turnTarget) {
      moosalot.driveTrain.tankDrive(speed, 0);
      waitOneFullHardwareCycle();
    }
  }

  public void turnBackwardUsingLeftMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() - encoderCounts;
    moosalot.driveTrain.tankDrive(-speed, 0);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > turnTarget) {
      moosalot.driveTrain.tankDrive(-speed, 0);
      waitOneFullHardwareCycle();
    }
  }

  public void turnForwardUsingRightMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + encoderCounts;
    moosalot.driveTrain.tankDrive(0, speed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < turnTarget) {
      moosalot.driveTrain.tankDrive(0, speed);
      waitOneFullHardwareCycle();
    }
  }

  public void turnBackwardUsingRightMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() - encoderCounts;
    moosalot.driveTrain.tankDrive(0, -speed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > turnTarget) {
      moosalot.driveTrain.tankDrive(0, -speed);
      waitOneFullHardwareCycle();
    }
  }

  public void turnCWUsingBothMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() - encoderCounts;
    moosalot.driveTrain.tankDrive(speed, -speed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > turnTarget) {
      moosalot.driveTrain.tankDrive(speed, -speed);
      waitOneFullHardwareCycle();
    }
  }

  public void turnCCWUsingBothMotorsAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + encoderCounts;
    moosalot.driveTrain.tankDrive(-speed, speed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < turnTarget) {
      moosalot.driveTrain.tankDrive(-speed, speed);
      waitOneFullHardwareCycle();
    }
  }

  public void stopDriving() throws InterruptedException {
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
  }


  public void driveBackwardAtSpeedForEncoderCounts(double speed, int encoderCounts) throws InterruptedException {

    int target = moosalot.driveTrain.leftDrive.getCurrentPosition() - encoderCounts;
    double straighten = 1.01;
    moosalot.driveTrain.tankDrive(-speed, -speed * straighten);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > target) {
      moosalot.driveTrain.tankDrive(-speed, -speed * straighten);
      waitOneFullHardwareCycle();
    }

  }


}
