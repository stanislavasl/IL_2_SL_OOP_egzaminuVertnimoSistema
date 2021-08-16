import services.FileService;
import services.LoginService;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        main.userRole(sc, main);


//        data.Student student = new data.Student(123, "Petras", "Petraitis");
//        data.Exam exam = new data.Exam(8, "OOP", "test");

//        File file = main.createFile(sc, student);
//        List<data.Answers> answersList = main.fillInTheAnswers(sc);
//        main.writeToFile(file, student, exam, answersList);


    }

    private void userRole(Scanner sc, Main main) {

        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("| Please, select user role:         |");
            System.out.println("| 1 - Teacher                       |");
            System.out.println("| 2 - Student                       |");
            System.out.println("| 3 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1": {
                    String role = "teacher";
                    main.loginMenu(sc, role);
                    break;
                }
                case "2": {
                    String role = "student";
                    main.loginMenu(sc, role);
                    break;
                }
                case "3": {
                    isLoading = false;
                    break;
                }
                default: {
                    System.out.println("Please select menu item");
                    break;
                }
            }
        }
    }


    private void loginMenu(Scanner sc, String role) {
        LoginService signIn = new LoginService();
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|****  Welcome to " + role + " menu  ****|");
            System.out.println("| 1 - User registration             |");
            System.out.println("| 2 - User login                    |");
            System.out.println("| 3 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1": {
                    signIn.registration(sc, role);
                    break;
                }
                case "2": {
                    signIn.login(sc, role);
                    break;
                }
                case "3": {
                    isLoading = false;
                    break;
                }
                default: {
                    System.out.println("Please select menu item");
                    break;
                }
            }
        }
    }




}
