import java.util.List;
import java.util.concurrent.Callable;

public class SearchAndBookTask implements Callable<Boolean> {
    private final BookingSystem bookingSystem;
    private final String destination;

    public SearchAndBookTask(BookingSystem bookingSystem, String destination) {
        this.bookingSystem = bookingSystem;
        this.destination = destination;
    }

    @Override
    public Boolean call() throws Exception {
        // Simulate searching for flights asynchronously
        List<Flight> availableFlights = bookingSystem.searchFlights(destination);

        if (availableFlights.isEmpty()) {
            System.out.println("No flights available to " + destination);
            return false;
        }

        // Attempt to book the first available flight
        for (Flight flight : availableFlights) {
            if (bookingSystem.bookTicket(flight)) {
                return true;
            }
        }

        return false;
    }
}
