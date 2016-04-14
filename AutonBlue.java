package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonBlue extends LinearOpMode {

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
    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();

    // do autonomous stuff here

    sleep(autonFile.waitTime);

    //drive towards rescue beacon
    moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > autonFile.driveDistanceFar) {
      moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed);
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // turn
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.turnDistanceFar;
    moosalot.driveTrain.tankDrive(.0, -autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < turnTarget) {
      moosalot.driveTrain.tankDrive(.0, -autonFile.driveSpeed);
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // backup
    int backTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.backDistanceFar;
    moosalot.driveTrain.tankDrive(-autonFile.driveSpeed, -autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < backTarget) {
      moosalot.driveTrain.tankDrive(-autonFile.driveSpeed, -autonFile.driveSpeed);
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    //dump climbers in place
    if (autonFile.climberDump == true) {
      while (moosalot.blueDebrisDumper.isDumped() == false) {
        moosalot.blueDebrisDumper.dumpSlowly();
        waitOneFullHardwareCycle();
      }
      sleep(1000);

      moosalot.blueDebrisDumper.collect();
      sleep(1000);
    }
  }
}
