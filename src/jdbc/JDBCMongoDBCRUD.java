package jdbc;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;
import static com.mongodb.client.model.Filters.eq;

public class JDBCMongoDBCRUD {
    public static void main(String[] args) {
        // 1. Connect to MongoDB server
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // 2. Access Database & Collection
        MongoDatabase database = mongoClient.getDatabase("university");
        MongoCollection<Document> collection = database.getCollection("engineeringstudents");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student  2. View Students  3. Update Student  4. Delete Student  5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: addStudent(collection, sc); break;
                case 2: viewStudents(collection); break;
                case 3: updateStudent(collection, sc); break;
                case 4: deleteStudent(collection, sc); break;
                case 5: 
                    mongoClient.close();
                    sc.close();
                    return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    // CREATE
    public static void addStudent(MongoCollection<Document> collection, Scanner sc) {
        System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Department: "); String dept = sc.nextLine();
        System.out.print("Enter First Name: "); String fname = sc.nextLine();
        System.out.print("Enter Last Name: "); String lname = sc.nextLine();
        System.out.print("Enter Passed Year: "); int year = sc.nextInt();
        System.out.print("Enter University Rank: "); int rank = sc.nextInt();

        Document doc = new Document("student_id", id)
                .append("department", dept)
                .append("first_name", fname)
                .append("last_name", lname)
                .append("passed_year", year)
                .append("university_rank", rank);

        collection.insertOne(doc);
        System.out.println("Student Added Successfully!");
    }

    // READ
    public static void viewStudents(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    // UPDATE
    public static void updateStudent(MongoCollection<Document> collection, Scanner sc) {
        System.out.print("Enter Student ID to Update: "); int id = sc.nextInt();
        System.out.print("Enter New University Rank: "); int rank = sc.nextInt();

        var result = collection.updateOne(eq("student_id", id),
                new Document("$set", new Document("university_rank", rank)));

        if (result.getModifiedCount() > 0) System.out.println("Student Updated Successfully!");
        else System.out.println("Student ID Not Found!");
    }

    // DELETE
    public static void deleteStudent(MongoCollection<Document> collection, Scanner sc) {
        System.out.print("Enter Student ID to Delete: "); int id = sc.nextInt();

        var result = collection.deleteOne(eq("student_id", id));
        if (result.getDeletedCount() > 0) System.out.println("Student Deleted Successfully!");
        else System.out.println("Student ID Not Found!");
    }
}
