import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
class FlightDurationFlightSortStrategy implements FlightSortStrategy {

    /**
     * This method sorts a list of flights by duration of the flight
     * @return  the list of the flights after the sort
     */
    public List<Flight> sort(List<Flight> flights) {
        List<Flight> sortedFlights = new ArrayList<>(flights);
        Collections.sort(sortedFlights, Comparator.comparingLong(Flight::getFlightDuration));
        return sortedFlights;
    }
}