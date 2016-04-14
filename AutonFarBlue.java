package ftc8390.resq;

public class AutonFarBlue extends AutonFar {

  @Override
  public void runOpMode() throws InterruptedException {
    autonIsRed = false;
    runAutonomous();
  }
}
