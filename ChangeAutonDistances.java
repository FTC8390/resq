package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

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

    telemetry.addData("Beacon 1 driveDistance", autonFile.driveDistanceBeacon);
    telemetry.addData("Beacon 2 turnDistance", autonFile.turnDistanceBeacon);
    telemetry.addData("Beacon 3 backDistance", autonFile.backDistanceBeacon);
    telemetry.addData("Beacon 4 climberDump", autonFile.climberDump);

    telemetry.addData("Ramp 1 driveDistance", autonFile.driveDistanceRamp);
    telemetry.addData("Ramp 2 turnDistance", autonFile.turnDistanceRamp);
    telemetry.addData("Ramp 3 climbDistance", autonFile.climbDistanceRamp);

    if (gamepad1.y) {
      autonFile.driveDistanceBeacon -= 2;
    }
    
    if (gamepad1.a) {
      autonFile.driveDistanceBeacon += 2;
    }
    
    if (gamepad1.dpad_up) {
      autonFile.turnDistanceBeacon += 2;
    }
    
    if (gamepad1.dpad_down) {
      autonFile.turnDistanceBeacon -= 2;
    }

    if (gamepad1.right_bumper) {
      autonFile.backDistanceBeacon += 2;
    }

    if (gamepad1.right_trigger > .75) {
      autonFile.backDistanceBeacon -= 2;
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

    if (gamepad2.a){
      autonFile.driveDistanceRamp += 2;
    }
    if (gamepad2.y) {
      autonFile.driveDistanceRamp -= 2;
    }

    if (gamepad2.dpad_up){
      autonFile.turnDistanceRamp += 2;
    }
    if(gamepad2.dpad_down){
      autonFile.turnDistanceRamp -= 2;
    }

    if (gamepad2.right_bumper){
      autonFile.climbDistanceRamp += 2;
    }
    if (gamepad2.right_trigger > .75) {
      autonFile.climbDistanceRamp -= 2;
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
