public class AirlineEmployee extends User implements FlightUpdateObserver {
    AirlineCompany airlineCompany;

    public AirlineEmployee(String name, String ID, String password, String phoneNumber, double money, AirlineCompany airline) {
        super(name, ID, password, phoneNumber, money);
        this.airlineCompany = airline;
        airline.addEmployeeToMemory(this);
    }

    public AirlineCompany getAirlineCompany() {
        return this.airlineCompany;
    }

    //This method fires an employee from the airline
}