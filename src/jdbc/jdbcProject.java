package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcProject{
    public static void main(String [] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/university";
        String user ="root";
        String password = "password";
        String query ="SELECT * FROM engineeringstudents";
        try {
            // Load MySQL JDBC Driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected to MySQL database!");

        }catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        try{
            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            // Create statement and execute query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String UniversityData="";
                for(int i = 1; i<=6; i++){
                    UniversityData += rs.getString(i) + ":";

                }
                System.out.println(UniversityData);
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();

        }catch (SQLException e) {
            System.out.println("SQL error occurred.");
            e.printStackTrace();
        }

    }
}
