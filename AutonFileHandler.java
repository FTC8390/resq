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

  public void readDataFromFile(Context context) {
    // setup initial configuration parameters here

    // read configuration data from file
    try {
      InputStream inputStream = context.openFileInput(configFileName);

      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        driveDistance = Integer.valueOf(bufferedReader.readLine());

        inputStream.close();
      }
    } catch (Exception e) {
      driveDistance = -9500;
    }

  }

  public boolean writeDataToFile(Context context) {
    // may want to write configuration parameters to a file here if they are needed for teleop too!
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(configFileName, Context.MODE_PRIVATE));

      // write each parameter as a string on its own line
      outputStreamWriter.write(Integer.toString(driveDistance) + "\n");

      outputStreamWriter.close();
      return true;
    } catch (IOException e) {
      return false;
    }

  }

}
