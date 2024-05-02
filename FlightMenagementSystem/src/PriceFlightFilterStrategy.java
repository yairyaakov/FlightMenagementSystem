import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
class PriceFlightFilterStrategy implements FlightFilterStrategy {
    private double minPrice;
    private double maxPrice;

    public PriceFlightFilterStrategy(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    /**
     * This method puts in the list any flight whose price is in the price range that the user has chosen
     * @return the new list of flight
     */
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>(flights);
        filteredFlights.removeAll(flights);
        for (Flight flight : flights) {
            if (flight.getPrice() >= minPrice && flight.getPrice() <= maxPrice) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}