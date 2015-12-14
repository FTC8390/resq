package ftc8390.resq;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created on 11/30/2015.
 */
public class DriveTrain {
    public DcMotor leftDrive, rightDrive;

    boolean isFacingForward;
    double speed;

    public void init(HardwareMap hwMap) {
        isFacingForward = true;
        speed = 1.0;

        leftDrive = hwMap.dcMotor.get("leftdrive");
        rightDrive = hwMap.dcMotor.get("rightdrive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        // leftDrive.setDirection(DcMotor.Direction.REVERSE);


        leftDrive.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightDrive.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void setModeToRunUsingEncoders() {
        leftDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    public void start() {
        leftDrive.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightDrive.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void runLeftAtPower(double power) {
        leftDrive.setPower(Range.clip(power, -1, 1));
    }

    public void runRightAtPower(double power) {
        rightDrive.setPower(Range.clip(power, -1, 1));
    }

    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void tankDrive(double left, double right) {
        if (isFacingForward == true) {
            leftDrive.setPower(speed * left);
            rightDrive.setPower(speed * right);
        }else{
            leftDrive.setPower(-speed * right);
            rightDrive.setPower(-speed * left);
        }
    }


    public void faceForward() {
        isFacingForward = true;
    }

    public void faceBackward() {
        isFacingForward = false;
    }

    public void highSpeed() {
        speed = 1;
    }

    public void lowSpeed() {
        speed = .2;
    }
}
