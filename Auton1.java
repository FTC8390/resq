package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Auton1 extends LinearOpMode {

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

  }
}
