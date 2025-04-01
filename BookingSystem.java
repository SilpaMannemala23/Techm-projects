import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private final List<Flight> availableFlights;

    public BookingSystem() {
        // Simulate a few flights
        this.availableFlights = new ArrayList<>();
        availableFlights.add(new Flight("AA101", "New York", 10));
        availableFlights.add(new Flight("BA202", "London", 5));
        availableFlights.add(new Flight("CX303", "Hong Kong", 3));
    }

    public synchronized List<Flight> searchFlights(String destination) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : availableFlights) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                result.add(flight);
            }
        }
        return result;
    }

    public boolean bookTicket(Flight flight) {
        return flight.bookSeat();
    }
}
