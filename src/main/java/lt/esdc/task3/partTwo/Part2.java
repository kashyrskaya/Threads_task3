package lt.esdc.task3.partTwo;

public class Part2 {
    private final int n;
    private final int k;
    private final int t;
    private int c1;
    private int c2;

    public Part2(int n, int k, int t) {
        this.n = n;
        this.k = k;
        this.t = t;
    }

    public static void main(String[] args) {
        Part2 p = new Part2(3, 5, 100);
        p.test();
        p.reset();
        p.testSync();
    }

    public void reset() {
        this.c1 = 0;
        this.c2 = 0;
    }

    private void performStep() {
        System.out.println("c1: " + c1 + ", c2: " + c2);

        c1++;

        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        c2++;
    }

    private void runThreads(boolean sync) {
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < k; j++) {
                    if (sync) {
                        synchronized (this) {
                            performStep();
                        }
                    } else {
                        performStep();
                    }
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void test() {
        System.out.println("=== test (Unsynchronized) ===");
        runThreads(false);
    }

    public void testSync() {
        System.out.println("=== test (Synchronized) ===");
        runThreads(true);
    }
}
