package ftc8390.resq;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created on 11/30/2015.
 */
public class RobotResq {

    public ChurroHook churroHook;
    public DebrisDumper redDebrisDumper, blueDebrisDumper;
    public DriveTrain driveTrain;
    public Lift lift;
    public Poker poker;
    public WhiskTrain whiskTrain;
    public Winch winch;

    public RobotResq() {
        churroHook = new ChurroHook();
        redDebrisDumper = new DebrisDumper();
        blueDebrisDumper = new DebrisDumper();
        driveTrain = new DriveTrain();
        lift = new Lift();
        poker = new Poker();
        whiskTrain = new WhiskTrain();
        winch = new Winch();
    }

    public void init(HardwareMap hwMap) {
        churroHook.init(hwMap);
        redDebrisDumper.init(hwMap, true);
        blueDebrisDumper.init(hwMap, false);
        driveTrain.init(hwMap);
        lift.init(hwMap);
        poker.init(hwMap);
        whiskTrain.init(hwMap);
        winch.init(hwMap);
    }

    public void start() {
        churroHook.start();
        redDebrisDumper.start();
        blueDebrisDumper.start();
        driveTrain.start();
        lift.start();
        poker.start();
        whiskTrain.start();
        winch.start();
    }

    public void raiseHookTowardMountain() {
        if (poker.isByRobot() == true) {
            lift.stop();
            poker.moveToVertical();
        } else if (lift.isUp() == false) {
            poker.stop();
            lift.raiseToTop();
        } else {
            lift.stop();
            poker.pokeToMountain();

        }
    }


    public void dropHookAndRetractLift() {
        if (lift.isHookDropped()== false) {
            lift.lowerToBottom();
            poker.stop();
        } else if (poker.isByMountain() == true) {
            poker.moveToVertical();
            lift.stop();
        } else if (lift.isDown() == false){
            poker.stop();
            lift.lowerToBottom();
        } else {
            lift.stop();
            poker.pullInToRobot();
        }
    }


    public boolean stopHook() {
        poker.stop();
        lift.stop();
        return false;
    }

}
