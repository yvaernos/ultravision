package ultravision;
/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 */
import java.sql.SQLException;

public class User extends  Register{

    //method to register the new user in the database
    @Override
    public void getLogin(Input input, Database database) {

        connectToDatabase(input, database);
        try {

            String insertValuesQuery = "INSERT INTO users (first_name, last_name, email, username, password)" +
                    "VALUES (?,?,?,?,?)";

            preparedStatement = database.connection.prepareStatement(insertValuesQuery);
            preparedStatement.setString(1, inputName);
            preparedStatement.setString(2, inputSurname);
            preparedStatement.setString(3, inputEmail);
            preparedStatement.setString(4, inputUsername);
            preparedStatement.setString(5, inputPassword);

            preparedStatement.executeUpdate();

            preparedStatement.close();


        } catch (SQLException e) {
           System.out.println("Error 12: "+e);
        }
        System.out.print("\nUser registered. Returning to the main menu");

        Session session = new Session();
        Main main = new Main();

        main.menuOptions(input, session, database);
    }

}
