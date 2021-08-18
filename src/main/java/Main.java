import data.Path;
import services.MenuService;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        MenuService menu = new MenuService(new Scanner(System.in));

        menu.userRole(menu);
    }
}
