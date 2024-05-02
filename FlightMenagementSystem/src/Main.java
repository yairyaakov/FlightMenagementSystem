import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /**
     * This function sort list of flight according to the user request
     * @return the list of flight after the sort
     */
    private static int sortList( List<Flight> Flights){
        Scanner scanner = new Scanner(System.in);
        int wantedNumber=-1;
        while (wantedNumber < 0 || wantedNumber > 4) {
            System.out.println("Sort by price, press 1\n" +
                    "Sort by departure time, press 2\n" +
                    "Sort by land time, press 3\n" +
                    "Sort by flight duration, press 4\n" +
                    "To return to the main menu, please press 0");
            try {
                wantedNumber = scanner.nextInt();
                // Check if the number is outside the valid range
                if (wantedNumber < 0 || wantedNumber > 4) {
                    System.out.println("Invalid number, please enter a number from 0 to 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid character, please enter a valid number.\n" +
                        "Sort by price, press 1\n" +
                        "Sort by departure time, press 2\n" +
                        "Sort by land time, press 3\n" +
                        "Sort by flight duration, press 4\n" +
                        "To return to the main menu, please press 0");
                scanner.next();
            }
        }
        FlightSortContext context = new FlightSortContext();
        if (wantedNumber==1){
            context.setStrategy(new PriceFlightSortStrategy());
            List<Flight> sortedFlightsByPrice = context.executeSort(Flights);
            System.out.println("Flights sorted by price:");
            for (Flight flight : sortedFlightsByPrice) {
                System.out.println(flight);
            }
            System.out.println();
            return wantedNumber;
        }
        else if (wantedNumber==2){
            context.setStrategy(new DepartureTimeFlightSortStrategy());
            List<Flight> sortedFlightsByDepartureTime = context.executeSort(Flights);
            System.out.println("Flights sorted by departure time:");
            for (Flight flight : sortedFlightsByDepartureTime) {
                System.out.println(flight);
            }
            System.out.println();
            return wantedNumber;
        }
        else if (wantedNumber==3){
            context.setStrategy(new LandTimeFlightSortStrategy());
            List<Flight> sortedFlightsByLandTime = context.executeSort(Flights);
            System.out.println("Flights sorted by land time:");
            for (Flight flight : sortedFlightsByLandTime) {
                System.out.println(flight);
            }
            System.out.println();
            return wantedNumber;
        }
        else if (wantedNumber==4){
            context.setStrategy(new FlightDurationFlightSortStrategy());
            List<Flight> sortedFlightsByDuration = context.executeSort(Flights);
            System.out.println("Flights sorted by duration:");
            for (Flight flight : sortedFlightsByDuration) {
                System.out.println(flight);
            }
            System.out.println();
            return wantedNumber;
        }
        return wantedNumber;
    }

    /**
     * This function introduce to the user extra information about the airline he chooses
     */
    private static void airlineInfo(AirlineCompany airline){
        Scanner scanner = new Scanner(System.in);
        airline.displayInfo();
        System.out.println();
        int wantedNum=-1;
        while (wantedNum < 0 || wantedNum > 1) {
            System.out.println("if you want to get more information for " + airline.getName() + " Airline, please press 1\n" +
                    "To return, please press 0 ");
            try {
                wantedNum = scanner.nextInt();
                // Check if the number is outside the valid range
                if (wantedNum < 0 || wantedNum > 1) {
                    System.out.println("Invalid number, please enter a number from 0 to 1.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid character, please enter a valid number.\n" +
                        "if you want to get more information for " + airline.getName() + " Airline, please press 1\n" +
                        "To return to the main menu, please press 0 ");
                scanner.next();
            }
        }
        if (wantedNum==1){
            airline.displayMoreInfo();
        }
    }

    /**
     * This function reads the name's country input from the user
     * @return the name of the user
     */
    private static String country_name_enter(){
        Scanner scanner = new Scanner(System.in);
        String countryName;
        while (true) {
            countryName = scanner.nextLine();
            // Check if the name contains only letters and spaces
            if (countryName.matches("[a-zA-Z ]+")) {
                break; // Exit the loop if the name is valid
            } else {
                System.out.println("Invalid input. Please enter only letters and spaces.");
            }
        }
        return countryName;
    }

    /**
     * This function reads the name's user input from the user
     * @return the name of the user
     */
    private static String name_enter(){
        Scanner scanner = new Scanner(System.in);
        String name;
        while (true) {
            name = scanner.nextLine();
            // Check if the name contains only letters and spaces
            if (name.matches("[a-zA-Z ]+")) {
                break; // Exit the loop if the name is valid
            } else {
                System.out.println("Invalid input. Please enter only letters and spaces.");
            }
        }
        return name;
    }

    /**
     * This function reads ID input from the user
     * @return ID
     */
    private static String ID_enter(){
        Scanner scanner = new Scanner(System.in);
        String ID;
        while (true) {
            System.out.println("Please enter your ID");
            ID = scanner.next();
            if (ID.matches("\\d+")) {
                break;
            }
            else {
                System.out.println("Invalid input. Please enter digits only.");
            }
            scanner.close();
        }
        return ID;
    }

    /**
     * This function reads the phone number input from the user
     * @return phone number
     */
    private static String phone_number_enter(){
        Scanner scanner = new Scanner(System.in);
        String phone_number;
        while (true) {
            System.out.println("Please enter your phone number");
            phone_number = scanner.next();
            if (phone_number.matches("\\d+")) {
                break;
            }
            else {
                System.out.println("Invalid input. Please enter digits only.");
            }
            scanner.close();
        }
        return phone_number;
    }

    /**
     * The function reads an amount of money from the user that he wants to put into the account
     * @return the amount of money
     */
    private static double money_enter(){
        Scanner scanner = new Scanner(System.in);
        double money = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter how much money would you like to load into your account");
            try {
                money = scanner.nextDouble();
                if (money >= 0) {
                    valid = true;
                }
                else {
                    System.out.println("Please enter a positive number.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n" +
                        "Enter how much money would you like to load into your account");
                scanner.nextLine();
            }
        }
        return money;
    }

    /**
     * This function makes a purchase confirmation after the user's purchase
     */
    private static void purchase_confirmation(User buyerUser, int seatsNum, Flight wantedFlight){
        AirlineCompany thisAirlineCompany=wantedFlight.getAirlineCompany();
        System.out.println("Purchase confirmation:\n" +
                "name: " + buyerUser.getName() + "\n" + "ID: " + buyerUser.getID() + "\n" + "phone number: " + buyerUser.getPhone_number() + "\n" +
                "Flight number: " + wantedFlight.getFlightNumber() + "\n" +
                "FROM " + wantedFlight.getDeparture() + " TO " + wantedFlight.getDestination() + "\n");
        if (thisAirlineCompany.getEmployed().contains(buyerUser)) {
            System.out.println("Since you work for the airline " + thisAirlineCompany.getName() + "you received a " + wantedFlight.getAirlineCompany().getDiscountEmployee() + "% discount\n");
        }
        System.out.println(
                "Total cost " + (seatsNum*wantedFlight.getPrice()) + "$\n" +
                "Thank you, we were happy to serve you. YAIR TRAVEL AGENCY");
    }

    /**
     * This function checks if there is ID in the memory like the user entered and if it is true the function checks if the ID does is matched with the username
     * @return false if there is ID like the user entered in the memory and the ID does not match the username and otherwise return true
     */
    private static boolean is_ID_correct(String name, String id){
        if (User.isThisUserExist(id) && !Objects.equals(User.getUserFromMemoryByID(id).getName(), name)){
                return false;
        }
        return true;
    }

    /**
     * Connects or registers the user to the system according to the user's request
     * @return
     */
    public static User connect(){
        Scanner scanner = new Scanner(System.in);
        User user = null;
        int wantedNum=-1;
        while (wantedNum < 1 || wantedNum > 2) {
            System.out.println("SIGN IN, press 1\n" +
                    "JOIN, press 2");
            try {
                wantedNum = scanner.nextInt();
                // Check if the number is outside the valid range
                if (wantedNum < 1 || wantedNum > 2) {
                    System.out.println("Invalid number, please enter a number from 1 to 2.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid character, please enter a valid number.\n" +
                        "SIGN IN, press 1\n" +
                        "JOIN, press 2" );
                scanner.next();
            }
        }
        if (wantedNum==1){
            System.out.println("Please enter your name and last Name");
            String buyerName=name_enter();
            System.out.println("Please enter your password");
            String password=scanner.next(); //The user can enter any char he wants to
            while (!User.checkIfUserExist(buyerName, password)) {
                wantedNum=-1;
                while (wantedNum < 0 || wantedNum > 2) {
                    System.out.println("There is a mistake with the name or the password you entered.\n" +
                            "To continue press 1\n" + "To create new account press 2\n" +
                            "To return to the main menu, please press 0");
                    try {
                        wantedNum = scanner.nextInt();
                        // Check if the number is outside the valid range
                        if (wantedNum < 0 || wantedNum > 2) {
                            System.out.println("Invalid number, please enter a number from 0 to 2.");
                        }
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid character, please enter a valid number.\n" +
                                "To continue press 1\n" + "To create new account press 2\n" +
                                "To return to the main menu, please press 0" );
                        scanner.next();
                    }
                }
                if (wantedNum==1){
                    System.out.println("Please enter your name and last Name");
                    buyerName=name_enter();
                    System.out.println("Please enter your password");
                    password =scanner.next();
                }
                else if (wantedNum==2){
                    break;
                }
            }
            user=User.getUserFromMemoryByPassword(buyerName, password);
        }
        if (wantedNum==2){
            System.out.println("Please enter your name and last Name");
            String buyerName=name_enter();
            String buyerID=ID_enter();
            System.out.println("Please enter new password");
            String password =scanner.next();
            while (!User.checkPasswordByName(buyerName, password)){
                System.out.println("This password is in use.");
                    System.out.println("Please enter new password");
                    password =scanner.next();
            }
            String BuyerPhoneNumber=phone_number_enter();
            double money=money_enter();
            if (User.checkIfUserExist(buyerName, password)){
                System.out.println("This user already exist\n" +
                        "We're getting you into your account now");
                user=User.getUserFromMemoryByPassword(buyerName, password);
            }
            else {
                user=new User(buyerName, buyerID, password, BuyerPhoneNumber, money);
            }
        }
        if (user!=null){
            System.out.println();
        }
        return user;
    }

    private static int numPlacesToBuy(User buyerUser, Flight wantedFlight){
        Scanner scanner = new Scanner(System.in);
        int numPlaces = 0;
        System.out.println("How many places do you want to buy?\n" +
                "Note! The number of places you can buy is limited\n" +
                "You can buy up to 9 seats on the flight.");
        numPlaces = scanner.nextInt();
        // limitation about the number of ticket that possible to buy
        while (numPlaces < 1 || numPlaces > 9) {
            System.out.println("You can not buy " + numPlaces + " places.\n" +
                    "Please enter a number in the range 1-9");
            numPlaces = scanner.nextInt();
        }
        // limitation about the number of ticket that one user can buy
        while (buyerUser.getReservedSeats().get(wantedFlight.getFlightNumber()).size() + numPlaces > 9) {
            System.out.println("You bought " + buyerUser.getReservedSeats().get(wantedFlight.getFlightNumber()).size() + " tickets, you can buy more " + (9 - buyerUser.getReservedSeats().size()) + " tickets\n" +
                    "enter How many places do you want to buy (until " + (9 - buyerUser.getReservedSeats().size()) + ")");
            numPlaces = scanner.nextInt();
            while (numPlaces<1 || numPlaces>(9 - buyerUser.getReservedSeats().size())) {
                System.out.println("invalid input\n " +
                        "enter How many places do you want to buy (until " + (9 - buyerUser.getReservedSeats().size()) + ")");
                numPlaces = scanner.nextInt();
            }
        }
        return numPlaces;
    }

    public static void main(String[] args) {
        // create airlines and amployees
        AirlineCompany Arkia = new AirlineCompany("Arkia", new Date(110, 10, 10) , 20, "Arkia Israeli Airlines Ltd. is an Israeli airline that operates domestic and international flights.");

        AirlineEmployee employee1 = new AirlineEmployee("Gal", "206400345", "369854","052-4589655", 1000, Arkia);
        AirlineEmployee employee2 = new AirlineEmployee("Ron", "206158745", "159852", "053-2569847", 2300, Arkia);
        AirlineEmployee employee3 = new AirlineEmployee("Arik", "206985856", "256652", "052-1458658",0,  Arkia);
        AirlineEmployee employee4 = new AirlineEmployee("Sharon", "206125478", "125874", "050-7896587",0, Arkia);
        AirlineEmployee employee5 = new AirlineEmployee("Dan", "206147145", "125632", "052-9865659",0, Arkia);


        AirlineCompany Elal = new AirlineCompany("El-Al", new Date(102, 9, 8) , 30,"EL-AL is the largest airline in Israel and the Israeli flag carrier.");

        AirlineEmployee employee6 = new AirlineEmployee("Dana", "207728985", "152587", "053-7896585",0, Elal);
        AirlineEmployee employee7 = new AirlineEmployee("Daniel", "206785412", "788561", "053-2588525",0, Elal);
        AirlineEmployee employee8 = new AirlineEmployee("Yair", "207723198", "896589", "053-9865858",0, Elal);
        AirlineEmployee employee9 = new AirlineEmployee("Ori", "206963258", "458745","053-7896969",1000, Elal);
        AirlineEmployee employee10 = new AirlineEmployee("Rotem", "206785297", "785654","050-9696987",452, Elal);

        AirlineCompany Turkish = new AirlineCompany("Turkish", new Date(112, 3,8), 10,"Turkish is the national airline of Turkey. The airline's main base is at the airport in Istanbul.");

        AirlineEmployee employee11 = new AirlineEmployee("Noa", "207745632", "452365", "053-4585854",0, Turkish);
        AirlineEmployee employee12 = new AirlineEmployee("Nurit", "206783254", "897987", "050-7852525",780, Turkish);
        AirlineEmployee employee13 = new AirlineEmployee("Michael", "207733558", "123456", "053-9633258",450, Turkish);
        AirlineEmployee employee14 = new AirlineEmployee("Eden", "206778555", "123654", "053-1414587",100, Turkish);
        AirlineEmployee employee15 = new AirlineEmployee("Pol", "206452525", "963369", "050-4547458",0, Turkish);

        AirlineCompany Latam = new AirlineCompany("LATAM Airlines", new Date(29, 2,10), 15,"LATAM Airlines is the national airline of Chile and its main base is at the airport of the capital city of Santiago de Chile.");

        AirlineEmployee employee16 = new AirlineEmployee("Matan", "2077124512", "969363", "053-9686985",0, Latam);
        AirlineEmployee employee17 = new AirlineEmployee("Einav", "206787856", "785254", "050-7854123",780, Latam);
        AirlineEmployee employee18 = new AirlineEmployee("Natan", "207778452", "159951", "053-7845124",450, Latam);
        AirlineEmployee employee19 = new AirlineEmployee("Shlomi", "206569587", "357753", "053-7878451",100, Latam);
        AirlineEmployee employee20 = new AirlineEmployee("Monika", "206986986", "785325", "050-3265326",0, Latam);
        Arkia.add(Turkish);
        Elal.add(Arkia);

        // Create flights
        Flight flight1 = new Flight("AA101", Arkia,  "New York", "Los Angeles",new Date(120, Calendar.FEBRUARY, 1, 10, 30, 0),
                new Date(120, Calendar.FEBRUARY, 1, 13, 30, 0),900, 70);
        Flight flight2 = new Flight("UA202", Arkia, "Chicago", "San Francisco", new Date(122, Calendar.MARCH, 5, 11, 0, 0),
                new Date(122, Calendar.MARCH, 5, 16, 10, 0), 1200, 50);
        Flight flight3 = new Flight("DL303", Elal, "Boston", "Miami", new Date(115, Calendar.MARCH, 1, 9, 0, 0),
                new Date(115, Calendar.MARCH, 1, 19, 45, 0), 1000, 25);
        Flight flight4 = new Flight("DL404", Turkish, "Tel Aviv", "Bangkok", new Date(123, Calendar.APRIL, 10, 22, 50, 0),
                new Date(123, Calendar.APRIL, 11, 3, 15, 0), 800, 200);
        Flight flight5 = new Flight("DL505", Turkish, "Peru", " Beijing", new Date(124, Calendar.MAY, 15, 16, 40, 0),
                new Date(124, Calendar.MAY, 15, 21, 20, 0), 1200, 80);
        Flight flight6 = new Flight("AA909", Latam, "Buenos Aires", "Medellín", new Date(124, Calendar.AUGUST, 17, 16, 30, 0),
                new Date(124, Calendar.AUGUST, 17, 16, 0, 0), 300, 120);
        Flight flight7 = new Flight("UA111", Latam, "Medellín", "Quito", new Date(124, Calendar.JULY, 3, 6, 30, 0),
                new Date(124, Calendar.JULY, 3, 8, 40, 0), 400, 80);
        Flight flight8 = new Flight("DL707", Latam, "Sao Paulo", "Rio de Janeiro", new Date(124, Calendar.SEPTEMBER, 10, 10, 12, 0),
                new Date(124, Calendar.SEPTEMBER, 10, 11, 20, 0), 350, 150);
        Flight flight9 = new Flight("DL122", Turkish, "Amsterdam", "Istanbul", new Date(124, Calendar.NOVEMBER, 12, 23, 40, 0),
                new Date(124, Calendar.NOVEMBER, 13, 4, 20, 0), 600, 100);

        //create users
        User Daniel = new User("Daniel", "207723196", "4523656", "0537855858", 8000);
        User Chen = new User("Chen", "20656985", "9696859", "0504589856", 6000);
        AirlineEmployee Yarden = new AirlineEmployee("Yarden", "20656998", "8586859", "0534298989", 6000, Turkish);
        flight5.addObserver(Daniel);
        flight6.addObserver(Chen);


        while (true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            boolean isValidInput = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            int wantedNumber =-1;
            while (wantedNumber < 1 || wantedNumber > 5) {
                System.out.println("search for a flight, press 1\n" +
                        "To view all flights with filter options, press 2\n" +
                        "To view airlines info press 3\n" +
                        "For updates for a flight press 4\n" +
                        "for buy a ticket please press 5");
                try {
                    wantedNumber = scanner.nextInt();

                    // Check if the number is outside the valid range
                    if (wantedNumber < 1 || wantedNumber > 5) {
                        System.out.println("Invalid number, please enter a number from 1 to 5.");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid character, please enter a valid number.\n" +
                            "search for a flight, press 1\n" +
                            "To view all flights with filter options, press 2\n" +
                            "To view airlines info press 3\n" +
                            "For updates for a flight press 4\n" +
                            "for buy a ticket please press 5");
                    scanner.next();  // Consume the invalid input and clear the buffer
                }
            }
            // search for a flight
             if (wantedNumber == 1) {
                System.out.println("Where from?");
                String wantedDeparture = country_name_enter();
                System.out.println("Where to?");
                String wantedDestination = country_name_enter();

                Date departureDate = null;
                // checks if the input is valid
                while (!isValidInput) {
                    System.out.print("Enter departure date (yyyy-MM-dd): ");
                    String input = scanner.nextLine();

                    try {
                        departureDate = dateFormat.parse(input);
                        isValidInput = true;
                    }
                    catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                    }
                }
                //Sorting the flights by departure and destination and date of flight
                FlightFilterContext context = new FlightFilterContext();
                context.setStrategy(new DepartureDestinationFlightFilterStrategy(wantedDeparture,wantedDestination));
                List<Flight> filteredFlights  = context.executeFilter(Flight.getAllFlights());
                context.setStrategy(new DepartureDateFlightFilterStrategy(departureDate));
                filteredFlights = context.executeFilter(filteredFlights);

                if (filteredFlights.isEmpty()){
                    System.out.println("There are no flights according to the data you entered");
                }
                else {
                    System.out.println("These are the flights according to the data you entered:");
                    for (Flight flight : filteredFlights) {
                        System.out.println(flight);
                    }
                }
            }

             // view all flights with filter options
            else if (wantedNumber == 2) {
                 for (Flight flight : Flight.getAllFlights()) {
                     System.out.println(flight);
                 }
                 System.out.println();
                 wantedNumber=-1;
                 while (wantedNumber < 0 || wantedNumber > 2) {
                     System.out.println("FILTER, press 1\n" +
                             "SORT, press 2\n" +
                             "To return to the main menu, please press 0");
                     try {
                         wantedNumber = scanner.nextInt();
                         // Check if the number is outside the valid range
                         if (wantedNumber < 0 || wantedNumber > 2) {
                             System.out.println("Invalid number, please enter a number from 0 to 2.");
                         }
                     } catch (InputMismatchException e) {
                         System.out.println("Invalid character, please enter a valid number.\n" +
                                "FILTER, press 1\n" +
                                 "SORT, press 2\n" +
                                 "To return to the main menu, please press 0");
                         scanner.next();  // Consume the invalid input and clear the buffer
                     }
                 }
                 //Filter option
                 if (wantedNumber==1){
                     boolean validInput = false;
                     String filterNum = "";
                     while (!validInput) {
                     System.out.println(  "You can choose several options (use format xyz)\n" +
                             "Filter by departure and destinations press 1\n" +
                             "Filter by price range, press 2\n" +
                             "Filter by departure time range, press 3\n" +
                             "To return to the main menu, please press 0");
                         filterNum = scanner.next();
                         // Check if the input is a digit and within the valid range
                         if (filterNum.matches("[0-3]")) {
                             validInput = true;
                         } else {
                             System.out.println("Invalid character, please enter a valid number.\n" +
                             "You can choose several options (use format xyz)\n" +
                                     "Filter by departure and destinations press 1\n" +
                                     "Filter by price range, press 2\n" +
                                     "Filter by departure time range, press 3\n" +
                                     "To return to the main menu, please press 0");
                         }
                     }
                     List<Flight> filteredFlights = Flight.getAllFlights();
                     FlightFilterContext context = new FlightFilterContext();
                     if (!filterNum.contains("0")) {

                         // Filter by departure and destinations
                         if (filterNum.contains("1")) {
                             System.out.println("Where from?");
                             String wantedDeparture = country_name_enter();
                             System.out.println("Where to?");
                             String wantedDestination = country_name_enter();
                             scanner.nextLine();
                             context.setStrategy(new DepartureDestinationFlightFilterStrategy(wantedDeparture, wantedDestination));
                             filteredFlights = context.executeFilter(filteredFlights);
                         }

                         //Filter by price range
                         if (filterNum.contains("2")) {
                             int min = 0;
                             int max = 0;
                             while (true) {
                                 System.out.println("Enter minimum price:");
                                 try {
                                     min = scanner.nextInt();
                                     break; // exit the loop if a valid integer is received
                                 } catch (InputMismatchException e) {
                                     System.out.println("Invalid character, please enter a valid number.");
                                     scanner.next();
                                 }
                             }
                             while (true) {
                                 System.out.println("Enter maximum price:");
                                 try {
                                     max = scanner.nextInt();
                                     if (max >= min) {
                                         break; // exit the loop if a valid integer is received and it's greater than or equal to min
                                     } else {
                                         System.out.println("The maximum price must be greater than or equal to the minimum price.");
                                     }
                                 } catch (InputMismatchException e) {
                                     System.out.println("Invalid character, please enter a valid number.");
                                     scanner.next();
                                 }
                             }
                             context.setStrategy(new PriceFlightFilterStrategy(min,max));
                             filteredFlights = context.executeFilter(filteredFlights);
                         }

                         //Filter by departure time range
                         if (filterNum.contains("3")) {
                             System.out.println("Please enter date in yyyy-MM-dd format");
                             isValidInput = false;
                             Date MinDate = null;
                             Date MaxData = null;
                             // checks if the input is valid
                             while (!isValidInput) {
                                 System.out.print("Search flight from date (yyyy-MM-dd): ");
                                 String input1 = scanner.nextLine();
                                 System.out.print("To date: ");
                                 String input2 = scanner.nextLine();
                                 try {
                                     MinDate = dateFormat.parse(input1);
                                     MaxData = dateFormat.parse(input2);
                                     isValidInput = true;
                                 }
                                 catch (ParseException e) {
                                     System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                                 }
                             }
                             context.setStrategy(new DepartureTimeRengeFlightFilterStrategy(MinDate, MaxData));
                             filteredFlights = context.executeFilter(filteredFlights);
                         }

                         // If the list is not empty sort the list according the user request
                         if (!filteredFlights.isEmpty()){
                             for (Flight flight : filteredFlights) {
                                 System.out.println(flight);
                             }
                             System.out.println();
                             int check;
                             do {
                                 check=sortList(filteredFlights);
                             }while (check>=1 && check<=4);
                         }
                         System.out.println("There are no flights according to the parameters you are looking for ");
                     }
                 }

                 // Sort option
                 if (wantedNumber==2){
                     int check;
                     do {
                         check=sortList(Flight.getAllFlights());
                     }while (check>=1 && check<=4);
                 }
             }

            // view airlines info
            else if (wantedNumber == 3) {
                do {
                    wantedNumber=-1;
                    while (wantedNumber < 0 || wantedNumber > 3) {
                        System.out.println("For Arkia airline press 1\n" +
                                "For El-Al airline press 2\n" +
                                "For Turkish airline press 3\n" +
                                "For LATAM airline press 4\n" +
                                "To return to the main menu, please press 0 ");
                        try {
                            wantedNumber = scanner.nextInt();
                            // Check if the number is outside the valid range
                            if (wantedNumber < 0 || wantedNumber > 3) {
                                System.out.println("Invalid number, please enter a number from 0 to 3.");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid character, please enter a valid number.");
                            System.out.println("For Arkia airline press 1\n" +
                                    "For El-Al airline press 2\n" +
                                    "For Turkish airline press 3\n" +
                                    "For LATAM airline press 4\n" +
                                    "To return to the main menu, please press 0 ");
                            scanner.next();
                        }
                    }
                    if (wantedNumber == 1){
                        airlineInfo(Arkia);
                    }
                    if (wantedNumber == 2){
                        airlineInfo(Elal);
                    }
                    if (wantedNumber == 3){
                        airlineInfo(Turkish);
                    }
                    if (wantedNumber == 4){
                        airlineInfo(Latam);
                    }
                    System.out.println();
                }while (wantedNumber!=0);
            }

            // updates for a flight
            else if (wantedNumber == 4) {
                System.out.println("Enter number of flight please");
                String numberFlight = scanner.next();
                 while (!Flight.isFlightExist(numberFlight)) {
                     System.out.println("There is no such flight, try another number\n ");
                     numberFlight = scanner.next();
                 }
                Flight wantedFlight = Flight.getFlightByNum(numberFlight);
                 System.out.println("Please enter your name and last Name");
                 String name=name_enter();
                 String ID=ID_enter();
                 // if the user is registered in the system, Check if the ID matches the username
                 while (!is_ID_correct(name, ID)){
                     System.out.println("this ID is incorrect.\n" +
                             "Please enter your ID");
                     ID = scanner.next();
                 }
                AirlineCompany wantedFlightsAirline = wantedFlight.getAirlineCompany();
                //check if this user is employee
                boolean isEmployee = false;
                for (int i = 0; i < wantedFlightsAirline.getEmployed().size(); i++) {
                    AirlineEmployee Employee = wantedFlightsAirline.getEmployed().get(i);
                    //Checks if he is an employee of the company
                    if (Employee.getID().equals(ID)) {
                        isEmployee = true;
                        //Checks if he is already subscribed to the update system
                        if (!wantedFlight.isRegisteredAlready(Employee)) {
                            wantedFlight.addObserver(Employee);
                            System.out.println("Hello the employee " + Employee.getName() + "!\n" +
                                    "We will keep you updated on any changes to " + wantedFlight.getFlightNumber() + " flight");
                        }
                    }
                }
                //checks if this user is passenger
                if (!isEmployee) {
                    if (wantedFlight.isHeThisFlightPassenger(ID)) {
                        Passenger passenger = wantedFlight.searchPassengerByID(ID);
                        if (!wantedFlight.isRegisteredAlready(passenger)) {
                            wantedFlight.addObserver(passenger);
                            System.out.println("Hello the passenger " + passenger.getName() + "!\n" +
                                    "We will keep you updated on any changes to " + wantedFlight.getFlightNumber() + " flight");
                        }
                    }
                    //checks if this user is system user
                    else {
                        //checks if this user already registered in the system
                        if (User.isThisUserExist(ID)) {
                            User user = User.getUserFromMemoryByID(ID);
                            if (!wantedFlight.isRegisteredAlready(user)){
                                wantedFlight.addObserver(user);
                                System.out.println("Hello  " + user.getName() + "!\n" +
                                        "We will keep you updated on any changes to " + wantedFlight.getFlightNumber() + " flight");
                            }
                        }
                        //If he is neither a company employee nor a passenger, nor a system user, then he is a regular user
                        else {
                            System.out.println("Please enter your password");
                            String password=scanner.next();
                            String phone_number;
                            phone_number=phone_number_enter();
                            User user = new User(name, ID, password, phone_number, 0);
                            wantedFlight.addObserver(user);
                            System.out.println("Hello  " + name + "!\n" +
                                    "We have registered you in the system and from now on we will inform you of any changes in the flight " + wantedFlight.getFlightNumber());
                        }
                    }
                }
            }

            // buy a ticket
            else {
                 System.out.println("Please Enter number of flight");
                 String flightNum = scanner.next();
                 while (!Flight.isFlightExist(flightNum)) {
                     System.out.println("There is no such flight, try another number\n ");
                     flightNum = scanner.next();
                 }
                 Flight wantedFlight = Flight.getFlightByNum(flightNum);
                 boolean return_main_menu = false;

                 User buyerUser = connect();

                 if (buyerUser == null) {
                     return_main_menu = true;
                 }
                 if (buyerUser.getReservedSeats().get(wantedFlight.getFlightNumber()).size() == 9) {
                     System.out.println("You bought 9 tickets for the flight " + wantedFlight.getFlightNumber() +". you can not buy more tickets. sorry");
                     return_main_menu = true;
                 }
                 if (!return_main_menu){
                     int numPlaces = 0;
                     numPlaces=numPlacesToBuy(buyerUser,wantedFlight);
                     if (numPlaces==0){
                         return_main_menu = true;
                     }
                     if (!return_main_menu){
                         while (buyerUser.getMoney()<(wantedFlight.getPrice()*numPlaces)){
                             wantedNumber=-1;
                             while (wantedNumber < 0 || wantedNumber > 1) {
                                 System.out.println("You don't have enough money in your account to buy this ticket\n"+
                                         "To load money to your account, press 1\n" +
                                         "To return to the main menu, please press 0 ");
                                 try {
                                     wantedNumber=scanner.nextInt();
                                     // Check if the number is outside the valid range
                                     if (wantedNumber < 0 || wantedNumber > 1) {
                                         System.out.println("Invalid number, please enter a number from 0 to 1.");
                                     }
                                 }
                                 catch (InputMismatchException e) {
                                     System.out.println("Invalid character, please enter a valid number.\n" +
                                             "To load money to your account, press 1\n" +
                                             "To return to the main menu, please press 0 ");
                                     scanner.next();
                                 }
                             }
                             if (wantedNumber==1){
                                 System.out.println("Enter how much money would you like to load into the account: ");
                                 buyerUser.loadMoney(scanner.nextDouble());
                             }
                             else {
                                 return_main_menu=true;
                                 break;
                             }
                         }
                     }
                     if (return_main_menu){
                         break;
                     }
                     Passenger.buyTicket(buyerUser, wantedFlight);
                     buyerUser.saveAnotherSeat(wantedFlight, buyerUser.getName(), buyerUser.getID());
                     if (numPlaces!=1){
                         int counter=1;
                         while (counter!=numPlaces) {
                             String name;
                             String id;
                             do {
                                 System.out.println("Note! You are the first passenger");
                                 System.out.println("enter the name and last name of passenger number " + (counter + 1));
                                 name = name_enter();
                                 System.out.println("Please enter ID of passenger number " + (counter + 1));
                                 while (true) {
                                     id = scanner.next();
                                     // Check if the input contains only digits
                                     if (id.matches("[0-9]+")) {
                                         // Valid ID entered
                                         break;
                                     }
                                     else {
                                         // Invalid input, prompt the user to enter again
                                         System.out.println("Invalid input. Please enter only numbers for the ID:");
                                     }
                                 }
                             } while (name.equals(buyerUser.getName()) && id.equals(buyerUser.getID()));
                             //If the User enter ID that recognize by author name - return this ID is incorrect
                             while (!is_ID_correct(name, id)) {
                                     System.out.println("this ID is incorrect.\n" +
                                             "Please enter ID of passenger number " + (counter + 1));
                                 while (true) {
                                     id = scanner.next();
                                     // Check if the input contains only digits
                                     if (id.matches("[0-9]+")) {
                                         // Valid ID entered
                                         break;
                                     }
                                     else {
                                         // Invalid input, prompt the user to enter again
                                         System.out.println("Invalid input. Please enter only numbers for the ID:");
                                     }
                                 }
                             }
                             System.out.println();
                             //Save one more person under the buyer user
                             buyerUser.saveAnotherSeat(wantedFlight, name, id);
                             //Buy one more ticket for him
                             Passenger.buyTicket(buyerUser, wantedFlight);
                             counter++;
                         }
                     }
                     purchase_confirmation(buyerUser, numPlaces, wantedFlight);
                     System.out.println();
                     // updates for a flight
                     wantedNumber=-1;
                     while (wantedNumber < 0 || wantedNumber > 1) {
                         System.out.println("if you want to receive updates about this flight, please press 1\n" +
                                 "To return to the main menu, please press 0");
                         try {
                             wantedNumber = scanner.nextInt();

                             // Check if the number is outside the valid range
                             if (wantedNumber < 0 || wantedNumber > 1) {
                                 System.out.println("Invalid number, please enter a number from 0 to 1.");
                             }
                         }
                         catch (InputMismatchException e) {
                             System.out.println("Invalid character, please enter a valid number.\n" +
                                     "if you want to receive updates about this flight, please press 1\n" +
                                     "To return to the main menu, please press 0");
                             scanner.next();  // Consume the invalid input and clear the buffer
                         }
                     }
                     if (wantedNumber == 1) {
                         if (!wantedFlight.isRegisteredAlready(buyerUser)) {
                             wantedFlight.addObserver(buyerUser);
                             System.out.println("Hello the passenger " + buyerUser.getName() + "!\n" +
                                     "We will keep you updated on any changes to " + wantedFlight.getFlightNumber() + " flight");
                         }
                     }
                     return_main_menu=true;
                 }
            }

            flight5.setPrice((flight5.getPrice()-20));
            flight6.addObserver(Yarden);
            flight6.cancelFlight();
            flight5.setDepartureDate(new Date(124, Calendar.MAY, 15, 14, 40, 0));
            flight5.setPrice((flight5.getPrice()+20));
            flight5.removeObserver(Daniel);
            Yarden.getAirlineCompany().fireEmployee(Yarden);
        }
    }
}
