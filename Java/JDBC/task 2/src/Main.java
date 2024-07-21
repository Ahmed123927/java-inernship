import java.sql.*;

public class Main {

    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost:5432/firstjdbc";
        final String USER = "postgres";
        final String PASSWORD = "root";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "CREATE TABLE IF NOT EXISTS Employee (" +
                    "id SERIAL PRIMARY KEY, " +
                    "F_Name VARCHAR(50), " +
                    "L_Name VARCHAR(50), " +
                    "Sex CHAR(1), " +
                    "Age INT, " +
                    "Address VARCHAR(100), " +
                    "Phone_Number VARCHAR(15), " +
                    "Vacation_Balance INT DEFAULT 30)";
            Statement statement = connection.createStatement();
            statement.execute(query);


            String insertSQL = "INSERT INTO Employee (F_Name, L_Name, Sex, Age, Address, Phone_Number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            String[][] employees = {
                    {"Ahmed", "Hussein", "M", "50", "123 giza ", "121-546"},
                    {"Mai", "ahmed", "F", "40", "456 giza", "555-5678"},
                    {"mostafa", "Hussein", "M", "50", "123 giza ", "121-546"},
                    {"fatma", "ahmed", "F", "40", "456 giza", "555-5678"},
                    {"omer", "ahmed", "M", "40", "454 giza", "555-7890"}
            };

            for (String[] employee : employees) {
                preparedStatement.setString(1, employee[0]);
                preparedStatement.setString(2, employee[1]);
                preparedStatement.setString(3, employee[2]);
                preparedStatement.setInt(4, Integer.parseInt(employee[3]));
                preparedStatement.setString(5, employee[4]);
                preparedStatement.setString(6, employee[5]);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            String updateVacationSQL = "UPDATE Employee SET Vacation_Balance = 45 WHERE Age > 45";
            String updateNameSQL = "UPDATE Employee SET F_Name = CASE " +
                    "WHEN Sex = 'M' THEN 'Mr. ' || F_Name " +
                    "WHEN Sex = 'F' THEN 'Mrs. ' || F_Name " +
                    "END " +
                    "WHERE Age > 45";

            statement.addBatch(updateVacationSQL);
            statement.addBatch(updateNameSQL);

            statement.executeBatch();

            String Query = "SELECT id, F_Name, L_Name, Vacation_Balance FROM Employee";
            ResultSet resultSet = statement.executeQuery(Query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fName = resultSet.getString("F_Name");
                String lName = resultSet.getString("L_Name");
                int vacationBalance = resultSet.getInt("Vacation_Balance");
                System.out.println("ID: " + id + ", Name: " + fName + " " + lName + ", Vacation Balance: " + vacationBalance);
            }

            resultSet.close();
            statement.close();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
