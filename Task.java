import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {
    private final String taskName;
    private final CountDownLatch latch;

    public Task(String taskName, CountDownLatch latch) {
        this.taskName = taskName;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Simulate task execution (e.g., file download or data processing)
            System.out.println("Task " + taskName + " started...");
            Thread.sleep((long) (Math.random() * 3000));  // Simulate time delay
            System.out.println("Task " + taskName + " completed.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();  // Decrease the count of the latch after completion
        }
    }
}
