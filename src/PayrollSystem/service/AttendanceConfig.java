package service;

import util.TimeVariablesFormat;
import java.nio.file.*;
import java.io.*;
import java.time.*;

public class AttendanceConfig {
    public static int totalLateMinutes = 0;
    public static int totalWorkMinutes = 0;
    public static int totalOvertimeMinutes = 0;

//Calculate working hours of an Employee based on a given starting date and end date.
    public static void calculateWorkHours(String employeeID, String startDate, String endDate) {
        totalLateMinutes = 0;
        totalWorkMinutes = 0;
        totalOvertimeMinutes = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(TimeVariablesFormat.ATTENDANCE_FILE))) {

            //Convert string input to LocalDate parseable data type.
            LocalDate start = LocalDate.parse(startDate, TimeVariablesFormat.DATE_FORMAT);
            LocalDate end = LocalDate.parse(endDate, TimeVariablesFormat.DATE_FORMAT);

            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6 || !data[0].trim().equals(employeeID))
                    continue;

                LocalDate date = LocalDate.parse(data[3].trim(), TimeVariablesFormat.DATE_FORMAT);

                //Ensure computation is within the given date range
                if (date.isBefore(start) || date.isAfter(end))
                    continue;
                    
                //Convert string input to LocalDate parseable data type.
                LocalTime loginTime = LocalTime.parse(data[4].trim(), TimeVariablesFormat.TIME_FORMAT);
                LocalTime logoutTime = LocalTime.parse(data[5].trim(), TimeVariablesFormat.TIME_FORMAT);

                //Calculate number of minutes the user was late in logging in (after the grace period).
                int dailyLate = loginTime.isAfter(TimeVariablesFormat.GRACE_PERIOD_END)
                        ? (int) Duration.between(TimeVariablesFormat.GRACE_PERIOD_END, loginTime).toMinutes()
                        : 0;
                //Calculate total work time in minutes (after subtracting 60 minutes for a break).
                int dailyWork = (int) Duration.between(loginTime, logoutTime).toMinutes() - 60;
                //Calculate number of minutes the user worked overtime (if they logged out after the standard logout time)
                int dailyOver = logoutTime.isAfter(TimeVariablesFormat.STANDARD_LOGOUT)
                        ? (int) Duration.between(TimeVariablesFormat.STANDARD_LOGOUT, logoutTime).toMinutes()
                        : 0;
                totalLateMinutes += dailyLate;
                totalWorkMinutes += (dailyWork - dailyLate);
                totalOvertimeMinutes += dailyOver;
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
