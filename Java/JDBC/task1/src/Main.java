import java.sql.*;

public class Main {

    public static void main(String[] args) {
         final String URL = "jdbc:postgresql://localhost:5432/firstjdbc";
         final String USER = "postgres";
         final String PASSWORD = "root";

        String query = "SELECT id, F_Name FROM employee";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection!= null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}