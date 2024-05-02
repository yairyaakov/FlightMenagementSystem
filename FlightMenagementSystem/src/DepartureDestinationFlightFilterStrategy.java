import java.util.List;
import java.util.ArrayList;

public class DepartureDestinationFlightFilterStrategy implements FlightFilterStrategy {
    private final String departure;
    private final String destination;

    public DepartureDestinationFlightFilterStrategy(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    /**
     * This method lists every flight that has a departure and destination that the user has chosen
     * @return the new list of the flights
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}

