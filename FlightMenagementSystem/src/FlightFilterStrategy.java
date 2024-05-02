import java.util.List;

interface FlightFilterStrategy {
    List<Flight> filter(List<Flight> flights);
}