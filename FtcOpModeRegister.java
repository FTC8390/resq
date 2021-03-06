package ftc8390.resq;

import com.qualcomm.ftcrobotcontroller.opmodes.NullOp;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */

    manager.register("Teleop", Teleop.class);
    //manager.register("Auton1", Auton1.class);


    manager.register("TestUltrasonic", TestUltrasonic.class);


    manager.register("AutonCloseRed", AutonCloseRed.class);
    manager.register("AutonCloseBlue", AutonCloseBlue.class);
    manager.register("AutonFarRed", AutonFarRed.class);
    manager.register("AutonFarBlue", AutonFarBlue.class);

/*
    manager.register("AutonRed", AutonRed.class);
    manager.register("AutonBlue", AutonBlue.class);
    manager.register("AutonRedForward", AutonRedForward.class);
    manager.register("AutonRedRamp", AutonRedRamp.class);
    manager.register("AutonBlueRamp", AutonBlueRamp.class);
    */

    manager.register("Manual Test", Test.class);
    manager.register("WinchControl", OpWinchControl.class);
    manager.register("Change Auton", ChangeAutonDistances.class);
    manager.register("NullOp", NullOp.class);

  }
}
