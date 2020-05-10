package ultravision;

/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */

import java.sql.SQLException;

class Customer extends Register{

    Session session = new Session();
    Main main = new Main();

    String customerUsername;

    public Customer() {

    }

    protected void addCustomer(Input input, Database database) {
        registerCustomerAddress(input, database);
        Subscription subscription = Subscription.getChoice(inputSubscription);

        try {

            String insertValuesQuery =  "INSERT INTO customers (first_name, last_name, email, username, password, address, subscription) VALUES (?,?,?,?,?,?,?)";

            preparedStatement = database.connection.prepareStatement(insertValuesQuery);
            preparedStatement.setString(1, inputName);
            preparedStatement.setString(2, inputSurname);
            preparedStatement.setString(3, inputEmail);
            preparedStatement.setString(4, inputUsername);
            preparedStatement.setString(5, inputPassword);
            preparedStatement.setString(6, inputAddress);
            preparedStatement.setString(7, subscription.getSubscription());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error 13: "+e);
            main.mainMenu(input, session);
        }

        System.out.print("\nCustomer registered. Returning to the main menu");

        main.mainMenu(input, session);
    }

    protected String getCustomer(Input input, Database database) {

        try {

            System.out.print("\nCustomer's username:\n> ");
           String  inputCustomerUsername =  input.getString();

            while (!usernamePattern.matcher(inputCustomerUsername).matches()) {

                System.out.print("\nUsername does not meet the requirements:" +
                        "\n- No blank spaces,\n- Lowercase letters only,\n- Minimum of 3 characters," +
                        "\n- Maximum of 8 characters.\nCustomer's username:\n> ");
                inputCustomerUsername = input.getString();
            }
            try {
                database.getConnection();

                preparedStatement = database.connection.prepareStatement("SELECT * FROM customers WHERE username = ? ");
                preparedStatement.setString(1, inputCustomerUsername);
                resultSet = preparedStatement.executeQuery();

                if (!resultSet.next()) {
                    System.out.print("\nUsername does not exist.");
                    main.mainMenu(input, session);
                }
            } catch (SQLException e) {
                System.out.println("Error 16: "+ e);

            }
            //Query to retrieve first and last name records based on the user input.
            String getValuesQuery = "SELECT first_name, last_name, email, username, password, address, subscription" +
                    " FROM customers WHERE username=?";

            database.getConnection();
            // Now you can extract all the records
            preparedStatement = database.connection.prepareStatement(getValuesQuery);
            preparedStatement.setString(1,inputCustomerUsername);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                //Retrieve by column username
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String subscription = resultSet.getString("subscription");

                customerUsername = username;

                System.out.println("First Name: " + firstName.substring(0, 1).toUpperCase() + firstName.substring(1) +
                        "\nLast Name: " + lastName.substring(0, 1).toUpperCase()+lastName.substring(1) +
                        "\nEmail: "+ email  +"\nUsername: "+ username  +
                        "\nPassword: "+ password+
                        "\nAddress: "+ address +
                        "\nSubscription: "+ subscription.substring(0, 1).toUpperCase()+subscription.substring(1));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error 14: "+e);
            main.mainMenu(input, session);
        }

        return customerUsername;
    }

    protected  void searchCustomer(Input input, Database database) {
        getCustomer(input, database);
        main.mainMenu(input, session);

    }

    protected void updateCustomer(Input input, Database database) {
        getCustomer(input, database);

        System.out.print("\nSelect one of the options below:" +
                "\n1) Press 1 to change the first name" +
                "\n2) Press 2 to change the last name " +
                "\n3) Press 3 to change the email" +
                "\n4) Press 4 to change the address" +
                "\n5) Press 5 to change the username" +
                "\n6) Press 6 to change the password" +
                "\n7) Press 7 to change the subscription"+
                "\n8) Press 8 to return to the previous menu"+"\n> ");

        boolean choice = false;
        String field = "";
        while (!choice) {
            try {

                int updateCustomer = input.getInt();
                switch (updateCustomer) {

                    case 1:
                        field = "first name";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerName = input.getString();
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET first_name = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerName);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 13: Can't perform this task due to error " + e);
                        }
                        break;

                    case 2:
                        field = "last name";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerSurname = input.getString();
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET last_name = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerSurname);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 14: Can't perform this task due to error " + e);
                        }
                        break;

                    case 3:
                        field = "email";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerEmail = input.getString();
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET email = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerEmail);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 15: Can't perform this task due to error " + e);
                        }
                        break;

                    case 4:
                        field = "address";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerAddress = input.getString();
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET address = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerAddress);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 16: Can't perform this task due to error " + e);
                        }
                        break;

                    case 5:
                        field = "username";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerUsername = input.getString();
                        if (!usernamePattern.matcher(newCustomerUsername).matches()) {
                            System.out.print("\nUsername does not meet the requirements:" +
                                    "\n- No blank spaces,\n- Lowercase letters only,\n- Minimum of 3 characters," +
                                    "\n- Maximum of 8 characters.\nWhat is the new customer's " + field + ":" + "\n> ");
                            newCustomerUsername = input.getString();
                        } else if (usernamePattern.matcher(newCustomerUsername).matches()) {
                            try {
                                preparedStatement = database.connection.prepareStatement("SELECT * FROM customers WHERE username = ? ");
                                preparedStatement.setString(1, newCustomerUsername);
                                resultSet = preparedStatement.executeQuery();

                                while (resultSet.next()) {
                                    System.out.print("\nUsername already exists. Please choose another one." +
                                            "\nWhat is the new customer's " + field + ":" + "\n> ");
                                    newCustomerUsername = input.getString();
                                }
                            } catch (SQLException e) {
                                System.out.println("Error 17: " + e);
                            }
                        }
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET username = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerUsername);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 18: Can't perform this task due to error " + e);
                        }
                        break;

                    case 6:
                        field = "password";
                        System.out.print("\nWhat is the new customer's " + field + ":" + "\n> ");
                        String newCustomerPassword = input.getString();
                        try {
                            database.getConnection();
                            //Execute a query
                            String query = "UPDATE customers SET password = ? WHERE username = ?";
                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, newCustomerPassword);
                            preparedStatement.setString(2, customerUsername);
                            int i = preparedStatement.executeUpdate();
                            if (i > 0) {
                                System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");
                                preparedStatement.close();
                                main.mainMenu(input, session);
                            } else {
                                System.out.println("An error occurred");
                                input.close();
                                main.mainMenu(input, session);
                            }
                            preparedStatement.close();
                            database.resultSet.close();
                            main.mainMenu(input, session);
                        } catch (SQLException e) {
                            System.out.println("Error 19: Can't perform this task due to error " + e);
                        }
                        break;

                    case 7:
                        System.out.println(customerUsername);
                        field = "subscription";

                        Subscription subscription = Subscription.getChoice(inputSubscription);

                        registerCustomerSubscription(input, database);

                        try {

                            String query = "UPDATE customers SET subscription = ? WHERE username = ?)";

                            preparedStatement = database.connection.prepareStatement(query);
                            preparedStatement.setString(1, subscription.getSubscription());
                            preparedStatement.setString(2, customerUsername);

                            preparedStatement.executeUpdate(query);

                            preparedStatement.close();

                        } catch (SQLException e) {
                            System.out.println("Error 21: " + e);
                            main.mainMenu(input, session);
                        }

                        System.out.println("\n" + field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully.");

                        main.mainMenu(input, session);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    @Override
    public void getLogin(Input input, Database database) {

    }
}
