import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
public class DepartureDateFlightFilterStrategy implements FlightFilterStrategy {
    private final Date departureDate;

    public DepartureDateFlightFilterStrategy(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * This method lists every flight that has a departure date that the user has chosen
     * @return the new list of the flights
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (isSameDate(flight.getDepartureDate(), departureDate)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    //This method compare between two dates
    private boolean isSameDate(Date date1, Date date2) {
        int year1 = date1.getYear();
        int month1 = date1.getMonth();
        int day1 = date1.getDate();

        int year2 = date2.getYear();
        int month2 = date2.getMonth();
        int day2 = date2.getDate();

        return (year1 == year2) && (month1 == month2) && (day1 == day2);
    }
}
