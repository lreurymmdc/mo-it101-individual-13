import model.Employee;
import data.EmployeeDatabase;
import service.AttendanceConfig;
import service.PayrollConfig;
import util.DisplayView;
import util.TimeVariablesFormat;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LocalDate dateNow = LocalDate.now();
    static LocalTime timeNow = LocalTime.now();

    public static void main(String[] args) {
        System.out.println("Welcome to MotorPH APS!");
        int userID = login();
        Employee emp = EmployeeDatabase.getEmployee(userID);
        String choice = "";

        while (!choice.equalsIgnoreCase("F")) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("A. Punch Clock | B. View Profile | C. View Attendance (Weekly) | D. View Payslip (Weekly) | F. Logout");
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A": //Punch clock feature
                    System.out.println("Clock-in or Clock-out? (A/B):");
                    String clockChoice = scanner.nextLine().trim().toUpperCase();
                    System.out.println("You have successfully "
                            + (clockChoice.equals("A") ? "clocked-in" : "clocked-out") + " at " + timeNow);
                    break;
                case "B": //Viewing employee information
                    DisplayView attendanceView = new DisplayView(emp);
                    attendanceView.viewEmployeeData();
                    break;
                case "C": //Viewing weekly attendance
                    System.out.println(
                            "Enter start date: (MM/DD/YYYY)");
                    String attendanceStartDate = scanner.nextLine();
                    System.out.println(
                            "Enter end date: (MM/DD/YYYY)");
                    String attendanceEndDate = scanner.nextLine();
                    AttendanceConfig.calculateWorkHours(String.valueOf(userID), attendanceStartDate, attendanceEndDate);
                    
                    //ensure date input are parseable.
                    LocalDate attendanceStartDateFormat = LocalDate.parse(attendanceStartDate, TimeVariablesFormat.DATE_FORMAT);
                    LocalDate attendanceEndDateFormat = LocalDate.parse(attendanceEndDate, TimeVariablesFormat.DATE_FORMAT);

                    //ensure date input does not exceed 7 days.
                    if (ChronoUnit.DAYS.between(attendanceStartDateFormat, attendanceEndDateFormat) < 8) {
                        System.out.println("Total Hours: " + PayrollConfig.round(AttendanceConfig.totalWorkMinutes / 60) + " hours");
                        System.out.println("Late: " + PayrollConfig.round(AttendanceConfig.totalLateMinutes / 60) + " hours");
                        System.out.println("Overtime: " + PayrollConfig.round(AttendanceConfig.totalOvertimeMinutes / 60) + " hours");
                    }
                    else {
                        System.out.println("Date range must not exceed 7 days.");
                    }
                    break;
                case "D": //Viewing weekly payslip information
                    System.out.println(
                            "Enter start date: (MM/DD/YYYY)");
                    String paySlipStartDate = scanner.nextLine();
                    System.out.println(
                            "Enter end date: (MM/DD/YYYY)");
                    String payslipEndDate = scanner.nextLine();
                    AttendanceConfig.calculateWorkHours(String.valueOf(userID), paySlipStartDate, payslipEndDate);

                    //ensure date input are parseable.
                    LocalDate paySlipStartDateFormat = LocalDate.parse(paySlipStartDate, TimeVariablesFormat.DATE_FORMAT);
                    LocalDate payslipEndDateFormat = LocalDate.parse(payslipEndDate, TimeVariablesFormat.DATE_FORMAT);

                    //ensure date input does not exceed 7 days.
                    if (ChronoUnit.DAYS.between(paySlipStartDateFormat, payslipEndDateFormat) < 8) {
                        DisplayView payslipView = new DisplayView(emp);
                        payslipView.paySlipView();
                    }
                    else {
                        System.out.println("Date range must not exceed 7 days.");
                    }
                    break;
                case "F": //Exit the program
                    System.out.println("Logging out. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
//Simulate login wall. No access unless employee ID matches one in the database
    static int login() {
        int id = 0;
        while (true) {
            System.out.print("Enter your Employee ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (EmployeeDatabase.containsEmployee(id)) {
                    Employee emp = EmployeeDatabase.getEmployee(id);
                    System.out.println("\nWelcome " + emp.firstName + " " + emp.lastName + "!");
                    return id;
                } else {
                    System.out.println("Invalid Employee ID. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}