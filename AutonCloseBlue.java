package ftc8390.resq;

public class AutonCloseBlue extends AutonClose {

  @Override
  public void runOpMode() throws InterruptedException {
    autonIsRed = false;
    runAutonomous();
  }
}
