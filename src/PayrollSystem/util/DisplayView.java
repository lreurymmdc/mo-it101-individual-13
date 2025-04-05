package util;
import java.time.LocalDate;

import model.Employee;
import model.Payslip;

public class DisplayView {

    public Employee e;

    public DisplayView(Employee e) {
        this.e = e;
    }

    //Display employee data
    public void viewEmployeeData() {
        System.out.println("\n===== Employee Information =====");
        System.out.println("ID: " + e.employeeID);
        System.out.println("Name: " + e.firstName + " " + e.lastName);
        System.out.println("Birthday: " + e.birthday);
        System.out.println("Address: " + e.address);
        System.out.println("Phone: " + e.phoneNumber);
        System.out.println("SSS: " + e.sssNumber);
        System.out.println("Philhealth: " + e.philhealthNumber);
        System.out.println("TIN: " + e.tinNumber);
        System.out.println("Pag-IBIG: " + e.pagibigNumber);
        System.out.println("Status: " + e.status);
        System.out.println("Position: " + e.position);
        System.out.println("Supervisor: " + e.immediateSupervisor);

        System.out.println("\n===== Compensation Details =====");
        System.out.println("Basic Salary: P " + e.basicSalary);
        System.out.println("Rice Subsidy: P " + e.riceSubsidy);
        System.out.println("Phone Allowance: P " + e.phoneAllowance);
        System.out.println("Clothing Allowance: P " + e.clothingAllowance);
        System.out.println("Gross Semi-Monthly Rate: P " + (e.basicSalary / 2));
        System.out.println("Hourly Rate: P " + String.format("%.2f", e.hourlyRate));
    }
    //Display payslip data
    public void paySlipView() {
        Payslip payslip = Payslip.generate(e); 
        
        System.out.println("\n--- PAYSLIP (WEEKLY) ---");
        System.out.println("Employee ID: " + e.employeeID);
        System.out.println("Employee Name: " + e.firstName + " " + e.lastName);
        System.out.println("Birthday: " + e.birthday);
        System.out.println("Position: " + e.position);

        System.out.println("\n---Government Information---");
        System.out.println("SSS: " + e.sssNumber);
        System.out.println("Philhealth: " + e.philhealthNumber);
        System.out.println("TIN: " + e.tinNumber);
        System.out.println("Pag-IBIG: " + e.pagibigNumber);

        System.out.println("\n---Compensation---");
        System.out.println("Basic Pay: P" + payslip.basic);
        System.out.println("Overtime Pay: P" + payslip.over);
        System.out.println("Rice Subsidy: P" + e.riceSubsidy / 4);
        System.out.println("Phone Allowance: P" + e.phoneAllowance / 4);
        System.out.println("Clothing Allowance: P" + e.clothingAllowance / 4);

        System.out.println("\n---Deductions---");
        System.out.println("Late Deduction: P" + payslip.late);
        System.out.println("SSS: P" + payslip.sss);
        System.out.println("Pag-IBIG: P" + payslip.pagibig);
        System.out.println("Philhealth: P" + payslip.philhealth);
        System.out.println("Tax: P" + payslip.tax);

        System.out.println("\nNet Pay: P" + payslip.net);
    }
}
