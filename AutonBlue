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
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > autonFile.driveDistance) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // turn
    int turnTarget = moosalot.driveTrain.leftDrive.getCurrentPosition() + autonFile.turnDistance;
    moosalot.driveTrain.tankDrive(.5, .0);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() < turnTarget ) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    // backup here?




    //dump climbers in place
    while (moosalot.redDebrisDumper.isDumped() == false) {
      moosalot.blueDebrisDumper.dumpSlowly();
      moosalot.redDebrisDumper.dumpSlowly();
      waitOneFullHardwareCycle();
    }
    sleep(2000);

    moosalot.redDebrisDumper.collect();
    moosalot.blueDebrisDumper.collect();
    sleep(2000);
  }
}
