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
    telemetry.addData("backDistance", autonFile.backDistance);
    telemetry.addData("waitTime", autonFile.waitTime);
    telemetry.addData("climberDump", autonFile.climberDump);

    
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

    if (gamepad1.right_bumper) {
      autonFile.backDistance += 2;
    }

    if (gamepad1.right_trigger > .75) {
      autonFile.backDistance -= 2;
    }
    if ( gamepad1.left_bumper) {
      autonFile.waitTime += 4;
    }
    if (gamepad1.left_trigger > .75) {
      autonFile.waitTime -= 4;
      if (autonFile.waitTime<0) {
        autonFile.waitTime=0;
      }
    }
    if (gamepad1.x) {
      autonFile.climberDump = false;
    }
    if (gamepad1.b) {
      autonFile.climberDump = true;
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
