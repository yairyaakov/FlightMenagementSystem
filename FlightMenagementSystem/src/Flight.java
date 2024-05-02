import java.util.*;
public class Flight implements AirlineComponent{
    private final String flightNumber;
    AirlineCompany airlineCompany;
    private final String departure;
    private final String destination;
    private Date departureDate;
    private Date landDate;
    private double price;
    private long flightDuration; //duration of the flight
    private List<FlightUpdateObserver> observers = new ArrayList<>(); //List of all subscribers registered for flight updates
    private boolean cancelled = false; //Was the flight cancelled
    private final Passenger[] seats; //An array of seats of users that bought a ticket flight
    private int numOfPassengers=0; //the number of the passengers who bought a ticket for this flight
    static private ArrayList<Flight>allFlights = new ArrayList<Flight>(); //An arrayList that contains all flights

    public Flight (String flightNumber, AirlineCompany arline, String departure, String destination, Date departureDate, Date landDate, double price, int numOfseats) {
        this.flightNumber=flightNumber;
        this.airlineCompany=arline;
        this.departure=departure;
        this.destination=destination;
        this.departureDate=departureDate;
        this.landDate=landDate;
        this.price=price;
        this.flightDuration = landDate.getTime()-departureDate.getTime();
        this.seats =new Passenger[numOfseats];

        allFlights.add(this);
        arline.add(this);
        for (int i=0; i<User.getUsersMemory().size(); i++){
            User.getUsersMemory().get(i).getReservedSeats().putIfAbsent(flightNumber, new HashMap<>());
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getLandDate() {
        return landDate;
    }

    public double getPrice() {
        return price;
    }

    public String getDeparture() {
        return departure;
    }

    public static ArrayList<Flight> getAllFlights() {
        return allFlights;
    }

    //This method returns all available seats left on the plane
    public int getNumAvailablePlaces() {
        return seats.length-this.numOfPassengers;
    }

    // Check if the flight was cancelled
    public boolean isCancelled() {
        return this.cancelled;
    }

    public long getFlightDuration() {
        return flightDuration / (1000 * 60 * 60); // Convert milliseconds to hours
    }

    //Change the departure time and update the observer
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
        long landTime=departureDate.getTime()+flightDuration;
        this.landDate=new Date(landTime);
        notifyObservers("Departure time of flight " + this.flightNumber + " has been changed to: " + departureDate);
    }

    //Change the price of this flight and update the observer
    public void setPrice(double price) {
        if (price<=0){
            System.out.println("InValid price");
            return;
        }
        this.price = price;
        notifyObservers("Price of flight " + this.flightNumber + " has been updated to: " + price);
    }

    //This method seats the passenger in a seat on the plane
    public void setSeats(Passenger P){
        if (this.getNumAvailablePlaces() == 0) {
            System.out.println("No space available in this Flight.");
            return;
        }
        this.seats[this.numOfPassengers]=P;
        numOfPassengers++;
    }


    //return the relevant information about this flight
    public String toString() {
        long departueTimeInMinutes = (flightDuration / (1000 * 60)) - (60 * (flightDuration / (1000 * 60 * 60)));
        return "Flight " + flightNumber + ": " + departure + " -> " + destination + ", Airline Company: " + airlineCompany.getName() + ", Price: " + price +"$, Departure Date: " + departureDate + ", landDate Time: " + landDate + ", Duration: " + flightDuration / (1000 * 60 * 60) + " hours and " + departueTimeInMinutes + " minutes" +
                "Number of seats: " + seats.length + "Number of available seats: " +  this.getNumAvailablePlaces();
    }

    //Print the relevant information about this flight
    @Override
    public void displayInfo() {
        long departureTimeInMinutes = (flightDuration / (1000 * 60)) - (flightDuration * 60 / (1000 * 60 * 60) );
        System.out.println("\tFlight " + flightNumber + ": " + departure + " -> " + destination + ", Airline Company: " + airlineCompany.getName() + ", Price: " + price + "$,  Departure Date: " + departureDate + ", landDate Time: " + landDate + ", Duration: " + flightDuration / (1000 * 60 * 60) + " hours and "+ departureTimeInMinutes + " minutes" +
                "Number of seats: " + seats.length + "Number of available seats: " +  this.getNumAvailablePlaces());
    }

    //This method checks if the user has already registered for the flight updates service
    public boolean isRegisteredAlready (FlightUpdateObserver observer){
        if (observers.contains(observer)) {
            System.out.println("You are already receiving notification about this flight");
            return true;
        }
        return false;
    }

    //add the user to the flight update service
    public void addObserver(FlightUpdateObserver observer) {
        observers.add(observer);
    }

    ////remove the user to the flight update service
    public void removeObserver(FlightUpdateObserver observer) {
        if (!observers.contains(observer)){
            return;
        }
        observers.remove(observer);
    }

    //Sends the user an update message regarding the flight
    private void notifyObservers(String message) {
        for (FlightUpdateObserver observer : observers) {
            observer.update(message);
        }
    }

    //This function checks whether the user is a passenger of this flight by ID number
    public boolean isHeThisFlightPassenger(String ID){
        for (int i=0; i<this.numOfPassengers; i++){
            if (seats[i].getID().equals(ID)){return true;}
        }
        return false;
    }

    //This method checks and return passenger who has the same ID or return null if there is none
    public Passenger searchPassengerByID(String ID){
        for (int i=0; i<this.numOfPassengers; i++){
            if (this.seats[i].getID().equals(ID)){
                return this.seats[i];
            }
        }
        System.out.println("No such passenger in this flight");
        return null;
    }

    //Cancel this flight
    public void cancelFlight() {
        if (!cancelled) {
            cancelled = true;
            this.airlineCompany.removeComponent(this);
            allFlights.remove(this);
            notifyObservers("The flight " + this.flightNumber + " has been canceled");
            for (int i = 0; i<this.observers.size(); i++){
                this.observers.remove(i);
            }
        }
    }

    //This method checks if there is a flight with the name flight that given by the user
    public static boolean isFlightExist(String numberFlight){
        boolean flag=false;
        for (int i=0; i<getAllFlights().size(); i++){
            if (getAllFlights().get(i).getFlightNumber().equals(numberFlight)){
                flag=true;
            }
        }
        return flag;
    }

    //This method returns the flight with the name flight that given by the user
    public static Flight getFlightByNum(String numberFlight) {
        Flight wanted = null;
        for (int i = 0; i < getAllFlights().size(); i++) {
            if (getAllFlights().get(i).getFlightNumber().equals(numberFlight)) {
                wanted = getAllFlights().get(i);
            }
        }
        return wanted;
    }
}
