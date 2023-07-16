package service;
import java.io.IOException;

public class CommandLineCleaner {

    public void clear() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                processBuilder.inheritIO().start().waitFor();
            } else {
                ProcessBuilder processBuilder = new ProcessBuilder("clear");
                processBuilder.inheritIO().start().waitFor();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
