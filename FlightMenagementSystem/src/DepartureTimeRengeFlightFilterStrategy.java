import java.util.*;

class DepartureTimeRengeFlightFilterStrategy implements FlightFilterStrategy {
    Date startDate;
    Date endDate;

    public DepartureTimeRengeFlightFilterStrategy(Date startDate, Date endDate){
        this.startDate=startDate;
        this.endDate=endDate;
    }

    /**
     * This method adds to the list any flight whose departure date is in the date range that the user has selected
     * @return the new list of the flights
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (isInPeriod(flight.getDepartureDate(), startDate, endDate)) {
                filteredFlights.add(flight);
            }
        }
        filteredFlights.sort(Comparator.comparingLong(f -> f.getDepartureDate().getTime()));
        return flights;
    }

    //This method checks if the departure date between two dates that the user has chosen
    private boolean isInPeriod(Date checkFlight, Date startDate, Date endDate) {
        int yearCheckFlight = checkFlight.getYear();
        int monthCheckFlight = checkFlight.getMonth();
        int dayCheckFlight = checkFlight.getDate();

        int yearStartDate = startDate.getYear();
        int monthStartDate = startDate.getMonth();
        int dayStartDate = startDate.getDate();

        int yearEndDate = endDate.getYear();
        int monthEndDate = endDate.getMonth();
        int dayEndDate = endDate.getDate();

        return (yearCheckFlight>=yearStartDate) && (yearCheckFlight<=yearEndDate) &&
                (monthCheckFlight>=monthStartDate) && (monthCheckFlight<=monthEndDate) &&
                (dayCheckFlight>=dayStartDate) && (dayCheckFlight<=dayEndDate);
    }
}
