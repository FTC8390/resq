package ftc8390.resq;

public abstract class AutonClose extends ResqLinearOpMode {
  public boolean autonIsRed = true;

  public void runAutonomous() throws InterruptedException {
    DebrisDumper autonDumper;

    AutonFileHandler autonFile;
    autonFile = new AutonFileHandler();
    autonFile.readDataFromFile(hardwareMap.appContext);

    initialize();

    // wait for the start button to be pressed
    waitForStart();
    moosalot.start();

    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();
    moosalot.driveTrain.setModeToRunUsingEncoders();
    waitOneFullHardwareCycle();

    // do autonomous stuff here

    sleep(autonFile.waitTime);

    // drive forward and whisk out
    moosalot.whiskTrain.pushOutDebris();
    driveForwardAtSpeedForEncoderCounts(autonFile.driveSpeed, autonFile.driveDistanceClose);
    stopDriving();
    moosalot.whiskTrain.stop();

    sleep(500);


    if (autonIsRed) {
      turnCWUsingBothMotorsAtSpeedForEncoderCounts(.7, autonFile.turnDistanceClose);
    } else {
      turnCCWUsingBothMotorsAtSpeedForEncoderCounts(.7, autonFile.turnDistanceClose);
    }
    stopDriving();
    sleep(500);

    driveBackwardAtSpeedForEncoderCounts(autonFile.driveSpeed, autonFile.backDistanceClose);
    stopDriving();


    // dump
    if (autonIsRed) {
      autonDumper = moosalot.redDebrisDumper;
    } else {
      autonDumper = moosalot.blueDebrisDumper;
    }

    if (autonFile.climberDump == true) {
      while (autonDumper.isDumped() == false) {
        autonDumper.dumpSlowly();
        waitOneFullHardwareCycle();
      }
      sleep(1000);

      autonDumper.collect();
      sleep(1000);
    }

    driveForwardAtSpeedForEncoderCounts(autonFile.driveSpeed, autonFile.backDistanceClose);
    stopDriving();

  }
}
