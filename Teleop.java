package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * TeleOp Mode
 * Enables control of the robot via the gamepad
 */
public class Teleop extends OpMode {

  private RobotResq moosalot;

  @Override
  public void init() {
    moosalot = new RobotResq();
    moosalot.init(hardwareMap);
  }

  @Override
  public void start() {
    moosalot.start();
  }

  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {

    if (gamepad2.b) {
      //moosalot.redDebrisDumper.dump();
      if (moosalot.redDebrisDumper.isDumped() == false) {
        moosalot.redDebrisDumper.dumpSlowly();
      }
    } else if (gamepad1.b){
      moosalot.redDebrisDumper.redHighDump();
    } else {
      moosalot.redDebrisDumper.collect();
    }


    if (gamepad2.x) {
      //moosalot.blueDebrisDumper.dump();
      if (moosalot.blueDebrisDumper.isDumped() == false) {
        moosalot.blueDebrisDumper.dumpSlowly();
      }
    } else if (gamepad1.x){
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



    moosalot.driveTrain.tankDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

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

    if (gamepad2.y) {
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
