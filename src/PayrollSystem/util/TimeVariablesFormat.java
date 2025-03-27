package util;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class TimeVariablesFormat {
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final LocalTime REQUIRED_LOGIN = LocalTime.of(8, 0);
    public static final LocalTime GRACE_PERIOD_END = LocalTime.of(8, 10);
    public static final LocalTime STANDARD_LOGOUT = LocalTime.of(17, 0);
    public static final String ATTENDANCE_FILE = "src/FilesToRead/MotorPH Employee Data - Attendance Record.csv";
}