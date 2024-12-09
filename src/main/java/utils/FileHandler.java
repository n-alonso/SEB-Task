package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {
    static Logger logger = LoggerFactory.getLogger(FileHandler.class);
    public static File createFile(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.debug("Current working directory: " + System.getProperty("user.dir"));
                logger.debug("Absolute file path: " + file.getAbsolutePath());
                throw new FileNotFoundException("File does not exist: " + filePath);
            }
            return file;
        } catch (NullPointerException e) {
            logger.error("Error creating file: " + e.getMessage());
            e.printStackTrace();
            throw new IOException();
        }
    }
}
