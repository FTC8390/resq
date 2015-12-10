package ftc8390.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp Mode
 * Enables control of the robot via the gamepad
 */
public class Test extends OpMode {

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
        double positionUpdate = 0.001;

        telemetry.clearData();

        if (!gamepad1.left_stick_button) {
            moosalot.driveTrain.tankDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        } else {
            moosalot.driveTrain.tankDrive(0, 0);
        }
        telemetry.addData("A1", "g1.sticks = drive");

        moosalot.whiskTrain.runAtPower(gamepad2.right_stick_y);
        telemetry.addData("A2", "g2.right_stick = whisk");

        if (gamepad1.dpad_up) {
            moosalot.redDebrisDumper.changePositionBy(positionUpdate);
        }
        if (gamepad1.dpad_down) {
            moosalot.redDebrisDumper.changePositionBy(-positionUpdate);
        }
        telemetry.addData("A3", "g1.dpad = red dumper");

        if (gamepad2.dpad_up) {
            moosalot.blueDebrisDumper.changePositionBy(positionUpdate);
        }
        if (gamepad2.dpad_down) {
            moosalot.blueDebrisDumper.changePositionBy(-positionUpdate);
        }
        telemetry.addData("A4", "g2.dpad = blue dumper");

        if (gamepad1.x) {
            moosalot.redZipLineReleaser.changePositionBy(positionUpdate);
        }
        if (gamepad1.a) {
            moosalot.redZipLineReleaser.changePositionBy(-positionUpdate);
        }
        telemetry.addData("A5", "g1.x,a = red zipliner");

        if (gamepad2.x) {
            moosalot.blueZipLineReleaser.changePositionBy(positionUpdate);
        }
        if (gamepad2.a) {
            moosalot.blueZipLineReleaser.changePositionBy(-positionUpdate);
        }
        telemetry.addData("A6", "g2.x,a = blue zipliner");

        if (gamepad1.left_bumper) {
            moosalot.churroHook.changePositionBy(positionUpdate);
        }
        if (gamepad1.left_trigger > 0.75) {
            moosalot.churroHook.changePositionBy(-positionUpdate);
        }
        telemetry.addData("A7", "g1.left_bump/trig = churro hook");

        if (gamepad1.right_bumper) {
            moosalot.poker.pullInToRobot();
        } else if (gamepad1.right_trigger > 0.75) {
            moosalot.poker.pokeToMountain();
        } else if (gamepad1.start && gamepad2.start) {
            moosalot.poker.pullIn();
        } else {
            moosalot.poker.stop();
        }
        telemetry.addData("A8", "g1.right_bump/trig = poker");

        if (gamepad2.left_bumper) {
            moosalot.lift.raiseToTop();
        } else if (gamepad2.left_trigger > 0.75) {
            moosalot.lift.lowerToBottom();
        } else if (gamepad1.left_stick_button && gamepad2.left_stick_button) {
            moosalot.lift.lower();
        } else {
            moosalot.lift.stop();
        }
        telemetry.addData("A9", "g2.left_bump/trig = lift");

        if (gamepad2.right_bumper) {
            moosalot.winch.windUpMountain();
        } else if (gamepad2.right_trigger > 0.75) {
            moosalot.winch.unwind();
        } else {
            moosalot.winch.stop();
        }
        telemetry.addData("AA", "g2.right_bump/trig = winch");

        telemetry.addData("AB", "both starts = poker in no encoder!!!");
        telemetry.addData("AC", "both left stick buttons = lift down no encoder!!!");


        telemetry.addData("D leftDrive", moosalot.driveTrain.leftDrive.getCurrentPosition());
        telemetry.addData("D rightDrive", moosalot.driveTrain.rightDrive.getCurrentPosition());

        telemetry.addData("M whisk", moosalot.whiskTrain.motor.getCurrentPosition());
        telemetry.addData("M winch", moosalot.winch.motor.getCurrentPosition());
        telemetry.addData("M lift", moosalot.lift.motor.getCurrentPosition());
        telemetry.addData("M poker", moosalot.poker.motor.getCurrentPosition());

        telemetry.addData("S reddd", moosalot.redDebrisDumper.position);
        telemetry.addData("S redz", moosalot.redZipLineReleaser.position);
        telemetry.addData("S bluedd", moosalot.blueDebrisDumper.position);
        telemetry.addData("S bluez", moosalot.blueZipLineReleaser.position);
        telemetry.addData("S churro", moosalot.churroHook.position);

    }
}
