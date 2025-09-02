package jdbc;
import java.sql.*;
import java.util.Scanner;

public class JdbcCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/university";
    static final String USER = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. Add Student 2. View Students 3. Update Student 4. Delete Student 5. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: createStudent(con, sc); break;
                    case 2: readStudents(con); break;
                    case 3: updateStudent(con, sc); break;
                    case 4: deleteStudent(con, sc); break;
                    case 5: con.close(); sc.close(); return;
                    default: System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void createStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Department: "); String dept = sc.nextLine();
        System.out.print("Enter First Name: "); String fname = sc.nextLine();
        System.out.print("Enter Last Name: "); String lname = sc.nextLine();
        System.out.print("Enter Passed Year: "); int year = sc.nextInt();
        System.out.print("Enter University Rank: "); int rank = sc.nextInt();
        String query = "INSERT INTO engineeringstudents VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id); ps.setString(2, dept); ps.setString(3, fname);
        ps.setString(4, lname); ps.setInt(5, year); ps.setInt(6, rank);
        ps.executeUpdate();
        System.out.println("Student Added Successfully!");
    }

    public static void readStudents(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM engineeringstudents");
        while (rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getInt(6));
        }
    }

    public static void updateStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to Update: "); int id = sc.nextInt();
        System.out.print("Enter New University Rank: "); int rank = sc.nextInt();
        String query = "UPDATE engineeringstudents SET universityrank = ? WHERE student_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, rank); ps.setInt(2, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Student Updated Successfully!");
        else System.out.println("Student ID Not Found!");
    }

    public static void deleteStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to Delete: "); int id = sc.nextInt();
        String query = "DELETE FROM engineeringstudents WHERE student_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Student Deleted Successfully!");
        else System.out.println("Student ID Not Found!");
    }
}
