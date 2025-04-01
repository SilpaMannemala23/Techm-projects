import java.util.concurrent.*;

public class FlightBookingSystem {
    public static void main(String[] args) {
        // Create the BookingSystem
        BookingSystem bookingSystem = new BookingSystem();

        // ExecutorService to manage concurrent tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // List of tasks (simulating user search and booking)
        Future<Boolean> future1 = executorService.submit(new SearchAndBookTask(bookingSystem, "New York"));
        Future<Boolean> future2 = executorService.submit(new SearchAndBookTask(bookingSystem, "London"));
        Future<Boolean> future3 = executorService.submit(new SearchAndBookTask(bookingSystem, "Hong Kong"));
        Future<Boolean> future4 = executorService.submit(new SearchAndBookTask(bookingSystem, "New York"));
        Future<Boolean> future5 = executorService.submit(new SearchAndBookTask(bookingSystem, "London"));

        try {
            // Get the result of each task
            System.out.println("Booking Status for User 1: " + future1.get());
            System.out.println("Booking Status for User 2: " + future2.get());
            System.out.println("Booking Status for User 3: " + future3.get());
            System.out.println("Booking Status for User 4: " + future4.get());
            System.out.println("Booking Status for User 5: " + future5.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}
