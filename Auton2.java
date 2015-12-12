package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Auton2 extends LinearOpMode {

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
    while(moosalot.driveTrain.leftDrive.getCurrentPosition()> -9500){
      waitOneFullHardwareCycle();
    }
    moosalot.driveTrain.tankDrive(0, 0);
    sleep(1000);
    
    //dump climbers in place
    while(moosalot.redDebrisDumper.isDumped()==false) {
      moosalot.blueDebrisDumper.dumpSlowly();
      moosalot.redDebrisDumper.dumpSlowly();
      waitOneFullHardwareCycle();
    }
    moosalot.redDebrisDumper.collect();
    moosalot.blueDebrisDumper.collect();
    sleep(2000);



  }
}