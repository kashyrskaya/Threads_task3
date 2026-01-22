package lt.esdc.partFive;

public class Part5 {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(() -> {

            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(t.getState());

        synchronized (monitor) {
            t.start();

            boolean isRunnablePrinted = false;
            while (t.getState() != Thread.State.BLOCKED) {
                if (!isRunnablePrinted && t.getState() == Thread.State.RUNNABLE) {
                    System.out.println(t.getState());
                    isRunnablePrinted = true;
                }
            }

            System.out.println(t.getState());
        }

        while (t.getState() != Thread.State.WAITING) {
        }

        System.out.println(t.getState());

        synchronized (monitor) {
            monitor.notifyAll();
        }

        while (t.getState() != Thread.State.TERMINATED) {
        }

        System.out.println(t.getState());
    }
}
