<H1>Terminal-operated Automated Payroll System</H1>
<small>Submitted by Eury Belga, BSIT Marketing Technology</small>
<H2>Key Features:</H2>
<ul>
    <li>Displays employee information.</li>
    <li>Generate weekly attendance reports containing total work hours, total over time, and total late time.</li>
    <li>Generate weekly payslip reports containing earnings and deductions.</li>
</ul>
<h2>Folder Structure</h2>
<pre>
src
|—— FilesToRead
        |———— MotorPH Employee Data - Attendance Record.csv
|—— PayrollSystem
         |———— Main.java
         |———— configurations
		 |———— AttendanceConfig.java
		 |———— PayrollConfig.java
         |———— data
		 |———— EmployeeDatabase.java
         |———— features
		 |———— Attendance.java
		 |———— Login.java
		 |———— Menu.java
		 |———— Payroll.java
         |———— model
		 |———— Employee.java
		 |———— Payslip.java
		 |———— User.java
         |———— util
		 |———— Dsiplay.java
		 |———— TimeVariablesFormat.java
</pre>
<h2>Glossary:</h2>
<ul>
<li><strong>Main.java:</strong> Initializes the program. It keeps the system running until the user chooses to exit.</li>
<li><strong>AttendanceConfig.java: </strong>For attendance-related calculations.</li>
<li><strong>PayrollConfig.java:</strong> For payroll-related calculations.</li>
<li><strong>EmployeeDatabase.java:</strong> Stores motorPH employee information inside a hashmap.</li>
<li><strong>Attendance.java: </strong>Attendance-related features i.e. punch clock and generating attendance report.</li>
<li><strong>Login.java: </strong>Prompts users to enter a valid employee ID before running the system.</li>
<li><strong>Menu.java: </strong>Prompts users with selections of actions that they can do inside once logged in.</li>
<li><strong>Payroll.java: </strong>Payroll-related features i.e. payslip generation.</li>
<li><strong>Employee.java:</strong> Blueprint of a single employee user.&nbsp;</li>
<li><strong>Payslip.java: </strong>Blueprint of payslip information.</li>
<li><strong>User.java:</strong> Isolate a single set of employee information based on their logged employee ID.</li>
<li><strong>Display.java: </strong>Contains the Information architecture of generated reports i.e. employee profile, payslip, and attendance.</li>
<li><strong>TimeVariablesFormat.java:</strong> Ensure time data are parseable.</li>
</ul>
<h2>Guide on how the system works.</h2>
<p>When users run the program, they are greeted and prompted to enter their employee User ID.</p>
<p>If the user enters an invalid employee ID, an error message will appear.</p>
<ul>
<li>If the ID does not match any record in the database:<em> Error: Employee ID not found. Please try again.&nbsp;</em></li>
<li>If the ID is not numeric: Invalid input: <em>Please enter a valid numeric employee ID.</em></li>
</ul>
<p>If the user enters a valid ID, they will be greeted and prompted to select from the menu.</p>
<h3>Menu Selection</h3>
<ul>
<li><strong>Punch Clock:</strong> The system will prompt users to choose either to Clock-in or Clock-out. Once selected, the system will retrieve the exact time when the user hits confirm.</li>
<li><strong>View Profile: </strong>The system will retrieve and display all employee information organized in Display.java, based on the user ID entered during login.</li>
<li><strong>View Attendance</strong>: The system will calculate total hours worked, overtime, and late time based on the attendance data in the FilesToRead folder, then generate a report organized by Display.java.<br />
<ul>
<li>The system will prompt users to input a start date and an end date. Before calculating and generating the report, it will check the following:
<ul>
<li>The end date is not earlier than the start date.</li>
<li>The date range does not exceed 7 days.</li>
</ul>
</li>
<li>If any of these conditions are violated, the system will prompt users to re-enter the dates until all conditions are met.</li>
</ul>
</li>
<li><strong>View Payslip: </strong>The system will calculate basic pay, overtime pay, and late deductions by multiplying the hourly rate by the relevant attendance data. It will also calculate weekly deductions based on the given matrix, then organize the report according to the structure defined in Display.java.<br />
<ul>
<li>The system will prompt users to input a start date and an end date. Before calculating and generating the report, it will check the following:
<ul>
<li>The end date is not earlier than the start date.</li>
<li>The date range does not exceed 7 days.</li>
</ul>
</li>
<li>If any of these conditions are violated, the system will prompt users to re-enter the dates until all conditions are met.</li>
</ul>
</li>
<li><strong>Logout:</strong> Main.java will stop running the system and bid users &ldquo;Goodbye!&rdquo; message.</li>
</ul>
