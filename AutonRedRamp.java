package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRedRamp extends LinearOpMode {

  private RobotResq moosalot;

  @Override
  public void runOpMode() throws InterruptedException {
    AutonFileHandler autonFile;
    autonFile= new AutonFileHandler();
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
    moosalot.driveTrain.tankDrive(-.6, -.6);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > autonFile.autonRampDistance) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // turn
    int turnTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() + autonFile.autonRampTurn;
    moosalot.driveTrain.tankDrive(-.6, 0);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > turnTarget ) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // backup
    int backTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() + autonFile.autonRampClimb;
    moosalot.driveTrain.tankDrive(-.6, -.6);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > backTarget ) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

  }
}
