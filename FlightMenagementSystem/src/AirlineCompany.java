import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AirlineCompany implements AirlineComponent{
    private final String name;
    private final Date foundingDate;
    String extraInfo;
    private double discountEmployee; // the discount that the company established
    private ArrayList<AirlineEmployee> employed = new ArrayList<AirlineEmployee>();; //An arraylist that contains all employers of the airline
    private ArrayList<AirlineComponent> components = new ArrayList<AirlineComponent>(); //An arraylist that contains all airlines subsidiary and flights of the airline

    public AirlineCompany(String name, Date foundingDate, double discountEmployee, String extraInfo){
        this.name=name;
        this.foundingDate=foundingDate;
        this.extraInfo=extraInfo;
        this.discountEmployee=discountEmployee;
    }

    public String getName() {
        return name;
    }

    public List<AirlineEmployee> getEmployed() {
        return employed;
    }

    public double getDiscountEmployee() {
        return discountEmployee;
    }

    public void setDiscountEmployee(double discountEmployee) {
        this.discountEmployee = discountEmployee;
    }

    //This method introduce the hierarchy model of all airlines, and there airlines subsidiary and flights
    public void displayInfo() {
        System.out.println("Airline Company: " + name);
        System.out.println("\tHierarchy model:");
        for (AirlineComponent component : components) {
            System.out.print("\t");
            component.displayInfo();
        }
    }

    //This method introduce extra detailed information about the airlines
    public void displayMoreInfo() {
        System.out.println(extraInfo + "\n" +
                name + " was established on " + foundingDate + "\n" +
                "The airline has " + employed.size() + " employees");
    }

    //Add flight or airline an airline subsidiary to airline
    public void add(AirlineComponent airlineComponent) {
        components.add(airlineComponent);
    }

    //Remove flight or airline an airline subsidiary to airline
    public void removeComponent(AirlineComponent airlineComponent) {
        components.remove(airlineComponent);
    }

    public void addEmployee(User user){
        int index = User.getUsersMemory().indexOf(user);
        if (index != -1) { // If User object exists in usersMemory
            User.getUsersMemory().remove(index); // Remove the User object
            AirlineEmployee employee = new AirlineEmployee(user.getName(), user.getID(), user.getPassword(), user.getPhone_number(), user.getMoney(), this);
            User.getUsersMemory().add(index, employee); // Insert the new AirlineEmployee object at the same index
            employed.add(employee);
        }
    }

    public void addEmployeeToMemory(AirlineEmployee employee){
        this.employed.add(employee);
    }

    public void removeEmployee(AirlineEmployee employee) {
        this.employed.remove(employee);
    }
    public void fireEmployee(AirlineEmployee airlineEmployee){
        this.removeEmployee(airlineEmployee); // Remove from AirlineCompany's employed list
    }
}
