package service;

import util.TimeVariablesFormat;
import java.nio.file.*;
import java.io.*;
import java.time.*;

public class AttendanceConfig {
    public static int totalLateMinutes = 0;
    public static int totalWorkMinutes = 0;
    public static int totalOvertimeMinutes = 0;

    public static void calculateWorkHours(String employeeID, String month) {
        totalLateMinutes = 0;
        totalWorkMinutes = 0;
        totalOvertimeMinutes = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(TimeVariablesFormat.ATTENDANCE_FILE))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6 || !data[0].trim().equals(employeeID)) continue;

                LocalDate date = LocalDate.parse(data[3].trim(), TimeVariablesFormat.DATE_FORMAT);
                if (date.getMonthValue() != Integer.parseInt(month)) continue;

                LocalTime loginTime = LocalTime.parse(data[4].trim(), TimeVariablesFormat.TIME_FORMAT);
                LocalTime logoutTime = LocalTime.parse(data[5].trim(), TimeVariablesFormat.TIME_FORMAT);

                int dailyLate = loginTime.isAfter(TimeVariablesFormat.GRACE_PERIOD_END) ? (int) Duration.between(TimeVariablesFormat.GRACE_PERIOD_END, loginTime).toMinutes() : 0;
                int dailyWork = (int) Duration.between(loginTime, logoutTime).toMinutes() - 60;
                int dailyOver = logoutTime.isAfter(TimeVariablesFormat.STANDARD_LOGOUT) ? (int) Duration.between(TimeVariablesFormat.STANDARD_LOGOUT, logoutTime).toMinutes() : 0;

                totalLateMinutes += dailyLate;
                totalWorkMinutes += (dailyWork - dailyLate);
                totalOvertimeMinutes += dailyOver;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
