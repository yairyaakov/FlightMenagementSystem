import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LandTimeFlightSortStrategy implements FlightSortStrategy{

    /**
     * This method sorts a list of flights by land date
     * @return  the list of the flights after the sort
     */
    @Override
    public List<Flight> sort(List<Flight> flights) {
        List<Flight> sortedFlights = new ArrayList<>(flights);
        sortedFlights.sort(Comparator.comparingLong(f -> f.getLandDate().getTime()));
        return sortedFlights;
    }
}
