import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements FlightUpdateObserver {
    private final String name;
    private final String ID;
    private final String password;
    private double money;
    private final String phone_number;
    private static ArrayList<User> usersMemory = new ArrayList<User>(); //Memory of all user registered in the system
    private Map<String, Map<String, String>> reservedSeats=new HashMap<>(); //A memory of all the people for whom the user bought a ticket and have a reserved seat on the flight

    public User(String name, String ID, String password, String phoneNumber, double money) {
        this.name=name;
        this.ID=ID;
        this.password=password;
        this.phone_number = phoneNumber;
        this.money=money;
        if(!usersMemory.contains(this)) {
            usersMemory.add(this);
        }
        //Add all the flights to the reserved seats memory
        for (int i=0; i<Flight.getAllFlights().size(); i++){
            reservedSeats.putIfAbsent(Flight.getAllFlights().get(i).getFlightNumber(), new HashMap<>());
        }
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.ID;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public double getMoney() {
        return money;
    }

    public static ArrayList<User> getUsersMemory() {
        return usersMemory;
    }

    //Get the user from the users memory according to ID
    public static User getUserFromMemoryByID(String ID) {
        for (User user : usersMemory) {
            if (user.ID.equals(ID)) {
                return user;
            }
        }
        return null;
    }

    //Get the user from the users memory according to password
    public static User getUserFromMemoryByPassword(String name, String password) {
        for (User user : usersMemory) {
            if (user.password.equals(password) && user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Map<String, Map<String, String>> getReservedSeats() {
        return reservedSeats;
    }

    //The update massage
    public void update(String message) {
        System.out.println("Dear " + name + ",\n" + message);
    }

    //Checking if this user is registered in the system according to ID
    public static boolean isThisUserExist(String ID){
        for (User user : usersMemory) {
            if (user.ID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    //Checks if the password is already in use
    public static boolean checkPasswordByName(String name, String password) {
        for (User user : usersMemory) {
            if (user.password.equals(password) && !user.name.equals(name)) {
                return false;
            }
        }
        return true;
    }

    //Checks if this user exist in the users memory
    public static boolean checkIfUserExist(String name, String password) {
        for (User user : usersMemory) {
            if (user.password.equals(password) && user.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    //Save a seat under the buyer user
    public void saveAnotherSeat(Flight flight, String name, String ID){
        this.reservedSeats.get(flight.getFlightNumber()).put(name,ID);
    }

    //Load money into the account
    public void loadMoney(double newMoney){
        this.money=this.money+newMoney;
    }

    public static void removeUser(String ID) {
        usersMemory.removeIf(user -> user.getID().equals(ID));
    }
}
