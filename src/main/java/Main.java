import services.MenuService;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        MenuService menu = new MenuService(new Scanner(System.in));

        menu.userRole(menu);
//
//        ExamService exam = new ExamService(new Scanner(System.in), new FileService());
//        exam.passingTheExam();

//        FileService fs = new FileService();
//        fs.readExamsToFile();

//        LoginService ls = new LoginService(new Scanner(System.in));
//        ls.readUsersFromFile();




    }
}
