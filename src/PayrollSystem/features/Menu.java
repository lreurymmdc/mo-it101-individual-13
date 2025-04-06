package features;

import java.util.Scanner;
import model.User;
import util.Display;

public class Menu {

    private final Scanner scanner;
    private final User user;
    private final Attendance attendanceFeature;
    private final Payroll payrollFeature;

    public Menu(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
        this.attendanceFeature = new Attendance(scanner, user);
        this.payrollFeature = new Payroll(scanner, user);
    }

    //Prompt users with the menu options
    public void showMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("A. Punch Clock | B. View Profile | C. View Attendance (Weekly) | D. View Payslip (Weekly) | F. Logout");
    }

    //Conditionals
    public boolean menuOptions(String choice) {
        switch (choice) {
            case "A": //punch clock
                attendanceFeature.punchClock();
                break;
            case "B": //view profile
                Display view = new Display(user.getEmployee());
                view.employeeDataView();
                break;
            case "C": //view attendance report
                attendanceFeature.viewAttendance();
                break;
            case "D": //view payslip report
                payrollFeature.viewPayslip();
                break;
            case "F": //logout
                return false;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        return true;
    }
}
