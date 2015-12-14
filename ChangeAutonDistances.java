package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ChangeAutonDistances extends OpMode {

  AutonFileHandler autonFile;

  @Override
  public void init() {
    autonFile = new AutonFileHandler();
    autonFile.readDataFromFile(hardwareMap.appContext);
  }

  @Override
  public void init_loop() {
    telemetry.addData("driveDistance", autonFile.driveDistance);
    telemetry.addData("turnDistance", autonFile.turnDistance);
    
    if (gamepad1.y) {
      autonFile.driveDistance -= 2;
    }
    
    if (gamepad1.a) {
      autonFile.driveDistance += 2;
    }
    
    if (gamepad1.dpad_up) {
      autonFile.turnDistance += 2;
    }
    
    if (gamepad1.dpad_down) {
      autonFile.turnDistance -= 2;
    }

  }

  @Override
  public void start() {
    autonFile.writeDataToFile(hardwareMap.appContext);
  }

  @Override
  public void loop() {

  }
}
