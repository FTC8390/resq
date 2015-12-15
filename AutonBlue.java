package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRed extends LinearOpMode {

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

    //drive towards rescue beacon
    moosalot.driveTrain.tankDrive(-.6, -.6);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() > autonFile.driveDistance) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // turn
    int turnTarget = moosalot.driveTrain.rightDrive.getCurrentPosition() + autonFile.turnDistance;
    moosalot.driveTrain.tankDrive(.0, .5);
    while (moosalot.driveTrain.rightDrive.getCurrentPosition() < turnTarget ) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // backup here?




    //dump climbers in place
    while (moosalot.blueDebrisDumper.isDumped() == false) {
      moosalot.blueDebrisDumper.dumpSlowly();
      waitOneFullHardwareCycle();
    }
    sleep(2000);

    moosalot.blueDebrisDumper.collect();
    sleep(2000);
  }
}
