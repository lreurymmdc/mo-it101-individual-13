package configurations;

import model.Employee;
import java.math.*;

public class PayrollConfig {
    //Calculate late deduction by multiplying the employee's hourly rate by his total late hours.
    public static double lateDeduction(Employee e, int totalLateMinutes) {
        return round((totalLateMinutes / 60.0) * e.hourlyRate);
    }
    //Calculate overtime pay by multiplying the employee's hourly rate by his total overtime hours.
    public static double overtimeAddition(Employee e, int totalOvertimeMinutes) {
        return round((totalOvertimeMinutes / 60.0) * e.hourlyRate);
    }
    //Calculate basic pay by multiplying the employee's hourly rate by his total work hours.
    public static double basicPay(Employee e, int totalWorkMinutes) {
        return round((totalWorkMinutes / 60.0) * e.hourlyRate);
    }

    //Calculate SSS deduction based on the given matrix.
    public static double sssDeduction(Employee e) {
        //SSS Matrix
        int[] sssContributionMin = { 3250, 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750,
                10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250,
                17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250 };
        int[] sssContributionMax = { 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750,
                10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250,
                17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250,
                24750 };
        double[] sssContributionValue = { 157.50, 180.00, 202.50, 225.00, 247.50, 270.00, 292.50, 315.00, 337.50,
                360.00, 382.50, 405.00, 427.50, 450.00, 472.50, 495.00, 517.50, 540.00, 562.50, 585.00, 607.50, 630.00,
                652.50, 675.00, 697.50, 720.00, 742.50, 765.00, 787.50, 810.00, 832.50, 855.00, 877.50, 900.00, 922.50,
                945.00, 967.50, 990.00, 1012.50, 1035.00, 1057.50, 1080.00, 1102.50 };
    
        double salary = e.basicSalary;
        if (salary < sssContributionMin[0]) {
            return 135.00;
        } else if (salary > sssContributionMax[sssContributionMax.length - 1]) {
            return 1125.00;
        } else {
            for (int i = 0; i < sssContributionMin.length; i++) {
                if (salary >= sssContributionMin[i] && salary <= sssContributionMax[i]) {
                    return sssContributionValue[i];
                }
            }
        }
        return 0.0;
    };
    //Calculate Pagibig deduction based on the given matrix.
    public static double pagibigDeduction(Employee e) {
        if (e.basicSalary > 1500)
            return e.basicSalary * 0.02;
        if (e.basicSalary >= 1000)
            return e.basicSalary * 0.01;
        return 0.0;
    }
    //Calculate Philhealth deduction based on the given matrix.
    public static double philhealthDeduction(Employee e) {
        return e.basicSalary * 0.03;
    }
    //Calculate Tax deduction (Weekly) using taxable income as the reference based on the given matrix.
    public static double tax(Employee e, double taxableIncome) {
        if (taxableIncome > 4807 && taxableIncome < 7691) {
            return (taxableIncome - 4808) * 0.20;
        } else if (taxableIncome > 7691 && taxableIncome < 15384) {
            return 2500 + (taxableIncome - 7692) * 0.25;
        } else if (taxableIncome > 15384 && taxableIncome < 38461) {
            return 10833 + (taxableIncome - 15385) * 0.30;
        } else if (taxableIncome > 38461 && taxableIncome < 153845) {
            return 40833.33 + (taxableIncome - 38462) * 0.32;
        } else if (taxableIncome >= 153846) {
            return 200833.33 + (taxableIncome - 153846) * 0.35;
        } else {
            return 0.0;
        }
    }

    //Calculate total compensation or earnings (weekly)
    public static double totalCompensation(Employee e, double overtime, double basic) {
        return basic + e.riceSubsidy / 4.33 + e.phoneAllowance / 4.33 + e.clothingAllowance / 4.33 + overtime;
    }
    //Calculate taxable income by subtracting earnings with non-taxable income.
    public static double taxableIncome(Employee e, double overtime, double late, double sss, double pagibig,
            double philhealth, double basic) {
        return (basic + overtime - late) - (sss + pagibig + philhealth);
    }
    //Calculate net pay by subtracting total compensation and total deductions 
    public static double netPay(double total, double deductions) {
        return round(total - deductions);
    }

    //Limit decimal number by 2. 
    public static double round(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
