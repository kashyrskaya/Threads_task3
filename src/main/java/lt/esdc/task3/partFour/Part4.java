package lt.esdc.task3.partFour;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part4 {
    private static final String FILE_NAME = "part4.txt";
    private static final int K = 10;
    private static final int N = 20;

    public static void main(String[] args) {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw")) {

            Thread[] threads = new Thread[K];

            for (int i = 0; i < K; i++) {
                final int threadNum = i;
                threads[i] = new Thread(() -> {
                    try {
                        runThreadContent(raf, threadNum);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threads[i].start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            printFileContent(raf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runThreadContent(RandomAccessFile raf, int threadIndex) throws IOException, InterruptedException {
        String lineSeparator = System.lineSeparator();
        int lineLength = N + lineSeparator.length();

        for (int j = 0; j < N; j++) {
            Thread.sleep(1);

            synchronized (raf) {
                long offset = (long) threadIndex * lineLength + j;
                raf.seek(offset);
                raf.writeBytes(String.valueOf(threadIndex));
            }
        }

        synchronized (raf) {
            long offset = (long) threadIndex * lineLength + N;
            raf.seek(offset);
            raf.writeBytes(lineSeparator);
        }
    }

    private static void printFileContent(RandomAccessFile raf) throws IOException {
        System.out.println("File result " + FILE_NAME + ":");
        raf.seek(0);
        String line;
        while ((line = raf.readLine()) != null) {
            System.out.println(line);
        }
    }
}
