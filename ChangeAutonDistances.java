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
    telemetry.addData("driveDistanceBeacon", autonFile.driveDistanceBeacon);
    telemetry.addData("turnDistanceBeacon", autonFile.turnDistanceBeacon);
    telemetry.addData("backDistanceBeacon", autonFile.backDistanceBeacon);
    telemetry.addData("waitTime", autonFile.waitTime);
    telemetry.addData("climberDump", autonFile.climberDump);
    telemetry.addData("driveDistanceRamp", autonFile.driveDistanceRamp);
    telemetry.addData("turnDistanceRamp", autonFile.turnDistanceRamp);
    telemetry.addData("climbDistanceRamp", autonFile.climbDistanceRamp);
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
