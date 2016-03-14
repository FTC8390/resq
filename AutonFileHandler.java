package ftc8390.resq;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AutonFileHandler {

  // variables used during the configuration process
  private String configFileName = "AutonInfo.txt";

  // all data here
  public Integer driveDistanceBeacon;
  public Integer turnDistanceBeacon;
  public Integer backDistanceBeacon;
  public Integer waitTime;
  public boolean climberDump;
  public Integer driveDistanceRamp;
  public Integer turnDistanceRamp;
  public Integer climbDistanceRamp;
  public Double driveSpeed;


  public void readDataFromFile(Context context) {
    // setup initial configuration parameters here

    // read configuration data from file
    driveDistanceBeacon = -10564;
    turnDistanceBeacon = 1446;
    backDistanceBeacon = 750;
    waitTime = 0;
    climberDump = true;
    driveDistanceRamp = -6000;
    turnDistanceRamp = -3000;
    climbDistanceRamp = -6000;
    climbDistanceRamp = -6000;
    driveSpeed = -.5;
    try {
      InputStream inputStream = context.openFileInput(configFileName);

      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // read data here
        driveDistanceBeacon = Integer.valueOf(bufferedReader.readLine());
        turnDistanceBeacon = Integer.valueOf(bufferedReader.readLine());
        backDistanceBeacon = Integer.valueOf(bufferedReader.readLine());
        waitTime = Integer.valueOf(bufferedReader.readLine());
        climberDump = Boolean.valueOf(bufferedReader.readLine());
        driveDistanceRamp = Integer.valueOf(bufferedReader.readLine());
        turnDistanceRamp = Integer.valueOf(bufferedReader.readLine());
        climbDistanceRamp = Integer.valueOf(bufferedReader.readLine());
        driveSpeed = Double.valueOf(bufferedReader.readLine());
        inputStream.close();
      }
    } catch (Exception e) {
      // values here, for first time or in case there's a problem reading.
      driveDistanceBeacon = -10564;
      turnDistanceBeacon = 1446;
      backDistanceBeacon = 750;
      waitTime = 0;
      climberDump = true;
      driveDistanceRamp = -6000;
      turnDistanceRamp = -3000;
      climbDistanceRamp = -6000;
      driveSpeed = -0.5;

    }

  }

  public boolean writeDataToFile(Context context) {
    // may want to write configuration parameters to a file here if they are needed for teleop too!
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(configFileName, Context.MODE_PRIVATE));

      // write data here, as a string on its own line. "\n" puts a new line at the end of the write, like hitting "enter"
      outputStreamWriter.write(Integer.toString(driveDistanceBeacon) + "\n");
      outputStreamWriter.write(Integer.toString(turnDistanceBeacon) + "\n");
      outputStreamWriter.write(Integer.toString(backDistanceBeacon) + "\n");
      outputStreamWriter.write(Integer.toString(waitTime) + "\n");
      outputStreamWriter.write(Boolean.toString(climberDump) + "\n");
      outputStreamWriter.write(Integer.toString(driveDistanceRamp) + "\n");
      outputStreamWriter.write(Integer.toString(turnDistanceRamp) + "\n");
      outputStreamWriter.write(Integer.toString(climbDistanceRamp) + "\n");
      outputStreamWriter.write(Double.toString(driveSpeed) + "\n");
      outputStreamWriter.close();
      return true;
    } catch (IOException e) {
      return false;
    }

  }

}
