package DrainSizer;
import java.util.*;
import Fixtures.*;
import utils.*;
/*
* This class encapsulates all operations for FileIO for the program. Instead of having many lines and messy code for file IO.
*/
public class FileIO {
    private String path;
    /// 
    /// Creates a new FileIO object. It takes one parameter of String for the file path with the format name. So: C:\Project\Directory\Filename.txt for example
    /// 
    public FileIO(String m_path) {
        path = m_path;
    }
    
    /** 
     * @return String
     */
    private String readFile() {
        return null;
    }
    
    private String readFile(String regex) {
        return null;
    }

    private boolean writeFile() {
        return false;
    }
    private boolean writeFile(String content) {
        return false;
    }

    private void appendFile(String content) {
        
    }

}
