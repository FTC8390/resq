package ftc8390.resq;

public class AutonCloseRed extends AutonClose {

  @Override
  public void runOpMode() throws InterruptedException {
    autonIsRed = true;
    runAutonomous();
  }
}
