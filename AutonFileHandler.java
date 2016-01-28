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
  public Integer driveDistance;
  public Integer turnDistance;
  public Integer backDistance;
  public Integer waitTime;
  public boolean climberDump;

  public void readDataFromFile(Context context) {
    // setup initial configuration parameters here

    // read configuration data from file
    driveDistance = -9844;
    turnDistance = 1432;
    backDistance = 0;
    waitTime = 0;
    climberDump = true;
    try {
      InputStream inputStream = context.openFileInput(configFileName);

      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // read data here
        driveDistance = Integer.valueOf(bufferedReader.readLine());
        turnDistance = Integer.valueOf(bufferedReader.readLine());
        backDistance = Integer.valueOf(bufferedReader.readLine());
        waitTime = Integer.valueOf(bufferedReader.readLine());
        climberDump = Boolean.valueOf(bufferedReader.readLine());

        inputStream.close();
      }
    } catch (Exception e) {
      // values here, for first time or in case there's a problem reading.
      driveDistance = -9844;
      turnDistance = 1432;
      backDistance = 0;
      waitTime = 0;
      climberDump = true;

    }

  }

  public boolean writeDataToFile(Context context) {
    // may want to write configuration parameters to a file here if they are needed for teleop too!
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(configFileName, Context.MODE_PRIVATE));

      // write data here, as a string on its own line. "\n" puts a new line at the end of the write, like hitting "enter"
      outputStreamWriter.write(Integer.toString(driveDistance) + "\n");
      outputStreamWriter.write(Integer.toString(turnDistance) + "\n");
      outputStreamWriter.write(Integer.toString(backDistance) + "\n");
      outputStreamWriter.write(Integer.toString(waitTime) + "\n");
      outputStreamWriter.write(Boolean.toString(climberDump) + "\n");

      outputStreamWriter.close();
      return true;
    } catch (IOException e) {
      return false;
    }

  }

}
