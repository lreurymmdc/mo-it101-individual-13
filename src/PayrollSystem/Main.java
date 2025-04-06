import java.util.*;

import features.Login;
import features.Menu;
import model.User;

public class Main {

    private static final Scanner scanner = new Scanner(System.in); 
    private static User session; 
    private static boolean isRunning = true;
    

    public static void main(String[] args) {
        System.out.println("Welcome to MotorPH APS!");

        Login loginWall = new Login(scanner);
        session = loginWall.login();

        Menu menuPrompt = new Menu(scanner, session);

        while (isRunning) {
            menuPrompt.showMenu();
            String choice = scanner.nextLine().trim().toUpperCase();
            isRunning = menuPrompt.menuOptions(choice);
        }

        System.out.println("Goodbye!");
    }
}
