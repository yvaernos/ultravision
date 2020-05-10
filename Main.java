package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
*/

import java.util.InputMismatchException;

public class Main  {

    /*
    To get to the classes Input and Session I used dependency injection through their
    respective setters, so I could use their implemented methods without having to set to static.
    */
    private Input input;
    private Session session;

    public void setInput(Input input) {
        this.input = input;
    }
    public void setSession(Session session) {
        this.session = session;
    }

    public static void main(String[] args) {

        Input input = new Input();
        Session session = new Session();
        Database database = new Database();

        Main main = new Main();

        main.setInput(input);
        main.setSession(session);

        welcomeMsg();
        menuOptions(input, session, database);

    }
    // A message to welcome the user.
    private static void welcomeMsg() {

        System.out.println("+----------------------------------+" +
                           "\n¦ Welcome to Ultra-Vision's System ¦" +
                           "\n+----------------------------------+");
    }
    /*
     I thought that the system should have some level of security, so I created a login method.
     This method gives to the user 3 options of which they can:
     > log in if they already have an account, sending to the Session class to log in.
     > register a new account, which links with Register class, or
     > log from the system.
    */
    protected static void menuOptions(Input input, Session session, Database database) {
        User user = new User();
        String choiceOne = "Log In ",
               choiceTwo = "Create an Account.",
               choiceThree = "Log Off.";

        System.out.print("\nPlease, choose one of the options below:"+
                "\n\n1) Press 1 to " + choiceOne +
                "\n2) Press 2 to " + choiceTwo +
                "\n3) Press 3 to " + choiceThree +
                "\n>  ");

        boolean choice = false;

        while (!choice){

            /*
             A try/catch to handle input exceptions.
             It returns to the main menu while the condition is not satisfied.
            */
            try {
                int userChoice = input.getInt();

                switch (userChoice) {
                    case 1:
                        System.out.println(choiceOne);
                        session.connectToDatabase(input,database);
                        input.close();
                        break;

                    case 2:
                        System.out.println(choiceTwo);
                        user.getLogin(input, database);
                        input.close();
                        break;

                    case 3:
                        System.out.println(choiceThree);
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("\"" +userChoice+"\"" + " is not one of the options.");
                        menuOptions(input, session, database);
                }
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("Error 1: "+"\"" +input.getString()+"\"" + " is not a valid number.");
                menuOptions(input, session, database);
            }
        } menuOptions(input, session, database);
    }
    /*
    After the existent user has logged in they can access the main menu,
    where they can view and manage customers and titles.
     */
    protected void mainMenu(Input input, Session session) {

        Database database = new Database();
        Customer customer = new Customer();

        String choiceOne = "Add new Customers.",
               choiceTwo = "Update Customers details",
               choiceThree = "Search Customers.",
               choiceFour = "Customer Rents.",
               choiceFive = "Customers Returns.",
               choiceSix = "Add new Subscription.",
               choiceSeven = "Search Subscription.",
               choiceEight = "Manage Rented Subscription.",
               choiceNine = "Log off";

        System.out.print("\nPlease, choose one of the options below:" +
                "\n\n1) Press 1 to " + choiceOne +   "\n2) Press 2 to " + choiceTwo +
                "\n3) Press 3 to " + choiceThree + "\n4) Press 4 to " + choiceFour +
                "\n5) Press 5 to " + choiceFive +  "\n6) Press 6 to " + choiceSix +
                "\n7) Press 7 to " + choiceSeven + "\n8) Press 8 to " + choiceEight +
                "\n9) Press 9 to " + choiceNine + "\n>  ");

        boolean choice = false;

        while (!choice) {

            try {
                int userChoice = input.getInt();

                switch (userChoice) {
                    case 1:
                        System.out.println(choiceOne);
                        customer.addCustomer(input, database);
                        input.close();
                        break;

                    case 2:
                        System.out.println(choiceTwo);
                        customer.updateCustomer(input, database);
                        input.close();
                        break;

                    case 3:
                        System.out.println(choiceThree);
                        customer.searchCustomer(input,database);
                        input.close();
                        break;

                    case 4:
                        System.out.println(choiceFour);

                        input.close();
                        break;

                    case 5:
                        System.out.println(choiceFive);

                        input.close();
                        break;
                    case 6:
                        System.out.println(choiceSix);

                        input.close();
                        break;
                    case 7:
                        System.out.println(choiceSeven);

                        input.close();
                        break;
                    case 8:
                        System.out.println(choiceEight);

                        input.close();
                        break;
                    case 9:
                        System.out.println(choiceNine);
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("\"" +userChoice+"\"" + " is not one of the options.");
                        mainMenu(input, session);
                }
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("Error 2: "+"\"" +input.getString()+"\"" + " is not a valid number.");
                mainMenu(input, session);
            }
        } mainMenu(input, session);


    }

}
