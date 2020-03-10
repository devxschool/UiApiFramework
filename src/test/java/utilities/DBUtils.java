package utilities;

import java.sql.*;

//JDBC - Java Data base connection
public class DBUtils {

    public static String dbUrl = "jdbc:mysql://3.20.225.112:3306/db_devxschool";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Loads a Class to java classpath.
        Class.forName("com.mysql.jdbc.Driver");

        //Establish the connection to DB -- we need url, username and password
        Connection connection = DriverManager.getConnection(dbUrl,"devxschool", "DevxchooL");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from db_devxschool.employee where salary = (select max(salary) from db_devxschool.employee);");

        while (resultSet.next()){

            String firstName = resultSet.getString("first_name");
            //System.out.println("First Name: " + firstName);

            String lastName = resultSet.getString("last_name");
            //System.out.println("Last Name: " + lastName);

            int salary = resultSet.getInt("salary");
            //System.out.println("Salary: " + salary);

            System.out.println(String.format("First Name:   %s \t \t  Last Name: %s  \t \t Salary: %d", firstName, lastName, salary));
        }


    }
}
