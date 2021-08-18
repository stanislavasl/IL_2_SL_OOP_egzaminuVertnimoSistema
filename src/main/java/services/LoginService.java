package services;

import data.Person;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.Scanner;

public class LoginService {

    private final Scanner sc;

    public LoginService(Scanner sc) {
        this.sc = sc;
    }

    FileService fs = new FileService();

    public void registration(String role, MenuService menu) {
        String filename = "src/files/users/" + role + ".json";
        System.out.println("*** User Registration ***");
        String userID = getUniqueUserID(filename);
        String password = getCorrectPassword();
        System.out.println("*** Insert your name ***");
        String name = sc.nextLine();
        System.out.println("** Insert your surname ***");
        String surname = sc.nextLine();

        Map<String, Person> teachers = fs.readUserFromFile(filename);
        teachers.put(userID, new Person (userID, password, name, surname, role));
        fs.writeUserToFile(filename, teachers);

        System.out.println("User successfully registered");
        switch(role) {
            case "teacher" -> menu.teacherWorkMenu(userID);
            case "student" -> menu.studentWorkMenu(userID);
        }
    }

    public void login(String role, MenuService menu) {
        String filename = "src/files/users/" + role + ".json";
        System.out.println("*** User login ***");
        System.out.println("Insert user ID");
        String userID = sc.nextLine();
        System.out.println("Insert password");
        String password = sc.nextLine();

        String encodedPassword = fs.readUserFromFile(filename).get(userID).getPassword();

        if(encodedPassword != null && encodedPassword.equals(DigestUtils.sha256Hex(password))){
            System.out.println("User login successfully");
            switch(role) {
                case "teacher" -> menu.teacherWorkMenu(userID);
                case "student" -> menu.studentWorkMenu(userID);
            }
        }else {
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
            password = sc.nextLine();
            System.out.println("Repeat you password");
            repeatPassword = sc.nextLine();
            text = "Passwords not equals";
        } while(!password.equals(repeatPassword));

        return DigestUtils.sha256Hex(password);
    }

    private String getUniqueUserID(String filename) {
        String userID;
        String text = "Please insert user ID";
        do {
            System.out.println(text);
            userID = sc.nextLine();
            text = "This name exist please insert another one";
        } while(fs.readUserFromFile(filename).get(userID) != null);
        return userID;
    }

}
