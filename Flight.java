public class Flight {
    private final String flightNumber;
    private final String destination;
    private int availableSeats;

    public Flight(String flightNumber, String destination, int availableSeats) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public synchronized boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Booked 1 seat on " + flightNumber + " to " + destination);
            return true;
        } else {
            System.out.println("No available seats on " + flightNumber + " to " + destination);
            return false;
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
