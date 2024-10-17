package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConnection {

    private static String[] readUserAndPW() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/postgres.txt"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("-");
            }
            return sb.toString().split("-");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
