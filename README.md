Java JDBC + MySQL CRUD project, along with MongoDB CRUD instructions for a plain Java project (no Maven).

Here’s the README file:

---

```markdown
# Java JDBC with MySQL & MongoDB CRUD Project

## 📌 Overview
This project demonstrates how to perform **CRUD (Create, Read, Update, Delete)** operations in Java using:
- **MySQL Database** with **JDBC**
- **MongoDB Database** with **MongoDB Java Driver**

It is a **menu-driven console application** where the user can add, view, update, or delete student records.

---

## 🛠️ Prerequisites
Before running the project, ensure the following software is installed:

1. **Java JDK** (>= 8) → [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. **MySQL Server** → [Download MySQL](https://dev.mysql.com/downloads/)
3. **MongoDB Server** → [Download MongoDB](https://www.mongodb.com/try/download/community)
4. **MySQL JDBC Driver** → [Download MySQL Connector](https://dev.mysql.com/downloads/connector/j/)
5. **MongoDB Java Driver JARs** (For plain Java project)
   - `mongodb-driver-sync-4.11.1.jar`
   - `mongodb-driver-core-4.11.1.jar`
   - `bson-4.11.1.jar`

Add the above JAR files to:
```

Project → Properties → Java Build Path → Add External JARs

```

---

## 📂 Project Structure
```

src/
├── jdbc/
│   ├── JdbcCRUD.java        # MySQL CRUD Operations using JDBC
│   └── jdbcProject.java     # Simple MySQL SELECT Example
├── MongoDBCRUD.java         # MongoDB CRUD Operations

````

---

## 🏗️ MySQL Setup
### 1. Start MySQL and create database
```sql
mysql -u root -p
CREATE DATABASE university;
USE university;
````

### 2. Create Table

```sql
CREATE TABLE engineeringstudents (
    student_id INT PRIMARY KEY,
    department VARCHAR(25),
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    passedyear INT,
    universityrank INT
);
```

---

## ⚡ Running the MySQL CRUD Program

1. **Set DB Credentials** in `JdbcCRUD.java`

```java
static final String URL = "jdbc:mysql://localhost:3306/university";
static final String USER = "root";
static final String PASSWORD = "your_password";
```

2. **Compile and Run**

```bash
javac JdbcCRUD.java
java JdbcCRUD
```

3. **Menu Options**

```
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Exit
```

---

## 🍃 MongoDB Setup

1. Start MongoDB server:

```bash
mongod
```

2. Database and collection will be created automatically after first insert.

---

## ⚡ Running the MongoDB CRUD Program

1. **Set MongoDB Connection** in `MongoDBCRUD.java`

```java
MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
MongoDatabase database = mongoClient.getDatabase("university");
MongoCollection<Document> collection = database.getCollection("engineeringstudents");
```

2. **Compile and Run**

```bash
javac MongoDBCRUD.java
java MongoDBCRUD
```

3. **Menu Options**

```
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Exit
```

---

## 🧠 Key Concepts Used

* **JDBC Driver** for MySQL connection
* **PreparedStatement** for secure queries (SQL Injection prevention)
* **ResultSet** for reading query results
* **MongoDB Java Driver** for MongoDB CRUD
* **Menu-driven program** for user-friendly interaction

---

## 📜 License

This project is open-source. You can modify and use it freely for learning purposes.

---

## ✨ Author

Developed by **Chitrala.Sai Siddharth Kumar** as a learning project for **Java Database Connectivity**.

<h3>📊 Flowchart</h3>
<p>Here is the visual flow of the CRUD operations:</p>
<a href="https://raw.githubusercontent.com/SiddharthChitrala/Jdbc_practice/main/images/flowchart.png" target="_blank">
  <img src="https://raw.githubusercontent.com/SiddharthChitrala/Jdbc_practice/main/images/flowchart.png" alt="Flowchart" width="400"/>
</a>




