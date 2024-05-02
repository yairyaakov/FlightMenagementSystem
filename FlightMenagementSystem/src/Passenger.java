import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Passenger extends User implements FlightUpdateObserver  {

    public Passenger(String name, String ID, String password, String phoneNumber, double money) {
        super(name, ID, password, phoneNumber, money);
    }

    /**
     * This method of buy ticket enter the user to the array of seats and lowers the amount of money the user has and if the user.
     * If the user is an employee of the airLine, the method calculates the amount of his money based on the discount that the company established
     */
    public static void buyTicket(User user, Flight flight){
        if(flight.isCancelled()){
            System.out.println("This flight has been cancelled, sorry");
            return;
        }
        AirlineCompany thisAirlineCompany=flight.getAirlineCompany();
        //discount to airline employee
        if (thisAirlineCompany.getEmployed().contains(user)){
            flight.setSeats(new Passenger(user.getName(), user.getID(), user.getPassword(), user.getPhone_number(), (user.getMoney()-(thisAirlineCompany.getDiscountEmployee()/100)*flight.getPrice())));
                return;
        }
        flight.setSeats(new Passenger(user.getName(), user.getID(), user.getPassword(), user.getPhone_number(), (user.getMoney()-flight.getPrice())));
    }
}
