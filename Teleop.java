package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp Mode
 * Enables control of the robot via the gamepad
 */
public class Teleop extends OpMode {

  private RobotResq moosalot;
  Boolean stopAtEndOfMatch;
  private ElapsedTime runTime = new ElapsedTime();
  private Double timeLeft, runTimeDouble;

  @Override
  public void init() {
    moosalot = new RobotResq();
    moosalot.init(hardwareMap);
    stopAtEndOfMatch = true;
  }

  @Override
  public void start() {
    moosalot.start();

    runTime.reset();
  }

  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {

    runTimeDouble = runTime.time();
    timeLeft = 120.5 - runTimeDouble;
    if (timeLeft <= 0)
      timeLeft = 0.0;

    telemetry.clearData();
    telemetry.addData("A: Time Left", String.format("%.2f", timeLeft));

    if ((timeLeft <= 60) && (timeLeft > 40)) {
      telemetry.addData("B1: GO TO MOUNTAIN SOON!!", 0);
      telemetry.addData("B2: PUT POKER OUT WHEN YOU DO!!", 0);
    }

    if ((timeLeft <= 40) && (timeLeft >= 30)) {
      telemetry.addData("C: END GAME IN", String.format("%.2f", timeLeft - 30));
    }
    if ((timeLeft <= 10) && (timeLeft >= 0)) {
      telemetry.addData("C: END OF MATCH IN", String.format("%.2f", timeLeft));
    }

    if((gamepad1.back)&& (gamepad2.back)){
      stopAtEndOfMatch = false;
    }

    if ((timeLeft == 0)&&(stopAtEndOfMatch)) {
      moosalot.stop();
    } else {
      if (gamepad2.b) {
        //moosalot.redDebrisDumper.dump();
        if (moosalot.redDebrisDumper.isDumped() == false) {
          moosalot.redDebrisDumper.dumpSlowly();
        }
      } else if (gamepad1.b) {
        moosalot.redDebrisDumper.redHighDump();
      } else {
        moosalot.redDebrisDumper.collect();
      }


      if (gamepad2.x) {
        //moosalot.blueDebrisDumper.dump();
        if (moosalot.blueDebrisDumper.isDumped() == false) {
          moosalot.blueDebrisDumper.dumpSlowly();
        }
      } else if (gamepad1.x) {
        moosalot.blueDebrisDumper.blueHighDump();
      } else {
        moosalot.blueDebrisDumper.collect();
      }


      if (gamepad1.y) {
        moosalot.driveTrain.faceForward();  //drive facing forward
      }

      if (gamepad1.a) {
        moosalot.driveTrain.faceBackward(); //drive facing backward
      }

      if (gamepad1.dpad_up) {
        moosalot.driveTrain.highSpeed();   //high speed
      }

      if (gamepad1.dpad_down) {
        moosalot.driveTrain.lowSpeed();    //low speed
      }


      if (moosalot.churroHook.isRaised) {
        moosalot.driveTrain.tankDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
      } else {
        moosalot.driveTrain.tankDriveBackwardsOnly(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
      }

      if (gamepad2.left_bumper) {
        moosalot.raiseHookTowardMountain(); //scissor lift up
      }
    /*else if (gamepad2.left_trigger > 0.75) {
      moosalot.dropHookAndRetractLift();  //scissor lift down
    } else {
      moosalot.stopHook();
    }*/
      else {
        if (gamepad2.left_stick_y < -0.75) {
          moosalot.poker.pokeOut();
        } else if (gamepad2.left_stick_y > +0.75) {
          moosalot.poker.pullIn();
        } else {
          moosalot.poker.stop();
        }

        if (gamepad2.right_stick_y < -0.75) {
          moosalot.lift.raise();
        } else if (gamepad2.right_stick_y > +0.75) {
          moosalot.lift.lower();
        } else {
          moosalot.lift.stop();
        }
      }

      if (gamepad2.y || (gamepad1.right_bumper &&(timeLeft<30.0))) {
        moosalot.winch.wind();
      } else {
        moosalot.winch.stop();
      }

      if (gamepad2.dpad_up || gamepad1.left_bumper) {
        moosalot.churroHook.raise();
      }

      if (gamepad2.dpad_right) {
        moosalot.churroHook.prepareForRamp();
      }

      if (gamepad2.dpad_down || (gamepad1.left_trigger > 0.75)) {
        moosalot.churroHook.lower();
      }

      if (gamepad2.right_bumper) {
        moosalot.whiskTrain.pushOutDebris();    //whisk train in
      } else if (gamepad2.right_trigger > 0.75) {
        moosalot.whiskTrain.pullInDebris();
      } else {
        moosalot.whiskTrain.stop();
      }
    }
  }

}
