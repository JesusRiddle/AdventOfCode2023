package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class FileReader {
    public InputStream getFileFromResourceAsStream(String fileName) throws FileNotFoundException {
        InputStream ioStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (ioStream == null) {
            throw new FileNotFoundException(fileName + " is not found");
        }
        return ioStream;
    }
}
