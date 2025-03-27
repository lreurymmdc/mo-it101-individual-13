package model;

public class Employee {
    public int employeeID, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
    public String lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber,
            pagibigNumber, status, position, immediateSupervisor;
    public double hourlyRate;

    public Employee(int employeeID, String lastName, String firstName, String birthday, String address,
                    String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber,
                    String pagibigNumber, String status, String position, String immediateSupervisor,
                    int basicSalary, int riceSubsidy, int phoneAllowance, int clothingAllowance, double hourlyRate) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.hourlyRate = hourlyRate;
    }

    public void viewEmployeeData() {
        System.out.println("\n===== Employee Information =====");
        System.out.println("ID: " + employeeID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Address: " + address);
        System.out.println("Birthday: " + birthday);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("SSS: " + sssNumber);
        System.out.println("Philhealth: " + philhealthNumber);
        System.out.println("TIN: " + tinNumber);
        System.out.println("Pag-IBIG: " + pagibigNumber);
        System.out.println("Status: " + status);
        System.out.println("Position: " + position);
        System.out.println("Supervisor: " + immediateSupervisor);

        System.out.println("\n===== Compensation Details =====");
        System.out.println("Basic Salary: P " + basicSalary);
        System.out.println("Rice Subsidy: P " + riceSubsidy);
        System.out.println("Phone Allowance: P " + phoneAllowance);
        System.out.println("Clothing Allowance: P " + clothingAllowance);
        System.out.println("Gross Semi-Monthly Rate: P " + (basicSalary / 2));
        System.out.println("Hourly Rate: P " + String.format("%.2f", hourlyRate));
    }
}
