package service;

import model.Employee;
import java.math.*;

public class PayrollConfig {
    public static double lateDeduction(Employee e, int lateMinutes) {
        return round((lateMinutes / 60.0) * e.hourlyRate);
    }

    public static double overtimeAddition(Employee e, int overtimeMinutes) {
        return round((overtimeMinutes / 60.0) * e.hourlyRate);
    }

    public static double sssDeduction(Employee e) {

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

    public static double pagibigDeduction(Employee e) {
        if (e.basicSalary > 1500)
            return e.basicSalary * 0.02;
        if (e.basicSalary >= 1000)
            return e.basicSalary * 0.01;
        return 0.0;
    }

    public static double philhealthDeduction(Employee e) {
        return e.basicSalary * 0.03;
    }

    public static double tax(Employee e, double taxableIncome) {
        if (taxableIncome > 20832 && taxableIncome < 33333) {
            return (taxableIncome - 20832) * 0.20;
        } else if (taxableIncome > 33333 && taxableIncome < 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome > 66667 && taxableIncome < 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome > 166667 && taxableIncome < 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else if (taxableIncome >= 666667) {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        } else {
            return 0.0;
        }
    }

    public static double totalCompensation(Employee e, double overtime) {
        return e.basicSalary + e.riceSubsidy + e.phoneAllowance + e.clothingAllowance + overtime;
    }

    public static double taxableIncome(Employee e, double overtime, double late, double sss, double pagibig,
            double philhealth) {
        return (e.basicSalary + overtime - late) - (sss + pagibig + philhealth);
    }

    public static double netPay(double total, double deductions) {
        return round(total - deductions);
    }

    private static double round(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
