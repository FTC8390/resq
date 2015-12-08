package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp Mode
 * Enables control of the robot via the gamepad
 */
public class Teleop extends OpMode {

  private String startDate;
  private ElapsedTime runtime = new ElapsedTime();

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
    else if (gamepad2.left_trigger > 0.75) {
      moosalot.dropHookAndRetractLift();  //scissor lift down
    } 
    else {
      moosalot.stopHook();
    }
      
    if(gamepad2.y){
      moosalot.winch.windUpMountain();
    } 
    else {
      moosalot.winch.stop();
    }
    
    if (gamepad2.dpad_up){
      moosalot.churroHook.raise();
    }
    
    if (gamepad2.dpad_down){
      moosalot.churroHook.lower();
    }
    
    if (gamepad2.right_bumper){
      moosalot.whiskTrain.pullInDebris();    //whisk train in
    } 
    else if (gamepad2.right_trigger > 0.75){
      moosalot.whiskTrain.pushOutDebris();
    } 
    else {
      moosalot.whiskTrain.stop();
    }
  }
}
