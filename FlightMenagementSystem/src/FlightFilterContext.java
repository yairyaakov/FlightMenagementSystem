import java.util.List;
class FlightFilterContext {
    private FlightFilterStrategy strategy;

    public void setStrategy(FlightFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Flight> executeFilter(List<Flight> flights) {
        return strategy.filter(flights);
    }
}