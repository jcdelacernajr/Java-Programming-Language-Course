package library;

import java.io.File;
import java.net.URISyntaxException;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Path {

    /** Declare it private to prevent instantiation */
    private Path() {
    }

    /**
     * Function to locate the file
     * 
     * @return File
     * @throws java.net.URISyntaxException 
     */
    public static File uri() throws URISyntaxException {
        File file = new File("data");
        if (!file.exists()) {
            throw new RuntimeException("Could not identify program location, found " + file.getAbsolutePath());
        }
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        return file;
    }
}
