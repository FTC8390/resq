package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonBlueRamp extends LinearOpMode {

  private RobotResq moosalot;

  @Override
  public void runOpMode() throws InterruptedException {
    AutonFileHandler autonFile;
    autonFile = new AutonFileHandler();
    autonFile.readDataFromFile(hardwareMap.appContext);

    moosalot = new RobotResq();
    moosalot.init(hardwareMap);
    waitOneFullHardwareCycle();

    // wait for the start button to be pressed
    waitForStart();
    moosalot.start();

    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();

    // do autonomous stuff here

    sleep(autonFile.waitTime);

    //drive towards rescue beacon
    moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > autonFile.driveDistanceClose) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // turn
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.turnDistanceClose;
    moosalot.driveTrain.tankDrive(0, autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > turnTarget) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // backup
    int backTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.backDistanceClose;
    moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > backTarget) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

  }
}

