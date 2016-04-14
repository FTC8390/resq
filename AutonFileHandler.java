package ftc8390.resq;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AutonFileHandler {

  // all data here
  public Integer driveDistanceFar;
  public Integer turnDistanceFar;
  public Integer backDistanceFar;
  public Integer waitTime;
  public boolean climberDump;
  public Integer driveDistanceClose;
  public Integer turnDistanceClose;
  public Integer backDistanceClose;
  public Double driveSpeed;
  // variables used during the configuration process
  private String configFileName = "AutonInfo.txt";

  public void readDataFromFile(Context context) {
    // setup initial configuration parameters here

    // read configuration data from file
    driveDistanceFar = -10564;
    turnDistanceFar = 1446;
    backDistanceFar = 750;
    waitTime = 0;
    climberDump = true;
    driveDistanceClose = -10000;
    turnDistanceClose = -1500;
    backDistanceClose = -3000;
    driveSpeed = -.5;
    try {
      InputStream inputStream = context.openFileInput(configFileName);

      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // read data here
        driveDistanceFar = Integer.valueOf(bufferedReader.readLine());
        turnDistanceFar = Integer.valueOf(bufferedReader.readLine());
        backDistanceFar = Integer.valueOf(bufferedReader.readLine());
        waitTime = Integer.valueOf(bufferedReader.readLine());
        climberDump = Boolean.valueOf(bufferedReader.readLine());
        driveDistanceClose = Integer.valueOf(bufferedReader.readLine());
        turnDistanceClose = Integer.valueOf(bufferedReader.readLine());
        backDistanceClose = Integer.valueOf(bufferedReader.readLine());
        driveSpeed = Double.valueOf(bufferedReader.readLine());
        inputStream.close();
      }
    } catch (Exception e) {
      // values here, for first time or in case there's a problem reading.
      driveDistanceFar = -10564;
      turnDistanceFar = 1446;
      backDistanceFar = 750;
      waitTime = 0;
      climberDump = true;
      driveDistanceClose = -10000;
      turnDistanceClose = -1500;
      backDistanceClose = -3000;
      driveSpeed = -0.5;

    }

  }

  public boolean writeDataToFile(Context context) {
    // may want to write configuration parameters to a file here if they are needed for teleop too!
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(configFileName, Context.MODE_PRIVATE));

      // write data here, as a string on its own line. "\n" puts a new line at the end of the write, like hitting "enter"
      outputStreamWriter.write(Integer.toString(driveDistanceFar) + "\n");
      outputStreamWriter.write(Integer.toString(turnDistanceFar) + "\n");
      outputStreamWriter.write(Integer.toString(backDistanceFar) + "\n");
      outputStreamWriter.write(Integer.toString(waitTime) + "\n");
      outputStreamWriter.write(Boolean.toString(climberDump) + "\n");
      outputStreamWriter.write(Integer.toString(driveDistanceClose) + "\n");
      outputStreamWriter.write(Integer.toString(turnDistanceClose) + "\n");
      outputStreamWriter.write(Integer.toString(backDistanceClose) + "\n");
      outputStreamWriter.write(Double.toString(driveSpeed) + "\n");
      outputStreamWriter.close();
      return true;
    } catch (IOException e) {
      return false;
    }

  }

}
