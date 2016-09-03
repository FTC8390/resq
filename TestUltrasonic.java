package ftc8390.resq;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbLegacyModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Bill on 8/2/2016.
 */

public class TestUltrasonic extends OpMode {

  UltrasonicSensorThread ultrasonicThreadA, ultrasonicThreadB;

  @Override
  public void init() {

    ModernRoboticsUsbLegacyModule legacyModule = (ModernRoboticsUsbLegacyModule) hardwareMap.legacyModule.get("legacy");

    int ultrasonicLegacyPort = 4; // legacy module port number for the ultrasonic sensor
    ultrasonicThreadA = new UltrasonicSensorThread(legacyModule,ultrasonicLegacyPort);
    ultrasonicThreadA.start();

    ultrasonicLegacyPort = 5; // legacy module port number for the ultrasonic sensor
    ultrasonicThreadB = new UltrasonicSensorThread(legacyModule,ultrasonicLegacyPort);
    ultrasonicThreadB.start();
  }

  @Override
  public void start() {

  }

  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    telemetry.addData("ultrasonic reading A: ",
        ultrasonicThreadA.getUltrasonicLevel()+" "+ ultrasonicThreadA.getUltrasonicLevelMin()+" "+ultrasonicThreadA.getUltrasonicLevelMedian()+" "+ultrasonicThreadA.getUltrasonicLevelMax());
    telemetry.addData("ultrasonic reading B: ", ultrasonicThreadB.getUltrasonicLevel());
  }

  @Override
  public void stop() {
    ultrasonicThreadA.interrupt();
    ultrasonicThreadB.interrupt();
  }

}
