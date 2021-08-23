package services;

import data.Path;

import java.util.Scanner;

public class MenuService {

    private Scanner sc;

    public MenuService(Scanner sc) {
        this.sc = sc;
    }

    public void userRole(MenuService menu) {
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
                case "1" -> menu.loginMenu("teacher", menu);
                case "2" -> menu.loginMenu("student", menu);
                case "3" -> isLoading = false;
                default -> System.out.println("Please select menu item");
            }
        }
    }

    private void loginMenu(String role, MenuService menu) {
        LoginService signIn = new LoginService(new Scanner(System.in));
        String filename = "";
        String userID = "";
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|*  Welcome to " + role + " login menu  *|");
            System.out.println("| 1 - User registration             |");
            System.out.println("| 2 - User login                    |");
            System.out.println("| 3 - Back to role menu             |");
            System.out.println("| 4 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1" -> signIn.registration(role, menu);
                case "2" -> signIn.login(role, menu);
                case "3" -> isLoading = false;
                case "4" -> System.exit(0);
                default -> System.out.println("Please select menu item");
            }
        }
    }

    protected void studentWorkMenu(String userId) {
        ExamPassingService exam = new ExamPassingService(new Scanner(System.in), new FileService());
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|*  Welcome to student work menu   *|");
            System.out.println("| 1 - Passing the exam              |");
            System.out.println("| 2 - Supplement existing exams     |");
            System.out.println("| 3 - Back to login menu            |");
            System.out.println("| 4 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1" -> exam.passingTheExam(userId);
                case "2" -> System.out.println("under construction");
                case "3" -> isLoading = false;
                case "4" -> System.exit(0);
                default -> System.out.println("Please select menu item");
            }
        }
    }

    protected void teacherWorkMenu(String userId) {
        ExamInsertService exam = new ExamInsertService(new Scanner(System.in), new FileService());
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|*  Welcome to teacher work menu   *|");
            System.out.println("| 1 - Create new exam data          |");
            System.out.println("| 2 - Supplement existing exams     |");
            System.out.println("| 3 - Back to login menu            |");
            System.out.println("| 4 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1" -> exam.insertExam(userId);
                case "2" -> System.out.println("under construction");
                case "3" -> isLoading = false;
                case "4" -> System.exit(0);
                default -> System.out.println("Please select menu item");
            }
        }
    }
}
