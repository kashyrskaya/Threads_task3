package lt.esdc.partThree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixGenerator {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 100;
        String fileName = "part3.txt";

        Random random = new Random();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.write(random.nextInt(1000) + (j == cols - 1 ? "" : " "));
                }
                writer.write("\n");
            }
            System.out.println("Matrix file " + fileName + " created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
