package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Auton1 extends LinearOpMode {

  AutonFileHandler autonFile;
  private RobotResq moosalot;

  @Override
  public void runOpMode() throws InterruptedException {

    moosalot = new RobotResq();
    moosalot.init(hardwareMap);
    waitOneFullHardwareCycle();

    // wait for the start button to be pressed
    waitForStart();
    moosalot.start();
    waitOneFullHardwareCycle();

    // do autonomous stuff here

    //drive towards rescue beacon
    moosalot.driveTrain.tankDrive(-.75, -.75);
    while (moosalot.driveTrain.leftDrive.getCurrentPosition() > -9500) {
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);

    //dump climbers in place
    moosalot.blueDebrisDumper.dump();
    moosalot.redDebrisDumper.dump();
    sleep(2000);
    moosalot.redDebrisDumper.collect();
    moosalot.blueDebrisDumper.collect();
    sleep(2000);


  }
}
