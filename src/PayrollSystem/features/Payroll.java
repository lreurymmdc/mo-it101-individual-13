package features;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import configurations.AttendanceConfig;
import model.User;
import util.Display;
import util.TimeVariablesFormat;

public class Payroll {
    private final Scanner scanner;
    private final User session;

    public Payroll(Scanner scanner, User session) {
        this.scanner = scanner;
        this.session = session;
    }
    
    public void viewPayslip() {

       try {
            System.out.println("You are trying to view your weekly payslip information");

            System.out.println("Enter start date (MM/DD/YYYY): ");
            String paySlipStartDate = scanner.nextLine();
            System.out.println("Enter end date (MM/DD/YYYY): ");
            String paySlipEndDate = scanner.nextLine();

            // ensure date input are parseable
            LocalDate paySlipStartDateFormat = LocalDate.parse(paySlipStartDate, TimeVariablesFormat.DATE_FORMAT);
            LocalDate paySlipEndDateFormat = LocalDate.parse(paySlipEndDate, TimeVariablesFormat.DATE_FORMAT);
    
            int userID = session.getEmployee().getId();
            AttendanceConfig.calculateWorkHours(String.valueOf(userID), paySlipStartDate, paySlipEndDate);
    
            // ensure date input does not exceed 7 days
            if (ChronoUnit.DAYS.between(paySlipStartDateFormat, paySlipEndDateFormat) < 8 && paySlipEndDateFormat.isAfter(paySlipStartDateFormat)) {
                Display payslipView = new Display(session.getEmployee());
                payslipView.paySlipView();

            } 
            else if (paySlipEndDateFormat.isBefore(paySlipStartDateFormat)) {
                System.out.println("Error: Start date must not be earlier than the end date. Please try again.");
            }
            else {
                System.out.println("Error: Date range must not exceed 7 days. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }

    }
}
