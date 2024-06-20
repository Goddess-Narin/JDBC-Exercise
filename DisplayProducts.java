
import java.sql.*;

public class DisplayProducts {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Product_DB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rin123";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);


            statement = connection.createStatement();


            String query = "SELECT * FROM Product";
            resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double pricePerUnit = resultSet.getDouble("price_per_unit");
                boolean activeForSell = resultSet.getBoolean("active_for_sell");

                // Display product information in console
                System.out.printf("ID: %d, Name: %s, Price per Unit: %.2f, Active for Sell: %b%n",
                        id, name, pricePerUnit, activeForSell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Close JDBC objects
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
