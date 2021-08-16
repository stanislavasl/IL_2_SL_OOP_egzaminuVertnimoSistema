package services;

import java.util.Scanner;

public class MenuService {

    private Scanner sc;

    public MenuService(Scanner sc) {
        this.sc = sc;
    }

    public void userRole(MenuService menu) {
//        Scanner sc = new Scanner(System.in);

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
                    menu.loginMenu(role, menu);
                    break;
                }
                case "2": {
                    String role = "student";
                    menu.loginMenu(role, menu);
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


    private void loginMenu(String role, MenuService menu) {
        LoginService signIn = new LoginService(new Scanner(System.in));
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|*  Welcome to " + role + " login menu  *|");
            System.out.println("| 1 - User registration             |");
            System.out.println("| 2 - User login                    |");
            System.out.println("| 3 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1": {
                    signIn.registration(role, menu);
                    break;
                }
                case "2": {
                    signIn.login(role, menu);
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


    public void workMenu(String role, MenuService menu){
        if(role.equals("teacher")){
            menu.teacherWorkMenu();
        }
//        if(role.equals("student")){
//            menu.studentWorkMenu();
//        }

    }



   private void teacherWorkMenu() {
        ExamService exam = new ExamService(new Scanner(System.in));
        boolean isLoading = true;
        while (isLoading) {
            System.out.println(" ___________________________________");
            System.out.println("|*  Welcome to teacher work menu   *|");
            System.out.println("| 1 - Create new exam data          |");
            System.out.println("| 2 - Supplement existing exams     |");
            System.out.println("| 3 - Exit                          |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1": {
                    exam.insertExam();
                    break;
                }
                case "2": {

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
