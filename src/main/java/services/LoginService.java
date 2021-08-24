package services;

import data.Path;
import data.Person;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginService {

    private final Scanner sc;

    public LoginService(Scanner sc) {
        this.sc = sc;
    }

    FileService fs = new FileService();

    public void registration(String role,MenuService menu) {
        String filename ="";
        switch (role) {
            case "teacher" -> filename = Path.TEACHERS.getCataloque();
            case "student" -> filename = Path.STUDENTS.getCataloque();
        }
        System.out.println("*** User Registration ***");
        String userID = getUniqueUserID(filename);
        String password = getCorrectPassword();
        System.out.println("*** Insert your name ***");
        String name = fs.getCorrectValue();
        System.out.println("** Insert your surname ***");
        String surname = fs.getCorrectValue();

        Map<String, Person> persons = new HashMap<>();
        persons = fs.readData(filename, persons);

        persons.put(userID, new Person (userID, password, name, surname));
        fs.writeData(filename, persons);

        System.out.println("User successfully registered");
        switch (role) {
            case "teacher" -> menu.teacherWorkMenu(userID);
            case "student" -> menu.studentWorkMenu(userID);
        }
    }

    public void login(String role, MenuService menu) {
        String filename ="";
        switch (role) {
            case "teacher" -> filename = Path.TEACHERS.getCataloque();
            case "student" -> filename = Path.STUDENTS.getCataloque();
        }
        System.out.println("*** User login ***");
        System.out.println("Insert user ID");
        String userID = fs.getCorrectValue();
        System.out.println("Insert password");
        String password = fs.getCorrectValue();

        Map<String, Person> persons = fs.readUserFromFile(filename);
        String encodedPassword = persons.get(userID).getPassword();
        if(encodedPassword != null && encodedPassword.equals(DigestUtils.sha256Hex(password))){
            System.out.println("User login successfully");
            switch (role) {
                case "teacher" -> menu.teacherWorkMenu(userID);
                case "student" -> menu.studentWorkMenu(userID);
            }
        } else {
            System.out.println("Login error, please check credentials or create account");
        }
    }

    private String getCorrectPassword() {
        String password;
        String repeatPassword;
        String text = "";
        do {
            System.out.println(text);
            System.out.println("Please insert password");
            password = fs.getCorrectValue();
            System.out.println("Repeat you password");
            repeatPassword = fs.getCorrectValue();
            text = "Passwords not equals";
        } while(!password.equals(repeatPassword));

        return DigestUtils.sha256Hex(password);
    }

    private String getUniqueUserID(String filename) {
        String userID;
        Map <String, String> list = new HashMap<>();
        list = fs.readData(filename, list);
        String text = "Please insert user ID";
        do {
            System.out.println(text);
            userID = fs.getCorrectValue();
            text = "This name exist please insert another one";
        } while(/*fs.readUserFromFile(filename)*/list.get(userID) != null);
        return userID;
    }

}
