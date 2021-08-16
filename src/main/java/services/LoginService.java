package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.digest.DigestUtils;
import services.FileService;
import data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginService {

    FileService fs = new FileService();

    public void registration(Scanner sc, String role) {
        String filename = "src/users/" + role + ".json";
        System.out.println("*** User Registration ***");
        String userID = getUniqueUserID(sc, filename);
        String password = getCorrectPassword(sc);
        System.out.println("*** Insert your name ***");
        String name = sc.nextLine();
        System.out.println("** Insert your surname ***");
        String surname = sc.nextLine();
        if(role.equals("teacher")){
            try {
                List<Person> teachers = fs.readFileAsList(filename);
                teachers.add(new Teacher(userID, password, name, surname, role));
                fs.writeUsersToFile(fs, filename, teachers);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(role.equals("student")){
            try {
                List<Person> students = fs.readFileAsList(filename);
                students.add(new Student(userID, password, name, surname, role));
                fs.writeUsersToFile(fs, filename, students);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("User successfully registered");
   }




    public void login(Scanner sc, String role) {
        String filename = "src/users/" + role + ".json";
        System.out.println("*** User login ***");
        System.out.println("Insert username");
        String userID = sc.nextLine();
        System.out.println("Insert password");
        String password = sc.nextLine();

        String encodedPassword = null;
        try {
            encodedPassword = fs.checkForId(fs, filename, userID);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (encodedPassword != null && encodedPassword.equals(DigestUtils.sha256Hex(password))) {
            System.out.println("User login successfully");
        } else {
            System.out.println("Login error, please check credentials or create account");
        }
    }




    private String getCorrectPassword(Scanner sc) {
        String password;
        String repeatPassword;
        String text = "";
        do {
            System.out.println(text);
            System.out.println("Please insert password");
            password = sc.nextLine();
            System.out.println("Repeat you password");
            repeatPassword = sc.nextLine();
            text = "Passwords not equals";
        } while (!password.equals(repeatPassword));

        return DigestUtils.sha256Hex(password);
    }





    private String getUniqueUserID(Scanner sc, String filename) {
        String userID = "";
        boolean searchResult = true;
        fs.createFile(filename);

//        do {
        System.out.println("Please enter user ID");
        String tempUserID = sc.nextLine();
//        try {
//            if (fs.checkForId(fs, filename, tempUserID) == null) {
                userID = tempUserID;
//                searchResult = false;
//            } else {
//                System.out.println("This name exists. Please insert another one or login in.");
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        } while (searchResult);

//
//
//
//        String userID;
//        String text = "Please insert userID";
//        do {
//            System.out.println(text);
//            userID = sc.nextLine();
//            text = "This name exist please insert another one";
//        } while (users.get(userID) != null);

        return userID;
    }
}

