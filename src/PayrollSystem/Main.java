import model.Employee;
import data.EmployeeDatabase;
import service.AttendanceConfig;
import service.PayrollConfig;

import java.time.*;
import java.util.*;

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
            System.out.println("A. Punch Clock | B. View Profile | C. View Attendance | D. View Payslip | F. Logout");
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    System.out.println("Clock-in or Clock-out? (A/B):");
                    String clockChoice = scanner.nextLine().trim().toUpperCase();
                    System.out.println("You have successfully "
                            + (clockChoice.equals("A") ? "clocked-in" : "clocked-out") + " at " + timeNow);
                    break;
                case "B":
                    emp.viewEmployeeData();
                    break;
                case "C":
                    System.out.println(
                            "Data is available from June (6) to December (12). Please enter a month number between 6 and 12:");
                    String month = scanner.nextLine();
                    AttendanceConfig.calculateWorkHours(String.valueOf(userID), month);
                    System.out.println("Total Hours: " + AttendanceConfig.totalWorkMinutes / 60);
                    System.out.println("Late: " + AttendanceConfig.totalLateMinutes + " mins");
                    System.out.println("Overtime: " + AttendanceConfig.totalOvertimeMinutes + " mins");
                    break;
                case "D":
                    System.out.println(
                            "Data is available from June (6) to December (12). Please enter a month number between 6 and 12:");
                    String payslipMonth = scanner.nextLine();
                    AttendanceConfig.calculateWorkHours(String.valueOf(userID), payslipMonth);

                    double late = PayrollConfig.lateDeduction(emp, AttendanceConfig.totalLateMinutes);
                    double over = PayrollConfig.overtimeAddition(emp, AttendanceConfig.totalOvertimeMinutes);
                    double sss = PayrollConfig.sssDeduction(emp);
                    double pagibig = PayrollConfig.pagibigDeduction(emp);
                    double philhealth = PayrollConfig.philhealthDeduction(emp);
                    double total = PayrollConfig.totalCompensation(emp, over);
                    double taxable = PayrollConfig.taxableIncome(emp, over, late, sss, pagibig, philhealth);
                    double tax = PayrollConfig.tax(emp, taxable);
                    double deductions = late + sss + pagibig + philhealth + tax;
                    double net = PayrollConfig.netPay(total, deductions);

                    System.out.println("\n--- Payslip ---");
                    System.out.println("Employee Name: " + emp.firstName + " " + emp.lastName);
                    System.out.println("Employee ID: " + emp.employeeID);
                    System.out.println("Position: " + emp.position);
                    System.out.println("\n---Government Information---");
                    System.out.println("SSS: " + emp.sssNumber);
                    System.out.println("Philhealth: " + emp.philhealthNumber);
                    System.out.println("TIN: " + emp.tinNumber);
                    System.out.println("Pag-IBIG: " + emp.pagibigNumber);
                    System.out.println("\n---Compensation---");
                    System.out.println("Basic Salary: P" + emp.basicSalary);
                    System.out.println("Overtime Pay: P" + over);
                    System.out.println("Rice Subsidy: P" + emp.riceSubsidy + ".0");
                    System.out.println("Phone Allowance: P" + emp.phoneAllowance + ".0");
                    System.out.println("Clothing Allowance: P" + emp.clothingAllowance + ".0");
                    System.out.println("\n---Deductions---");
                    System.out.println("Late Deduction: P" + late);
                    System.out.println("SSS: P" + sss);
                    System.out.println("Pag-IBIG: P" + pagibig);
                    System.out.println("Philhealth: P" + philhealth);
                    System.out.println("Tax: P" + tax);

                    System.out.println("\nNet Pay: P" + net);
                    break;
                case "F":
                    System.out.println("Logging out. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

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