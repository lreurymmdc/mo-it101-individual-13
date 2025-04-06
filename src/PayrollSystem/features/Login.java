package features;

import java.util.Scanner;
import data.EmployeeDatabase;
import model.Employee;
import model.User;

public class Login {

    private final Scanner scanner;
    private int userID;

    public Login(Scanner scanner) {
        this.scanner = scanner;
    }

    // Login prompt
    public User login() {
        Employee employee = null;

        while (employee == null) {
            System.out.print("Please enter your employee ID: ");
            if (scanner.hasNextInt()) {
                userID = scanner.nextInt();
                scanner.nextLine(); 

                employee = EmployeeDatabase.getEmployee(userID);
                if (employee == null) {
                    System.out.println("Error: Employee ID not found. Please try again.");
                } 
                else { //Login success message
                    System.out.println("\nWELCOME BACK! " + "\nName: "+ employee.getFullname() + "\nEmployee ID: " + employee.getId() + "\nBirthday: " + employee.getBirthday());
                }
                
            } else {
                System.out.println("Invalid input. Please enter a valid numeric employee ID.");
                scanner.nextLine(); 
            }
        }

        return new User(employee);    }
}
