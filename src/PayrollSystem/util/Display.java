package util;
import configurations.AttendanceConfig;
import configurations.PayrollConfig;
import model.Employee;
import model.Payslip;

public class Display {

    public Employee employee;

    public Display(Employee employee) {
        this.employee = employee;
    }

    //Organizes employee data when viewing
    public void employeeDataView() {
        System.out.println("\n--- EMPLOYEE INFORMATION ---");
        System.out.println("ID: " + employee.employeeID);
        System.out.println("Name: " + employee.firstName + " " + employee.lastName);
        System.out.println("Birthday: " + employee.birthday);
        System.out.println("Address: " + employee.address);
        System.out.println("Phone: " + employee.phoneNumber);
        System.out.println("SSS: " + employee.sssNumber);
        System.out.println("Philhealth: " + employee.philhealthNumber);
        System.out.println("TIN: " + employee.tinNumber);
        System.out.println("Pag-IBIG: " + employee.pagibigNumber);
        System.out.println("Status: " + employee.status);
        System.out.println("Position: " + employee.position);
        System.out.println("Supervisor: " + employee.immediateSupervisor);

        System.out.println("\n--- BENEFITS INFORMATION ---");
        System.out.println("Basic Salary: P " + employee.basicSalary);
        System.out.println("Rice Subsidy: P " + employee.riceSubsidy);
        System.out.println("Phone Allowance: P " + employee.phoneAllowance);
        System.out.println("Clothing Allowance: P " + employee.clothingAllowance);
        System.out.println("Gross Semi-Monthly Rate: P " + (employee.basicSalary / 2));
        System.out.println("Hourly Rate: P " + String.format("%.2f", employee.hourlyRate));
    }
    //Organizes payslip data when viewing
    public void paySlipView() {
        Payslip payslip = Payslip.generate(employee); 
        
        System.out.println("\n--- PAYSLIP (WEEKLY) ---");
        System.out.println("Employee ID: " + employee.employeeID);
        System.out.println("Employee Name: " + employee.firstName + " " + employee.lastName);
        System.out.println("Birthday: " + employee.birthday);
        System.out.println("Position: " + employee.position);

        System.out.println("\n--- GOVERNMENT INFORMATION ---");
        System.out.println("SSS: " + employee.sssNumber);
        System.out.println("Philhealth: " + employee.philhealthNumber);
        System.out.println("TIN: " + employee.tinNumber);
        System.out.println("Pag-IBIG: " + employee.pagibigNumber);

        System.out.println("\n--- EARNINGS ---");
        System.out.println("Basic Pay: P" + payslip.basic);
        System.out.println("Overtime Pay: P" + payslip.over);
        System.out.println("Rice Subsidy: P" + employee.riceSubsidy / 4);
        System.out.println("Phone Allowance: P" + employee.phoneAllowance / 4);
        System.out.println("Clothing Allowance: P" + employee.clothingAllowance / 4);

        System.out.println("\n--- DEDUCTIONS ---");
        System.out.println("Late Deduction: P" + payslip.late);
        System.out.println("SSS: P" + payslip.sss);
        System.out.println("Pag-IBIG: P" + payslip.pagibig);
        System.out.println("Philhealth: P" + payslip.philhealth);
        System.out.println("Tax: P" + payslip.tax);

        System.out.println("\nNet Pay: P" + payslip.net);
    }
    public void attendanceView() {
        System.out.println("\n--- ATTENDANCE REPORT ---");
        System.out.println("Total Hours: " + PayrollConfig.round(AttendanceConfig.totalWorkMinutes / 60) + " hours");
        System.out.println("Late: " + PayrollConfig.round(AttendanceConfig.totalLateMinutes / 60) + " hours");
        System.out.println("Overtime: " + PayrollConfig.round(AttendanceConfig.totalOvertimeMinutes / 60) + " hours");
    }
}
