package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

public class JsonParser<T> {
    Logger logger = LoggerFactory.getLogger(JsonParser.class);

    public T parseJsonFile(File file, Class<T> classReference) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, classReference);
        } catch (Exception e) {
            logger.error("Error parsing JSON file: " + e.getMessage());
            e.printStackTrace();
            throw new IOException();
        }
    }
}
