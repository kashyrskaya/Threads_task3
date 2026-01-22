package lt.esdc.partOne;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Spam {
    private final Thread[] threads;
    private final String[] messages;
    private final int[] timeouts;

    public Spam(String[] messages, int[] timeouts) {
        this.messages = messages;
        this.timeouts = timeouts;
        this.threads = new Thread[messages.length];
    }

    public static void main(String[] args) {
        String[] messages = new String[]{"@@@", "bbbbbbb"};
        int[] times = new int[]{333, 222};

        Spam spam = new Spam(messages, times);
        spam.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        spam.stop();
    }

    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(messages[i], timeouts[i]);
            threads[i].start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static class Worker extends Thread {
        private final String message;
        private final int timeout;

        public Worker(String message, int timeout) {
            this.message = message;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(message);
                    Thread.sleep(timeout);
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

}
