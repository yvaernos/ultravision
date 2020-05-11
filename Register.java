package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */

import ultravision.interfaces.Sessionable;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


/*
This class is the super class of both classes User and Customer, as they share many similarities.
I set to abstract so it would implement some behaviours set by the Sessionable interface,
but just implementing the methods that it needs and nothing more.
 */
abstract class Register implements Sessionable {

    PreparedStatement preparedStatement;
    ResultSet resultSet;

    Pattern namePattern = Pattern.compile("^[^\\d\\s]{2,15}+$");
    Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
    Pattern usernamePattern = Pattern.compile("^[a-z]{3,8}+$");
    Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9]{3,8}+$");

    protected String inputName = "", inputSurname = "", inputAddress = "",
            inputEmail = "", inputUsername = "", inputPassword = "",
            inputConfirmPassword = "",
            choiceOne = "Music Lovers.",choiceTwo = "Video Lovers",
            choiceThree = "TV Lovers", choiceFour = "Premium";
    int inputSubscription;

    public Register() {

    }

    //method used to connect to the database before it adds the new customer or update data.
    @Override
    public void connectToDatabase(Input input, Database database) {

        try {
            database.getConnection();

            if (database.isConnected == true) {
                registerUser(input, database);
            }
        } catch (Exception e) {
           System.out.println("Error 10: "+ e);
        }
    }

    /*A method to register new users.
     It prompts the user to fill the fields first name, last name, email, username, and password.
     It has assigned a validation for each field.
    */
    public void registerUser(Input input, Database database) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("\nPlease, fill the form below.");

        System.out.print("\nFirst Name:\n> ");
        inputName = userInput.nextLine();

        while (!namePattern.matcher(inputName).matches()) {
            System.out.print("\nFirst name does not meet the requirements:\n- Letters only," +
                    "\n- No blank spaces,\n- Minimum of 2,\n- Maximum of 15 characters.");
            System.out.print("\nFirst Name:\n> ");
            inputName = userInput.nextLine();
        }
        System.out.print("\nLast Name:\n> ");
        inputSurname = input.getString();

        while (!namePattern.matcher(inputSurname).matches()) {
            System.out.print("\n Last name does not meet the requirements:" +
                    "\n- No blank spaces,\n- Letters only, \n- Minimum of 2,\n- Maximum of 15 characters." +
                    "\nLast Name: " + "\n> ");
            inputSurname = input.getString();
        }
        System.out.print("\nEmail:\n> ");
        inputEmail = input.getString();

        while (!emailPattern.matcher(inputEmail).matches()) {
            System.out.print("\nEmail does not meet the requirements:" +
                    "\n- No blank spaces, \n- Minimum of 8 characters.\nEmail:\n> ");
            inputEmail = input.getString();
        }

        /*In the username field it checks the existence of the same username in the database
        to avoid repetition.*/
        System.out.print("\nUsername:\n> ");
        inputUsername = input.getString();

        if (!usernamePattern.matcher(inputUsername).matches()) {

            System.out.print("\nUsername does not meet the requirements:" +
                    "\n- No blank spaces,\n- Lowercase letters only,\n- Minimum of 3 characters," +
                    "\n- Maximum of 8 characters.\nUsername:\n> ");
            inputUsername = input.getString();

        } else if (usernamePattern.matcher(inputUsername).matches()) {
            try {
                preparedStatement = database.connection.prepareStatement("SELECT * FROM users WHERE username = ? ");
                preparedStatement.setString(1, inputUsername);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    System.out.print("\nUsername already exists. Please choose another one.\nUsername:\n> ");
                    inputUsername = input.getString();
                }
            } catch (SQLException e) {
               System.out.println("Error 11: "+ e);
            }
        }

        System.out.print("\nPassword:\n> ");
        inputPassword = input.getString();

        while (!passwordPattern.matcher(inputPassword).matches()) {
            System.out.print("\nPassword does not meet the requirements." +
                    "\n- Alphanumeric characters only,\n- No blank spaces,\n- Minimum of 3 characters," +
                    "\n- Maximum of 8 characters.");
            System.out.print("\nPassword:\n> ");
            inputPassword = input.getString();
        }

        System.out.print("\nConfirm Password:\n> ");
        inputConfirmPassword = input.getString();

        while (!inputPassword.matches(inputConfirmPassword)) {
            System.out.print("\nPasswords do not match.\nPlease retype the same password ");
            System.out.print("\nPassword:\n> ");
            inputConfirmPassword = input.getString();
        }
    }
    /*
    This method reuses the "registerUser" method to register customers, since the fields are
    the same and adds two more fields: Address and Subscription, respectively.
     */

    public void registerCustomerAddress(Input input, Database database){

        connectToDatabase(input, database);

        try {
            System.out.print("\nAddress:\n> ");
            inputAddress = input.getString();
        } catch (InputMismatchException e) {
           System.out.println("\nWrong input. Please try again.\nAddress:\n> ");
            inputAddress = input.getString();
        }

        registerCustomerSubscription(input,database);
    }

    //this method
    public void registerCustomerSubscription(Input input, Database database) {

        database.getConnection();

        System.out.print("\nPlease, please choose a type of Subscription:" +
                "\n0) Press 0 for " + choiceOne+
                "\n1) Press 1 for " + choiceTwo+
                "\n2) Press 2 for " + choiceThree+
                "\n3) Press 3 for " + choiceFour+
                "\n>  ");

            inputSubscription = input.getInt();
            Subscription subscription = Subscription.getChoice(inputSubscription);

            switch (subscription) {

                case ML:
                    System.out.println(choiceOne);
                    break;
                case VL:
                    System.out.println(choiceTwo);
                    break;
                case TV:
                    System.out.println(choiceThree);
                    break;
                case PR:
                    System.out.println(choiceFour);
                    break;

                default:
                    System.out.println("\"" + inputSubscription + "\"" + " is not one of the options.");


        }
    }
  }
