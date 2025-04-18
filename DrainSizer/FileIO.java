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
                content += fileScanner.nextLine() + "\n";
            }
            Log.debug(content);
            return content;
        } catch (Exception e) {
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
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
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
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
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
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
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
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
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
            return false;
        }
    }

    public boolean fileExists() {
        try {
            File myFile = new File(path);
            return myFile.exists();
        } catch (Exception e) {
            Log.error("An error occurred.\n" + ExceptionHelper.stackToString(e));
            return false;
        }
    }


    //Test main
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file name and path to read  ");
        String path = input.nextLine();
        FileIO readFile = new FileIO(path);
        System.out.println(readFile.readFile());
        System.out.println("\n\n\nNow enter a file name to write to. ");
        String writeto = input.nextLine();
        FileIO writeToFile = new FileIO(writeto);
        writeToFile.writeFile(""" 
        According to all known laws
        of aviation,

        
        there is no way a bee
        should be able to fly.

        
        Its wings are too small to get
        its fat little body off the ground.

        
        The bee, of course, flies anyway

        
        because bees don't care
        what humans think is impossible.

        
        Yellow, black. Yellow, black.
        Yellow, black. Yellow, black.

        
        Ooh, black and yellow!
        Let's shake it up a little.

        
        Barry! Breakfast is ready!

        
        Ooming!

        
        Hang on a second.

        
        Hello?""");
        System.out.println("All done");
        input.close();
    }
}
