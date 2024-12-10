import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/*The try is the block of code that gets tested while the catch handles the errors that occur in the try block */

public class FNAFagain {
  public static void main(String[] args) {
    // Database URL, username, and password
    String url = "jdbc:mysql://localhost:3306/FNAF_dummy"; // Replace with your DB name
    String user = "root"; // Replace with your MySQL username
    String password = "Celine133*155"; // Replace with your MySQL password

    // Database connection and resources
    Connection connection = null;
    Scanner scnr = new Scanner(System.in);

    try {
      // Database connection
      connection = DriverManager.getConnection(url, user, password);
      System.out.println("Connected to the database!");

      // Starting the program
      System.out.println("Would you like to access the database now? (Yes/No)");
      String userInput = scnr.nextLine();

      // Handle invalid input for the first question
      while (!userInput.equalsIgnoreCase("Yes") && !userInput.equalsIgnoreCase("No")) {
        System.out.println("Invalid input! Please enter 'Yes' or 'No'.");
        userInput = scnr.nextLine();
      }

      if (userInput.equalsIgnoreCase("No")) {
        System.out.println("Goodbye!");
        return;
      }

      // Main program loop
      while (userInput.equalsIgnoreCase("Yes")) {
        // User interface menu
        System.out.println("What would you like to find out?");
        System.out.println("p - Find out how many times a character appears in total throughout the series");
        System.out.println("f - How many animatronics are in one pizzeria?");
        System.out
            .println("e - How many of each animatronic appears in any game you select (regular, withered, toy, etc.)");
        System.out.println("s - Which animatronic has the highest power stats from which game?");
        System.out.println("q - Quit program!");

        String otherUserInput = scnr.nextLine();

        // User choices
        switch (otherUserInput.toLowerCase()) {
          case "p":
            System.out.println("Which character are you looking for?");
            String animatronicSelection = scnr.nextLine();
            characterFinder charFinder = new characterFinder(connection);
            charFinder.findCharacter(animatronicSelection);
            break;

          case "f":
            System.out.println("Feature for 'f' not implemented yet.");
            break;

          case "e":
            System.out.println("Feature for 'e' not implemented yet.");
            break;

          case "s":
            System.out.println("Feature for 's' not implemented yet.");
            break;

          case "q":
            System.out.println("Goodbye!");
            return; // Exit the program

          default:
            System.out.println("Invalid input. Please try again.");
            break;
        }

        // Ask user again if they want to continue
        System.out.println("Would you like to continue? (Yes/No)");
        userInput = scnr.nextLine();
      }
    }

    catch (SQLException e) { // Connected to the try statement above, will send this out if the database
                             // doesn't connect
      System.out.println("Error connecting to the database: " + e.getMessage());
    }

    System.out.println("Thank you for using our database!");

  }
}
