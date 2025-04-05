# motorph-aps
This educational project is a terminal-based payroll system for Computer Programming 1, designed to meet the requirements of the fictional company MotorPH.
How it works:
1. The system requires users to log in using their Employee ID.
2. It will then lookup the employee database with the Employee ID as the search key; If it's found, the system will log in the employee, If not, it will direct users to contact admin.
3. Once logged in, employees can perform the following actions:
    - Punch Clock – Clock in or out to record daily attendance.
    - View Profile – Access basic, government, and employment details. The system retrieves this data from the database (hashmap) using the logged employee ID as the search key.
    - View Attendance – Retrieves weekly attendance records. The system prompts employees to select a starting date and an end date before displaying the data.
    - View Payslip - Retrieves weekly pay records. The system prompts employees to select a starting date and an end date before displaying the data.
      
Updates:
- Revised code calculation to compute weekly payroll information.
- added Payslip.java and Display.java to better organize and reduce code cluttering in the Main.java file. 
