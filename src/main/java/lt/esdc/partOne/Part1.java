package lt.esdc.partOne;

import java.io.InputStream;

public class Part1 {
    public static void main(String[] args) throws InterruptedException {
        InputStream originalInput = System.in;

        System.setIn(new InputStream() {
            private boolean firstRead = true;
            private final byte[] enter = System.lineSeparator().getBytes();
            private int i = 0;

            @Override
            public int read() {
                if (firstRead) {
                    firstRead = false;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (i < enter.length) {
                        return enter[i++];
                    }
                }
                return -1;
            }
        });

        Thread t = new Thread() { public void run() {Spam.main(null);}};
        t.start();

        t.join();

        System.setIn(originalInput);

    }
}
