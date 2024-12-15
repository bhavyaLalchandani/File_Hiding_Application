# File Hiding Application

## Description

The **File Hiding Application** is a secure Java-based project built with Maven and MySQL. This application allows users to:

1. **Hide Files**: Securely copy file data to a MySQL database and delete the original file.
2. **View Hidden Files**: View a list of all hidden files stored in the database.
3. **Unhide Files**: Restore hidden files back to their original location.

The application uses **OTP-based authentication** powered by the **JavaMail API** and Google App Passwords to ensure secure email delivery for signup and login.

## Features

- **Secure User Authentication**: Login/signup using OTPs sent to the user's email.
- **File Hiding**: Store file content securely in the database and remove the original file.
- **View Hidden Files**: Display the list of hidden files.
- **Unhide Files**: Restore hidden files back to their original locations.
- **JavaMail API Integration**: Secure email delivery via Gmail App Passwords.
- **Maven Integration**: Use Maven for dependency and build management.

## Tech Stack

- **Java**: Core programming language.
- **Maven**: Project and dependency management.
- **MySQL**: Database for user and file data storage.
- **JavaMail API**: Sending OTPs securely.
- **Google App Passwords**: Secure email authentication.

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 15 or above.
- **Maven**: To manage project dependencies.
- **MySQL**: Database server.
- **Gmail Account**: To send OTPs using App Passwords (turn on 2-Step Verification).

## Installation

Follow these steps to set up and run the **File Hiding Application**:

### 1. Clone the Repository

```bash
git clone https://github.com/bhavyaLalchandani/File_Hiding_Application.git
cd File_Hiding_Application
```

### 2.  Set Up MySQL Database

- Open MySQL and create a new database and tables using the below code:
```bash
CREATE DATABASE filehider;

USE filehider;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(100),
    path VARCHAR(255),
    email VARCHAR(100),
    bin_data BLOB
);
```

- Update the database connection details in **MyConnection.java**:

    - Replace your-username and your-password with your MySQL credentials.
  
```bash
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filehider", "your-username", "your-password");
```

### 3. Configure Gmail for OTP Delivery

- Enable 2-Step Verification in your Gmail account.
- Generate an App Password from Google Account -> Security -> App Passwords.
-  Update the **SendOTPService.java** file with your email and generated app password:
  - Replace your-email@gmail.com and your-app-password with your Gmail address and generated App Password.
![image](https://github.com/user-attachments/assets/9a121eb5-5638-43b7-a4f9-42b8b5e4697a)

```bash
return new PasswordAuthentication("your-email@gmail.com", "your-app-password");
```

### 4. Add Required Maven Dependencies

**Ensure the following dependencies are added to your pom.xml file to include JDBC Driver and JavaMail API:**

- **MySQL JDBC Driver: Add the following dependency to your pom.xml:**

```bash
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.32</version>
</dependency>
```

- **JavaMail API: Add the following dependency to your pom.xml:**

```bash
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>
```
- For more details on these dependencies:

  - MySQL JDBC Maven Dependency: https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.33
  - JavaMail API Maven Dependency: https://mvnrepository.com/artifact/com.sun.mail/javax.mail

### 5. Run the Application
Run the Main.java file to start the application.
