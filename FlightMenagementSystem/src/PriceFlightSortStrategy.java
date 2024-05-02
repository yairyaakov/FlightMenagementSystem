import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriceFlightSortStrategy implements FlightSortStrategy {

    /**
     * This method sorts a list of flights by price
     * @return the new list of the flights
     */
    @Override
    public List<Flight> sort(List<Flight> flights) {
        List<Flight> sortedFlights = new ArrayList<>(flights);
        sortedFlights.sort(Comparator.comparingDouble(Flight::getPrice));
        return sortedFlights;
    }
}
