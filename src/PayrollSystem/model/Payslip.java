package model;
import service.AttendanceConfig;
import service.PayrollConfig;


public class Payslip {
    public double basic;
    public double late;
    public double over;
    public double sss;
    public double pagibig;
    public double philhealth;
    public double tax;
    public double total;
    public double net;


//Payslip information architecture
    public static Payslip generate(Employee emp) {
        Payslip payslip = new Payslip();
        payslip.basic = PayrollConfig.basicPay(emp, AttendanceConfig.totalWorkMinutes);
        payslip.late = PayrollConfig.lateDeduction(emp, AttendanceConfig.totalLateMinutes);
        payslip.over = PayrollConfig.overtimeAddition(emp, AttendanceConfig.totalOvertimeMinutes);
        payslip.sss = PayrollConfig.round(PayrollConfig.sssDeduction(emp) / 4.33);
        payslip.pagibig =PayrollConfig.round(PayrollConfig.pagibigDeduction(emp) / 4.33);
        payslip.philhealth = PayrollConfig.round(PayrollConfig.philhealthDeduction(emp) / 4.33);
        payslip.total = PayrollConfig.totalCompensation(emp, payslip.over, payslip.basic);
    
        double taxable = PayrollConfig.taxableIncome(emp, payslip.over, payslip.late, payslip.sss, payslip.pagibig, payslip.philhealth, payslip.basic);
        payslip.tax = PayrollConfig.round(PayrollConfig.tax(emp, taxable));
        double deductions = payslip.late + payslip.sss + payslip.pagibig + payslip.philhealth + payslip.tax;
        payslip.net = PayrollConfig.netPay(payslip.total, deductions);
    
        return payslip;
    }
}



