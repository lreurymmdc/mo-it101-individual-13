# motorph-aps
This educational project is a terminal-based payroll system for Computer Programming 1, designed to meet the requirements of the fictional company MotorPH.
How it works:
1. The system requires users to log in using their Employee ID.
2. It will then lookup the employee database with the Employee ID as the search key; If it's found, the system will log in the employee, If not, it will direct users to contact admin.
3. Once logged in, employees can perform the following actions:
    - Punch Clock – Clock in or out to record daily attendance.
    - View My Profile – Access basic, government, and employment details. The system retrieves this data from the database (hashmap) using the logged employee ID as the search key.
    - View My Attendance – Retrieves monthly attendance records. The system prompts employees to select a month before displaying the data.
    - View My Payslip - Retrieves monthly pay records. The system prompts employees to select a month before displaying the data.
Other Notes:
- I've utilized two types of data sources: Hashmap for Employee Data and a CSV file for Attendance to try two different methods of accessing and retrieving data in Java.
