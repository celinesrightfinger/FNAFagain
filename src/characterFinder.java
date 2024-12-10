import java.sql.*;

public class characterFinder {

    // Connection from the main program
    private Connection connection;

    // Constructor for the connection
    public characterFinder(Connection connection) {
        this.connection = connection;
    }

    // Method to search for a character
    public void findCharacter(String animatronicSelection) {
        // Check if the input is empty or null
        if (animatronicSelection == null || animatronicSelection.trim().isEmpty()) {
            System.out.println("You must enter a character!");
            return; // Exit the method early if the input is empty or null
        }

        // SQL query to search for the character
        String selectQuery = "SELECT COUNT(CharacterName) AS character_count FROM robot WHERE CharacterName LIKE ?";

        // For the database
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Preparing the statement with the query
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, "%" + animatronicSelection + "%");

            // Executing the query
            resultSet = preparedStatement.executeQuery();

            // Outputs query result
            if (resultSet.next()) {
                int count = resultSet.getInt("character_count");
                System.out.println(animatronicSelection + " appears in the series " + count + " times.");
            } else {
                // If no data is found
                System.out.println("Could not retrieve character data!");
            }

        } catch (SQLException e) { // If a problem occurs while executing the query
            System.out.println("Error executing SQL query: " + e.getMessage());
        }

    }
}
