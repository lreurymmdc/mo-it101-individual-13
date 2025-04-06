package features;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import configurations.AttendanceConfig;
import model.User;
import util.Display;
import util.TimeVariablesFormat;

public class Attendance {

    private final Scanner scanner;
    private final User session;

    public Attendance(Scanner scanner, User session) {
        this.scanner = scanner;
        this.session = session;
    }

    //Able to display clock-in/clock-out time of an employee
    public void punchClock() {
        System.out.println("Clock-in or Clock-out? (A/B):");
        String choice = scanner.nextLine().trim().toUpperCase();
        if (choice.equals("A")) {
            System.out.println("Clocked-in at " + LocalTime.now());
        } else if (choice.equals("B")) {
            System.out.println("Clocked-out at " + LocalTime.now());
        } else {
            System.out.println("Invalid input.");
        }
    }

    //Retrieve employee attendance hours within the given date range
    public void viewAttendance() {
        try {
            System.out.println("You are trying to view your weekly attendance information");

            System.out.println("Enter start date (MM/DD/YYYY): ");
            String attendanceStartDate = scanner.nextLine();
            System.out.println("Enter end date (MM/DD/YYYY): ");
            String attendanceEndDate = scanner.nextLine();

            // ensure date input are parseable
            LocalDate attendanceStartDateFormat = LocalDate.parse(attendanceStartDate, TimeVariablesFormat.DATE_FORMAT);
            LocalDate attendanceEndDateFormat = LocalDate.parse(attendanceEndDate, TimeVariablesFormat.DATE_FORMAT);
    
            int userID = session.getEmployee().getId();
            AttendanceConfig.calculateWorkHours(String.valueOf(userID), attendanceStartDate, attendanceEndDate);
    
            // ensure date input does not exceed 7 days
            if (ChronoUnit.DAYS.between(attendanceStartDateFormat, attendanceEndDateFormat) < 8 && attendanceEndDateFormat.isAfter(attendanceStartDateFormat)) {                
                Display attendanceView = new Display(session.getEmployee());
                attendanceView.attendanceView();
            } 
            else if (attendanceEndDateFormat.isBefore(attendanceStartDateFormat)) {
                System.out.println("Error: Start date must not be earlier than the end date. Please try again.");
            }
            else {
                System.out.println("\nError: Date range must not exceed 7 days. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }
    
}
