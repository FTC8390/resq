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

    // do autonomous stuff here

    sleep(autonFile.waitTime);

    //drive towards rescue beacon
    moosalot.driveTrain.tankDrive(-.5, -.5);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > autonFile.driveDistanceBeacon) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // turn
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.turnDistanceBeacon;
    moosalot.driveTrain.tankDrive(.0, .5);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < turnTarget) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // backup
    int backTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.backDistanceBeacon;
    moosalot.driveTrain.tankDrive(.5, .5);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < backTarget) {
      waitOneFullHardwareCycle();
    }
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
