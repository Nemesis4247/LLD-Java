package Multithreading.UnixTail;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PeriodicWriter implements Runnable{

    final Path path;
    int lineCount;

    public PeriodicWriter(String uri) throws IOException {
        this.path = Path.of(uri);
        if (!path.toFile().exists()) {
            boolean newFile = path.toFile().createNewFile();
            if (!newFile) throw new RuntimeException("File creation failed for " + path);
        }
        Files.writeString(path, "This is a Test File --- Header");
    }

    @Override
    public void run() {
        final String line = String.format("This is line no: %d%n", ++lineCount);
        try {
            Files.writeString(path, line, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
