package lt.esdc.partThree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part3 {
    private static final String FILE_NAME = "part3.txt";

    public static void main(String[] args) {
        int[][] matrix = readMatrixFromFile(FILE_NAME);
        if (matrix.length == 0) {
            System.err.println("Matrix is empty");
            return;
        }

        long startTime1 = System.currentTimeMillis();
        int max1 = findMaxParallel(matrix);
        long time1 = System.currentTimeMillis() - startTime1;

        long startTime2 = System.currentTimeMillis();
        int max2 = findMaxSequential(matrix);
        long time2 = System.currentTimeMillis() - startTime2;

        System.out.println("MAX1: " + max1);
        System.out.println("TIME1: " + time1);
        System.out.println("MAX2: " + max2);
        System.out.println("TIME2: " + time2);
    }

    private static int[][] readMatrixFromFile(String fileName) {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                int[] row = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.toArray(new int[0][]);
    }

    private static int findMaxSequential(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    private static int findMaxParallel(int[][] matrix) {
        int rows = matrix.length;
        Worker[] workers = new Worker[rows];

        for (int i = 0; i < rows; i++) {
            workers[i] = new Worker(matrix[i]);
            workers[i].start();
        }

        int globalMax = Integer.MIN_VALUE;
        try {
            for (Worker worker : workers) {
                worker.join();
                if (worker.getMax() > globalMax) {
                    globalMax = worker.getMax();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return globalMax;
    }

    private static class Worker extends Thread {
        private final int[] row;
        private int max;

        public Worker(int[] row) {
            this.row = row;
            this.max = (row.length > 0) ? row[0] : Integer.MIN_VALUE;
        }

        public int getMax() {
            return max;
        }

        @Override
        public void run() {
            for (int val : row) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                if (val > max) {
                    max = val;
                }
            }
        }
    }
}