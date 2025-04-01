import java.util.concurrent.*;

public class CountdownTimerSystem {

    public static void main(String[] args) throws InterruptedException {
        // Number of tasks to execute
        int numberOfTasks = 5;

        // Create a CountDownLatch initialized with the number of tasks
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        // Executor service to manage parallel tasks
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Fixed pool with 3 threads

        // Submit tasks to executor service
        for (int i = 1; i <= numberOfTasks; i++) {
            executorService.submit(new Task("Task-" + i, latch));
        }

        // Wait for all tasks to complete (i.e., latch count down to zero)
        System.out.println("Main thread is waiting for tasks to complete...");
        latch.await();  // This will block until the latch count reaches zero

        // After all tasks are completed, the main thread proceeds
        System.out.println("All tasks completed. Main thread resuming...");

        // Shutdown the executor service
        executorService.shutdown();
    }
}
