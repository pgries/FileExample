package uoft.csc207.fileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

  // See these for more information:
  // + https://developer.android.com/reference/android/util/Log
  // + https://developer.android.com/training/data-storage/files/internal

  /** For logging output. */
  private static final String TAG = "Main Activity";

  /** The example file to write and read. */
  private static final String EXAMPLE_FILE = "example.txt";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Where is the root directory on the device?
    File rootDir = getFilesDir();
    Log.i(TAG, "root directory is " + rootDir);

    writeToFile();

    String s = readFromFile();
    Log.i(TAG, "information read: " + s);
  }

  /** Create a file and write a line of text to it. */
  private void writeToFile() {
    PrintWriter out = null;

    try {
      OutputStream outStream = openFileOutput(EXAMPLE_FILE, Context.MODE_PRIVATE);
      out = new PrintWriter(outStream);
    } catch (FileNotFoundException e) {
      Log.e(TAG, "Error encountered trying to open file for writing: " + EXAMPLE_FILE);
    }

    out.println("Line 1");
    out.println("Line 2");
    out.close();
  }

  /**
   * Read and return the information in EXAMPLE_FILE.
   *
   * @return the contents of EXAMPLE_FILE.
   */
  private String readFromFile() {
    StringBuffer buffer = new StringBuffer();
    try (Scanner scanner = new Scanner(openFileInput(EXAMPLE_FILE))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        buffer.append(line).append('\n');
      }
    } catch (IOException e) {
      Log.e(TAG, "Error encountered trying to open file for reading: " + EXAMPLE_FILE);
    }

    return buffer.toString();
  }
}
