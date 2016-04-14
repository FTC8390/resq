package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ChangeAutonDistances extends OpMode {

  AutonFileHandler autonFile;

  public ChangeAutonDistances() {
    //gamepad1 = new Gamepad(); // to fix bug in 201601 SDK
    //gamepad2 = new Gamepad();
  }

  @Override
  public void init() {
    autonFile = new AutonFileHandler();
    autonFile.readDataFromFile(hardwareMap.appContext);
  }

  @Override
  public void init_loop() {

    telemetry.addData("A delay start by", autonFile.waitTime);
    telemetry.addData("A driveSpeed", autonFile.driveSpeed);
    telemetry.addData("B climberDump", autonFile.climberDump);

    telemetry.addData("Close 1 driveDistance", autonFile.driveDistanceClose);
    telemetry.addData("Close 2 turnDistance", autonFile.turnDistanceClose);
    telemetry.addData("Close 3 backDistance", autonFile.backDistanceClose);

    telemetry.addData("Far 1 driveDistance", autonFile.driveDistanceFar);
    telemetry.addData("Far 2 turnDistance", autonFile.turnDistanceFar);
    telemetry.addData("Far 3 backDistance", autonFile.backDistanceFar);


    //First Drive Distance
    if (gamepad1.y) {
      autonFile.driveDistanceFar -= 2;
    }

    if (gamepad1.a) {
      autonFile.driveDistanceFar += 2;
    }
    // First Turn Distance
    if (gamepad1.dpad_up) {
      autonFile.turnDistanceFar += 2;
    }

    if (gamepad1.dpad_down) {
      autonFile.turnDistanceFar -= 2;
    }
    //First Back Distance
    if (gamepad1.right_bumper) {
      autonFile.backDistanceFar += 2;
    }

    if (gamepad1.right_trigger > .75) {
      autonFile.backDistanceFar -= 2;
    }
    //Wait Time at the start
    if (gamepad1.left_bumper) {
      autonFile.waitTime += 4;
    }

    if (gamepad1.left_trigger > .75) {
      autonFile.waitTime -= 4;
      if (autonFile.waitTime < 0) {
        autonFile.waitTime = 0;
      }
    }
    //First Climber Dump
    if (gamepad1.x) {
      autonFile.climberDump = false;
    }

    if (gamepad1.b) {
      autonFile.climberDump = true;
    }

    if (gamepad1.dpad_left) {
      autonFile.driveSpeed -= .001;
    }

    if (gamepad1.dpad_right) {
      autonFile.driveSpeed += .001;
    }

    if (gamepad2.a) {
      autonFile.driveDistanceClose += 2;
    }
    if (gamepad2.y) {
      autonFile.driveDistanceClose -= 2;
    }

    if (gamepad2.dpad_up) {
      autonFile.turnDistanceClose += 2;
    }
    if (gamepad2.dpad_down) {
      autonFile.turnDistanceClose -= 2;
    }

    if (gamepad2.right_bumper) {
      autonFile.backDistanceClose += 2;
    }
    if (gamepad2.right_trigger > .75) {
      autonFile.backDistanceClose -= 2;
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
