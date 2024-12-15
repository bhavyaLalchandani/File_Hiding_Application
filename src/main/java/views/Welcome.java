package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the File Hiding Application");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Signup");
        System.out.println("Press 0 to Exit");

        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (choice) {
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        System.out.println("Enter your Email ID: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String generatedOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, generatedOTP);
                System.out.println("\nEnter OTP sent to your Email ID: ");
                String otp = sc.nextLine();
                if (otp.equals(generatedOTP)){
                    System.out.println("---------------\nWelcome\n---------------");
                    new UserView(email).home();
                } else {
                    System.out.println("---------------\nWrong OTP\n---------------");
                }
            } else {
                System.out.println("User Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name");
        String name = sc.nextLine();

        System.out.println("Enter Email");
        String email = sc.nextLine();

        String generatedOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, generatedOTP);

        System.out.println("Enter the OTP sent to your Email ID: ");
        String otp = sc.nextLine();

        if(otp.equals(generatedOTP)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 1 -> {
                    System.out.println("User Registered Successfully");
                    break;
                }
                case 0 -> {
                    System.out.println("User Already Exists");
                    break;
                }
            }
        } else {
            System.out.println("Wrong OTP");
        }
    }
}
