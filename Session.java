package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */

import ultravision.interfaces.Sessionable;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Map;

public class Session implements Sessionable {

    public void Session(){

        Input input = new Input();
        Session session;
        Database database= new Database();

    }

    @Override
    public void connectToDatabase(Input input, Database database) {

        try {
            System.out.println("\nChecking connection with database...");
            database.getConnection();

        }catch (Exception e){
            System.out.println("\nError 8:" + e);
        }getLogin(input, database);
    }

    @Override
    public void getLogin(Input input, Database database) {


        Map<String, String> username_password = database.getUserInfo();

        String username = "", password = "";

        try {

            System.out.print("\nPlease, enter your username: " + "\n>  ");
            username = input.getString();

            if (username.isEmpty() || !username_password.containsKey(username)) {
                System.out.println("\nUsername does not exist.\nPlease try again.");
                getLogin(input, database);
            } else {

                System.out.print("\nPlease, enter your password: " + "\n>  ");
                password = input.getString();
            }
            if (username_password.get(username).equals(password)) {
                database.userInput = username;
                for (String name : database.getFullName().keySet()) {

                    String fullNameMessage = name + " " + database.getFullName().get(name);
                    String nameToUppercase = fullNameMessage.substring(0, 1).toUpperCase() + fullNameMessage.substring(1).toUpperCase();
                    System.out.println("\nHi, " + nameToUppercase + ".");

                    Main main = new Main();

                    database.statement.close();
                    database.connection.close();
                    main.mainMenu(input);
                }
            } else {

                System.out.println("\nUsername and password do not match.\nPlease, try again");
                getLogin(input, database);

            }
        } catch (IllegalArgumentException | InputMismatchException | SQLException ex) {
            System.out.println("Error 8: " + "\nInvalid input.\nPlease, try again.");
            getLogin(input, database);


        }

    }
}
