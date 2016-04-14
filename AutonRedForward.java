package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRedForward extends LinearOpMode {

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

    double straighten = 1.01;

    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();
    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();

    // do autonomous stuff here

    sleep(autonFile.waitTime);

    // drive forward and whisk out

    moosalot.driveTrain.tankDrive(-autonFile.driveSpeed, -autonFile.driveSpeed * straighten);
    moosalot.whiskTrain.pushOutDebris();
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() < -autonFile.driveDistanceFar) {
      moosalot.driveTrain.tankDrive(-autonFile.driveSpeed, -autonFile.driveSpeed * straighten);
      moosalot.whiskTrain.pushOutDebris();
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    moosalot.whiskTrain.stop();
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    moosalot.whiskTrain.stop();
    sleep(500);


    // turn 135 degrees

    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() - 3 * autonFile.turnDistanceFar;
    moosalot.driveTrain.tankDrive(0, autonFile.driveSpeed);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > turnTarget) {
      moosalot.driveTrain.tankDrive(0, autonFile.driveSpeed);
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // move forward (later put in light sensor check?)

    int forwardTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() - autonFile.backDistanceFar;
    moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed * straighten);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > forwardTarget) {
      moosalot.driveTrain.tankDrive(autonFile.driveSpeed, autonFile.driveSpeed * straighten);
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    waitOneFullHardwareCycle();
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(500);

    // dump
    if (autonFile.climberDump == true) {
      while (moosalot.redDebrisDumper.isDumped() == false) {
        moosalot.redDebrisDumper.dumpSlowly();
        waitOneFullHardwareCycle();
      }
      sleep(1000);

      moosalot.redDebrisDumper.collect();
      sleep(1000);
    }


  }
}
