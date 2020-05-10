package ultravision;

import java.util.Scanner;

public final class Input {

    /** Since only one user Input is not enough for a system of this scale,
     it only made sense to create a separate class to hold all user inputs.
     This singleton creates a code reusability and avoids repetition. */
    private final Scanner userInput;

        Input() {

            userInput = new Scanner(System.in);
        }

        //this method holds all users input integers
        public int getInt() {

        return userInput.nextInt();
        }

        //this method holds all users input characters.
        public String getString() {

        return userInput.next();
        }

        //this method holds user input with whitespace.
        public String getNextLine() {

            return userInput.nextLine();
        }

        //a method that simply closes the scanner and shuts the system down.
        public void close() {
            userInput.close();
            System.exit(0);
        }

}