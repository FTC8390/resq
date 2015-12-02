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

    if (gamepad2.left_bumper) {
      moosalot.raiseHookTowardMountain(); //scissor lift up
    }

    if (gamepad2.left_trigger > 0.75) {
      moosalot.dropHookAndRetractLift();  //scissor lift down
    }

    if (gamepad1.a) {
      moosalot.blueZipLineReleaser.extend();
      
    }
    moosalot.drivetrain.tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
      
    if(gamepad2.x){
      moosalot.winch.up();
    }
    
    if (gamepad2.dpad_up){
      moosalot.churroHook.up();
    }
    
    if (gamepad2.dpad_down){
      moosalot.churroHook.down();
    }
    
    if (gamepad2.right_bumper){
      moosalot.whiskTrain.forward();    //whisk train in
    }
    
    if (gamepad2.right_trigger){
      moosalot.whiskTrain.backwards();
  }
}
