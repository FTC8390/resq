package ftc8390.resq;

public class AutonFarRed extends AutonFar {

  @Override
  public void runOpMode() throws InterruptedException {
    autonIsRed = true;
    runAutonomous();
  }
}
