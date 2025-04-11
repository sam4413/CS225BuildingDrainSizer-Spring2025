package drainsizer;

import java.io.*;
import java.util.*;

import utils.*;

/*
* This class encapsulates all operations for FileIO for the program. Instead of having many lines and messy code for file IO.
*/
public class FileIO {
    private String path;

    ///
    /// Creates a new FileIO object. It takes one parameter of String for the file
    /// path with the format name. So: C:\Project\Directory\Filename.txt for example
    ///
    public FileIO(String m_path) {
        path = m_path;
    }

    /**
     * @return String
     */

    public String readFile() {
        try (Scanner fileScanner = new Scanner(new File(path))) {
            String content = "";
            while (fileScanner.hasNextLine()) {
                content += fileScanner.nextLine();
            }
            Log.debug(content);
            return content;
        } catch (Exception e) {
            Log.error("An error occurred.\n" + e.getStackTrace());
            return null;

        }
    }

    public ArrayList<String> readFile(String regex) {
        try (Scanner fileScanner = new Scanner(new File(path))) {
            ArrayList<String> content = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(regex);
                content.addAll(Arrays.asList(parts));
            }
            Log.debug(regex);
            return content;
        } catch (Exception e) {
            Log.error("An error occurred.\n" + e.getStackTrace());
            return null;
        }
    }

    public boolean writeFile() {
        try {
            File myFile = new File(path);
            if (myFile.createNewFile()) {
                Log.debug("File created: " + myFile.getName());
                return true;
            } else {
                Log.error("File already exists.");
                return false;
            }
        } catch (IOException e) {
            Log.error("An error occurred.\n" + e.getStackTrace());
            return false;
        }
    }

    public boolean writeFile(String content) {
        try {
            FileWriter myFileWriter = new FileWriter(path);
            myFileWriter.write(content);
            myFileWriter.close();
            Log.debug("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            Log.error("An error occurred.\n" + e.getStackTrace());
            return false;
        }
    }

    public boolean appendFile(String content) {
        try {
            BufferedWriter myBufferedWriter = new BufferedWriter(new FileWriter(path, true));
            myBufferedWriter.write(content);
            myBufferedWriter.close();
            return true;
        } catch (IOException e) {
            Log.error("An error occurred.\n" + e.getStackTrace());
            return false;
        }
    }

}
