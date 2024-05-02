import java.util.List;

public class FlightSortContext {
    private FlightSortStrategy strategy;

    public void setStrategy(FlightSortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Flight> executeSort(List<Flight> flights) {
        return strategy.sort(flights);
    }
}
