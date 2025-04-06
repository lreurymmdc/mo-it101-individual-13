package model;

public class User {
    private Employee employee;

    public User(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
