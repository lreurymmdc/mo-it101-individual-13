package model;

public class Employee {
    public int employeeID, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
    public String lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber,
            pagibigNumber, status, position, immediateSupervisor;
    public double hourlyRate;

//employee information architecture
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
    public int getId() {
        return employeeID;
    }
    public String getFullname() {
        return firstName + " " + lastName;
    }
    public String getBirthday() {
        return birthday;
    }
}
