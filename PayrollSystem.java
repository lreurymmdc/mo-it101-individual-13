import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Submitted by: Eury John Belga
//Program: BSIT, Marketing Technology

public class PayrollSystem {
        
        static Scanner promptScanner = new Scanner(System.in);
        static LocalDate dateNow = LocalDate.now();
        static LocalTime timeNow = LocalTime.now();
        
//EMPLOYEE DATA

        // Employee - Data attributes
        private int employeeID, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
        private String lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber,
                        pagibigNumber, status, position, immediateSupervisor;
        private double hourlyRate;
        
        // Employee - Data Constructor
        public PayrollSystem(int employeeID, String lastName, String firstName, String birthday, String address,
                        String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber,
                        String pagibigNumber,
                        String status, String position, String immediateSupervisor, int basicSalary, int riceSubsidy,
                        int phoneAllowance, int clothingAllowance, double hourlyRate) {
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
        };
        
        // Employee - Database (using hashmap)
        static Map<Integer, PayrollSystem> employeeMap = new HashMap<>();

        // Employee - Sample Dataset
        static {
                List<PayrollSystem> employees = Arrays.asList(
                                new PayrollSystem(10002, "Lim", "Antonio", "06/19/1988",
                                                "San Antonio De Padua 2, Block 1 Lot 8 and 2, Dasmarinas, Cavite",
                                                "171-867-411",
                                                "52-2061274-9", "331735646338", "683-102-776-000", "663904995411",
                                                "Regular",
                                                "Chief Operating Officer", "Garcia Manuel III", 60000, 1500, 2000, 1000,
                                                357.14),
                                new PayrollSystem(10003, "Aquino", "Bianca Sofia", "32724",
                                                "Rm. 402 4/F Jiao Building Timog Avenue Cor. Quezon Avenue 1100, Quezon City",
                                                "966-889-370",
                                                "30-8870406-2", "177451189665", "971-711-280-000", "171519773969",
                                                "Regular",
                                                "Chief Finance Officer", "Garcia, Manuel III", 60000, 1500, 2000, 1000,
                                                357.14),
                                new PayrollSystem(10004, "Reyes", "Isabella", "34501",
                                                "460 Solanda Street Intramuros 1000, Manila",
                                                "786-868-477", "40-2511815-0", "341911411254", "876-809-437-000",
                                                "416946776041", "Regular",
                                                "Chief Marketing Officer", "Garcia, Manuel III", 60000, 1500, 2000,
                                                1000, 357.14),
                                new PayrollSystem(10005, "Hernandez", "Eduard", "32774",
                                                "National Highway, Gingoog,  Misamis Occidental", "088-861-012",
                                                "50-5577638-1", "957436191812",
                                                "031-702-374-000", "952347222457", "Regular",
                                                "IT Operations and Systems", "Lim, Antonio",
                                                52670, 1500, 1000, 1000, 313.51),
                                new PayrollSystem(10006, "Villanueva", "Andrea Mae", "32187",
                                                "17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands ",
                                                "918-621-603",
                                                "49-1632020-8", "382189453145", "317-674-022-000", "441093369646",
                                                "Regular", "HR Manager",
                                                "Lim, Antonio", 52670, 1500, 1000, 1000, 313.51),
                                new PayrollSystem(10007, "San Jose", "Brad ", "35139",
                                                "99 Strosin Hills, Poblacion, Bislig 5340 Tawi-Tawi", "797-009-261",
                                                "40-2400714-1",
                                                "239192926939", "672-474-690-000", "210850209964", "Regular",
                                                "HR Team Leader",
                                                "Villanueva, Andrea Mae", 42975, 1500, 800, 800, 255.80),
                                new PayrollSystem(10008, "Romualdez", "Alice", "33738",
                                                "12A/33 Upton Isle Apt. 420, Roxas City 1814 Surigao del Norte ",
                                                "983-606-799", "55-4476527-2",
                                                "545652640232", "888-572-294-000", "211385556888", "Regular",
                                                "HR Rank and File",
                                                "San, Jose Brad", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10009, "Atienza", "Rosie ", "17800",
                                                "90A Dibbert Terrace Apt. 190, San Lorenzo 6056 Davao del Norte",
                                                "266-036-427", "41-0644692-3",
                                                "708988234853", "604-997-793-000", "260107732354", "Regular",
                                                "HR Rank and File",
                                                "San, Jose Brad", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10010, "Alvaro", "Roderick", "32232",
                                                "#284 T. Morato corner, Scout Rallos Street, Quezon City",
                                                "053-381-386", "64-7605054-4",
                                                "578114853194", "525-420-419-000", "799254095212", "Regular",
                                                "Accounting Head",
                                                "Aquino, Bianca Sofia ", 52670, 1500, 1000, 1000, 313.51),
                                new PayrollSystem(10011, "Salcedo", "Anthony", "34226",
                                                "93/54 Shanahan Alley Apt. 183, Santo Tomas 1572 Masbate",
                                                "070-766-300", "26-9647608-3",
                                                "126445315651", "210-805-911-000", "218002473454", "Regular",
                                                "Payroll Manager",
                                                "Alvaro, Roderick", 50825, 1500, 1000, 1000, 302.53),
                                new PayrollSystem(10012, "Lopez", "Josie ", "31791",
                                                "49 Springs Apt. 266, Poblacion, Taguig 3200 Occidental Mindoro",
                                                "478-355-427", "44-8563448-3",
                                                "431709011012", "218-489-737-000", "113071293354", "Regular",
                                                "Payroll Team Leader",
                                                "Salcedo, Anthony", 38475, 1500, 800, 800, 229.02),
                                new PayrollSystem(10013, "Farala", "Martha", "15352",
                                                "42/25 Sawayn Stream, Ubay 1208 Zamboanga del Norte ", "329-034-366",
                                                "45-5656375-0",
                                                "233693897247", "210-835-851-000", "631130283546", "Regular",
                                                "Payroll Rank and File",
                                                "Salcedo, Anthony", 24000, 1500, 500, 500, 142.86),
                                new PayrollSystem(10014, "Martinez", "Leila", "25760",
                                                "37/46 Kulas Roads, Maragondon 0962 Quirino ",
                                                "877-110-749", "27-2090996-4", "515741057496", "275-792-513-000",
                                                "101205445886", "Regular",
                                                "Payroll Rank and File", "Salcedo, Anthony", 24000, 1500, 500, 500,
                                                142.86),
                                new PayrollSystem(10015, "Romualdez", "Fredrick ", "31116",
                                                "22A/52 Lubowitz Meadows, Pililla 4895 Zambales", "023-079-009",
                                                "26-8768374-1", "308366860059",
                                                "598-065-761-000", "223057707853", "Regular", "Account Manager",
                                                "Lim, Antonio", 53500, 1500,
                                                1000, 1000, 318.45),
                                new PayrollSystem(10016, "Mata", "Christian", "32071",
                                                "90 O'Keefe Spur Apt. 379, Catigbian 2772 Sulu ",
                                                "783-776-744", "49-2959312-6", "824187961962", "103-100-522-000",
                                                "631052853464", "Regular",
                                                "Account Team Leader", "Romualdez, Fredrick ", 42975, 1500, 800, 800,
                                                255.80),
                                new PayrollSystem(10017, "De Leon", "Selena ", "27445",
                                                "89A Armstrong Trace, Compostela 7874 Maguindanao", "975-432-139",
                                                "27-2090208-8",
                                                "587272469938", "482-259-498-000", "719007608464", "Regular",
                                                "Account Team Leader",
                                                "Romualdez, Fredrick ", 41850, 1500, 800, 800, 249.11),
                                new PayrollSystem(10018, "San Jose", "Allison ", "31587",
                                                "08 Grant Drive Suite 406, Poblacion, Iloilo City 9186 La Union",
                                                "179-075-129", "45-3251383-0",
                                                "745148459521", "121-203-336-000", "114901859343", "Regular",
                                                "Account Rank and File",
                                                "Mata, Christian", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10019, "Rosario", "Cydney ", "35344",
                                                "93A/21 Berge Points, Tapaz 2180 Quezon",
                                                "868-819-912", "49-1629900-2", "579253435499", "122-244-511-000",
                                                "265104358643", "Regular",
                                                "Account Rank and File", "Mata, Christian", 22500, 1500, 500, 500,
                                                133.93),
                                new PayrollSystem(10020, "Bautista", "Mark ", "33281",
                                                "65 Murphy Center Suite 094, Poblacion, Palayan 5636 Quirino",
                                                "683-725-348", "49-1647342-5",
                                                "399665157135", "273-970-941-000", "260054585575", "Regular",
                                                "Account Rank and File",
                                                "Mata, Christian", 23250, 1500, 500, 500, 138.39),
                                new PayrollSystem(10021, "Lazaro", "Darlene ", "31376",
                                                "47A/94 Larkin Plaza Apt. 179, Poblacion, Caloocan 2751 Quirino",
                                                "740-721-558", "45-5617168-2",
                                                "606386917510", "354-650-951-000", "104907708845", "Probationary",
                                                "Account Rank and File",
                                                "Mata, Christian", 23250, 1500, 500, 500, 138.39),
                                new PayrollSystem(10022, "Delos Santos", "Kolby ", "29277",
                                                "06A Gulgowski Extensions, Bongabon 6085 Zamboanga del Sur",
                                                "739-443-033", "52-0109570-6",
                                                "357451271274", "187-500-345-000", "113017988667", "Probationary",
                                                "Account Rank and File",
                                                "Mata, Christian", 24000, 1500, 500, 500, 142.86),
                                new PayrollSystem(10023, "Santos", "Vella ", "30681",
                                                "99A Padberg Spring, Poblacion, Mabalacat 3959 Lanao del Sur",
                                                "955-879-269", "52-9883524-3",
                                                "548670482885", "101-558-994-000", "360028104576", "Probationary",
                                                "Account Rank and File",
                                                "Mata, Christian", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10024, "Del Rosario", "Tomas", "28842",
                                                "80A/48 Ledner Ridges, Poblacion, Kabankalan 8870 Marinduque",
                                                "882-550-989", "45-5866331-6",
                                                "953901539995", "560-735-732-000", "913108649964", "Probationary",
                                                "Account Rank and File",
                                                "Mata, Christian", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10025, "Tolentino", "Jacklyn ", "30821",
                                                "96/48 Watsica Flats Suite 734, Poblacion, Malolos 1844 Ifugao",
                                                "675-757-366", "47-1692793-0",
                                                "753800654114", "841-177-857-000", "210546661243", "Probationary",
                                                "Account Rank and File",
                                                "De Leon, Selena", 24000, 1500, 500, 500, 142.86),
                                new PayrollSystem(10026, "Gutierrez", "Percival ", "25920",
                                                "58A Wilderman Walks, Poblacion, Digos 5822 Davao del Sur",
                                                "512-899-876", "40-9504657-8",
                                                "797639382265", "502-995-671-000", "210897095686", "Probationary",
                                                "Account Rank and File",
                                                "De Leon, Selena", 24750, 1500, 500, 500, 147.32),
                                new PayrollSystem(10027, "Manalaysay", "Garfield ", "31652",
                                                "60 Goyette Valley Suite 219, Poblacion, Tabuk 3159 Lanao del Sur",
                                                "948-628-136",
                                                "45-3298166-4", "810909286264", "336-676-445-000", "211274476563",
                                                "Probationary",
                                                "Account Rank and File", "De Leon, Selena", 24750, 1500, 500, 500,
                                                147.32),
                                new PayrollSystem(10028, "Villegas", "Lizeth ", "29932",
                                                "66/77 Mann Views, Luisiana 1263 Dinagat Islands", "332-372-215",
                                                "40-2400719-4",
                                                "934389652994", "210-395-397-000", "122238077997", "Probationary",
                                                "Account Rank and File",
                                                "De Leon, Selena", 24000, 1500, 500, 500, 142.86),
                                new PayrollSystem(10029, "Ramos", "Carol ", "28722",
                                                "72/70 Stamm Spurs, Bustos 4550 Iloilo",
                                                "250-700-389", "60-1152206-4", "351830469744", "395-032-717-000",
                                                "212141893454",
                                                "Probationary", "Account Rank and File", "De Leon, Selena", 22500, 1500,
                                                500, 500, 133.93),
                                new PayrollSystem(10030, "Maceda", "Emelia ", "26768",
                                                "50A/83 Bahringer Oval Suite 145, Kiamba 7688 Nueva Ecija",
                                                "973-358-041", "54-1331005-0",
                                                "465087894112", "215-973-013-000", "515012579765", "Probationary",
                                                "Account Rank and File",
                                                "De Leon, Selena", 22500, 1500, 500, 500, 133.93),
                                new PayrollSystem(10031, "Aguilar", "Delia ", "32535",
                                                "95 Cremin Junction, Surallah 2809 Cotabato",
                                                "529-705-439", "52-1859253-1", "136451303068", "599-312-588-000",
                                                "110018813465",
                                                "Probationary", "Account Rank and File", "De Leon, Selena", 22500, 1500,
                                                500, 500, 133.93),
                                new PayrollSystem(10032, "Castro", "John Rafael", "33643", "Hi-way, Yati, Liloan Cebu",
                                                "332-424-955 ",
                                                "26-7145133-4", "601644902402", "404-768-309-000", "697764069311",
                                                "Regular",
                                                "Sales & Marketing", "Reyes, Isabella", 52670, 1500, 1000, 1000,
                                                313.51),
                                new PayrollSystem(10033, "Martinez", "Carlos Ian", "33193", "Bulala, Camalaniugan",
                                                "078-854-208",
                                                "11-5062972-7", "380685387212", "256-436-296-000", "993372963726",
                                                "Regular",
                                                "Supply Chain and Logistics", "Reyes, Isabella", 52670, 1500, 1000,
                                                1000, 313.51),
                                new PayrollSystem(10034, "Santos", "Beatriz", "33092", "Agapita Building, Metro Manila",
                                                "526-639-511",
                                                "20-2987501-5", "918460050077", "911-529-713-000", "874042259378",
                                                "Regular",
                                                "Customer Service and Relations", "Reyes, Isabella", 52670, 1500, 1000,
                                                1000, 313.51));
                employees.forEach(PayrollSystem::addToEmployeeMap);
        }

//ATTENDANCE DATA AND DATA FORMATTING

        //Attendance variables
        static int totalLateMinutes = 0;
        static int totalOvertimeMinutes = 0;
        static int totalWorkMinutes = 0;
        
        // Attendance - Sample Dataset from a CSV file
        static String attendanceFilePath = "MotorPH Employee Data - Attendance Record.csv";

        //Attendance time and date format
        static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        static LocalTime requiredLogInTime = LocalTime.of(8, 0);
        static LocalTime gracePeriodEndTime = LocalTime.of(8, 10);
        static LocalTime standardLogOutTime = LocalTime.of(17, 0);

//PAYROLL SYSTEM METHODS

        //Method to push the each employee information to the HashMap.
        static void addToEmployeeMap(PayrollSystem employee) {
                employeeMap.put(employee.employeeID, employee);
        };

        //DISPLAY METHODS

                // Method to display employee details
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

                // Method to dsiplay payslip details
                static void viewPayslip(int userId) {

                PayrollSystem employee = employeeMap.get(userId);

                System.out.println("\n===MOTORPH PAYSLIP===\n");
                
                System.out.println("Employee Name: " + employee.firstName +" " + employee.lastName);
                System.out.println("Employee ID: " + userId);
                System.out.println("Status: " + employee.status);
                System.out.println("Position: " + employee.position);

                
                System.out.println("\n===Government Information===\n");
                System.out.println("SSS: " + employee.sssNumber);
                System.out.println("Philhealth: " + employee.philhealthNumber);
                System.out.println("TIN: " + employee.tinNumber);
                System.out.println("Pag-IBIG: " + employee.pagibigNumber);

                
                System.out.println("\n===Compensation===\n");
                System.out.println("Basic: P" + employee.basicSalary +".0");
                System.out.println("Overtime Pay: P" + overMinutesAddition(userId));
                System.out.println("Rice Subsidy: P" + employee.riceSubsidy +".0");
                System.out.println("Phone Allowance: P" + employee.phoneAllowance +".0");
                System.out.println("Clothing Allowance: P" + employee.clothingAllowance +".0");
                System.out.println("\nGross Pay: P" + totalCompensation(userId));
                System.out.println("Taxable Income: P" + taxableCompensation(userId));
                
                System.out.println("\n===Deductions===\n");
                System.out.println("Late/Undertime (" + totalLateMinutes + " minutes): P" + lateMinutesDeduction(userId));
                System.out.println("SSS: P" + sssDeduction(userId));
                System.out.println("Philhealth: P" + employee.basicSalary*0.03 );
                System.out.println("Pag-ibig: P" + pagIbigDeduction(userId));     
                System.out.println("Witholding Tax: P" + Math.round(taxDeduction(userId)));
                System.out.println("\nTotal Deductions: P" + totalDeductions(userId) +"\n" );
                

                System.out.println("Net Pay: P" + new BigDecimal((totalCompensation(userId) - totalDeductions(userId))).setScale(2, RoundingMode.HALF_UP).doubleValue() + "\n");
        };

                //Method to view attendance
                static void viewAttendance(int userId, String month, String employeeID) {
                PayrollSystem employee = employeeMap.get(userId);
                calculateWorkHours(employeeID, month);
                System.out.println("\nAttendance Summary for " + employee.firstName + " " + employee.lastName + "\n");
                System.out.println("Total Working Hours: " + totalWorkMinutes / 60 + " hours " + totalWorkMinutes % 60 + " minutes");
                System.out.println("Total Late Time: " + totalLateMinutes / 60 + " hours " + totalLateMinutes % 60 + " minutes");
                System.out.println("Total Overtime: " + totalOvertimeMinutes / 60 + " hours " + totalOvertimeMinutes % 60 + " minutes");
                }
        
        //CALCULATION METHODS

                //Attendance-related calculations
                
                        // Method to calculate working hours || Note: Working hours follows the standard Philippine working hours 8:00 to 5:00 with Lunch break not included in the working hours.
                        static void calculateWorkHours(String employeeID, String month) {

                                try (BufferedReader br = Files.newBufferedReader(Paths.get(attendanceFilePath))) {
                                        String line = br.readLine(); // Skip header
                                        while ((line = br.readLine()) != null) {
                                                String[] data = line.split(",");
                                                if (data.length < 6 || !data[0].trim().equals(employeeID))
                                                        continue;
                
                                                LocalDate date = LocalDate.parse(data[3].trim(), dateFormat);
                                                if (date.getMonthValue() != Integer.parseInt(month))
                                                        continue;
                
                                                LocalTime loginTime = LocalTime.parse(data[4].trim(), timeFormat);
                                                LocalTime logoutTime = LocalTime.parse(data[5].trim(), timeFormat);
                
                                                int dailyLateMinutes = loginTime.isAfter(gracePeriodEndTime)
                                                                ? (int) Duration.between(gracePeriodEndTime, loginTime).toMinutes()
                                                                : 0;
                                                int dailyWorkMinutes = (int) Duration.between(loginTime, logoutTime).toMinutes() - 60; // Deduct
                                                                                                                                // 1-hour
                                                                                                                                // lunch
                                                int dailyOvertimeMinutes = logoutTime.isAfter(standardLogOutTime)
                                                                ? (int) Duration.between(standardLogOutTime, logoutTime).toMinutes()
                                                                : 0;
                
                                                totalLateMinutes += dailyLateMinutes;
                                                totalWorkMinutes += dailyWorkMinutes - dailyLateMinutes;
                                                totalOvertimeMinutes += dailyOvertimeMinutes;
                                        }
                                } catch (IOException e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                }
                                };
                        
                        //Method to calculate late minutes deduction
                        static double lateMinutesDeduction(int userid) {
                                PayrollSystem employee = employeeMap.get(userid);
                                
                                double hourlyPay = employee.hourlyRate;
                                double lateTimeCalc = (totalLateMinutes / 60) * hourlyPay;
                                return new BigDecimal(lateTimeCalc).setScale(2,RoundingMode.HALF_UP).doubleValue();
                        };
                        
                        //Method to calculate overtime minutes additions
                        static double overMinutesAddition(int userid) {
                        PayrollSystem employee = employeeMap.get(userid);
                        
                        double hourlyPay = employee.hourlyRate;
                        double overTimeCalc = (totalOvertimeMinutes / 60) * hourlyPay;
                        return new BigDecimal(overTimeCalc).setScale(2,RoundingMode.HALF_UP).doubleValue();
                };
                
                //Deductions calculations

                        //Method to calculate Tax 
                        static double taxDeduction(int userId) {

                                double salary = taxableCompensation(userId);

                                if (salary > 20832 && salary < 33333) {
                                        return (salary - 20832)*0.20;
                                }
                                else if (salary > 33333 && salary < 66667) {
                                        return 2500 + (salary - 33333)*0.25;
                                }
                                else if (salary > 66667 && salary < 166667) {
                                        return 10833 + (salary - 66667)*0.30;
                                }
                                else if (salary > 166667 && salary < 666667) {
                                        return 40833.33 + (salary - 166667)*0.32;
                                }
                                else if (salary >= 666667) {
                                        return 200833.33 + (salary - 666667)*0.35;
                                }
                                else {
                                        return 0.0;
                                }          
                        }        
                        
                        //Method to calculate SSS contribution 
                        static double sssDeduction(int userId) {
                                
                                PayrollSystem employee = employeeMap.get(userId);
                        int[] sssContributionMin = {3250, 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750, 10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250, 17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250};
                        int[] sssContributionMax = {3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750, 10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250, 17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250, 24750};
                        double[] sssContributionValue = {157.50, 180.00, 202.50, 225.00, 247.50, 270.00, 292.50, 315.00, 337.50, 360.00, 382.50, 405.00, 427.50, 450.00, 472.50, 495.00, 517.50, 540.00, 562.50, 585.00, 607.50, 630.00, 652.50, 675.00, 697.50, 720.00, 742.50, 765.00, 787.50, 810.00, 832.50, 855.00, 877.50, 900.00, 922.50, 945.00, 967.50, 990.00, 1012.50, 1035.00, 1057.50, 1080.00, 1102.50};

                        double salary = employee.basicSalary;
                                if (salary < sssContributionMin[0]) {
                                        return 135.00;
                                }
                                else if (salary > sssContributionMax[sssContributionMax.length - 1]) {
                                        return 1125.00;
                                }
                                else {
                                        for (int i = 0; i < sssContributionMin.length; i++) {
                                                if (salary >= sssContributionMin[i] && salary <= sssContributionMax[i]) {
                                                        return sssContributionValue[i];
                                                }
                                        }
                                }

                return 0.0;
                        };
                        
                        //Method to calculate Pagibig contribution
                        static double pagIbigDeduction(int userId) {
                        
                        PayrollSystem employee = employeeMap.get(userId);

                        double pagIbigContribution;
        
                        if (employee.basicSalary >= 1000 && employee.basicSalary <= 1500) {
                        pagIbigContribution = employee.basicSalary * 0.01;
                        } else if (employee.basicSalary > 1500) {
                        pagIbigContribution = employee.basicSalary * 0.02;
                        } else {
                        pagIbigContribution = 0.0; // In case salary is below 1000
                        }
                
                        return pagIbigContribution;
                };

                //Aggregations

                        // Method to calculate total compensation
                        static double totalCompensation(int userId) {
                                PayrollSystem employee = employeeMap.get(userId);
                                
                                int allowanceSum = employee.riceSubsidy + employee.phoneAllowance + employee.clothingAllowance;
                                
                                return employee.basicSalary + allowanceSum + (totalOvertimeMinutes / 60) * employee.hourlyRate;
                        }

                        // Method to calculate taxable compensation
                        static double taxableCompensation(int userId) {
                        PayrollSystem employee = employeeMap.get(userId);

                        double govtDeductionSum = sssDeduction(userId) + pagIbigDeduction(userId) + employee.basicSalary*0.03;
                        
                        return ((employee.basicSalary + overMinutesAddition(userId)) - lateMinutesDeduction(userId))-govtDeductionSum;
                }

                        //Method to calculate total deductions 
                        static double totalDeductions(int userId) {
                        PayrollSystem employee = employeeMap.get(userId);
                        double rawTotalDeductions = lateMinutesDeduction(userId) + sssDeduction(userId) + pagIbigDeduction(userId) + taxDeduction(userId) + employee.basicSalary * 0.03;
                        return new BigDecimal(rawTotalDeductions).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }

        //ADMIN FEATURES

                //Method to search an employee
                static void searchEmployee(int userId) {
                
                        PayrollSystem employee = employeeMap.get(userId);
                        System.out.println("\n===== Employee Information =====");
                        System.out.println("ID: " + employee.employeeID);
                        System.out.println("Name: " + employee.firstName + " " + employee.lastName);
                        System.out.println("Address: " + employee.address);
                        System.out.println("Birthday: " + employee.birthday);
                        System.out.println("Phone: " + employee.phoneNumber);
                        System.out.println("SSS: " + employee.sssNumber);
                        System.out.println("Philhealth: " + employee.philhealthNumber);
                        System.out.println("TIN: " + employee.tinNumber);
                        System.out.println("Pag-IBIG: " + employee.pagibigNumber);
                        System.out.println("Status: " + employee.status);
                        System.out.println("Position: " + employee.position);
                        System.out.println("Supervisor: " + employee.immediateSupervisor);
                        System.out.println("\n===== Compensation Details =====");
                        System.out.println("Basic Salary: P " + employee.basicSalary);
                        System.out.println("Rice Subsidy: P " + employee.riceSubsidy);
                        System.out.println("Phone Allowance: P " + employee.phoneAllowance);
                        System.out.println("Clothing Allowance: P " + employee.clothingAllowance);
                        System.out.println("Gross Semi-Monthly Rate: P " + (employee.basicSalary / 2));
                        System.out.println("Hourly Rate: P " + String.format("%.2f", employee.hourlyRate));
                };

                //Method to update an employee
                static void updateEmployee(int userId, int fieldNum, String newInfo) {
                PayrollSystem employee = employeeMap.get(userId);

                switch (fieldNum) {
                        case 1:
                                employee.firstName = newInfo;
                                break;
                        case 2:
                                employee.lastName = newInfo;
                                break;
                        case 3: 
                                employee.birthday = newInfo;
                                break;
                        case 4:
                                employee.address = newInfo;
                                break;
                        case 5: 
                                employee.phoneNumber = newInfo;
                                break;
                        case 6: 
                                employee.sssNumber = newInfo;
                                break;
                        case 7: 
                                employee.philhealthNumber = newInfo;
                                break;
                        case 8:
                                employee.tinNumber = newInfo;
                                break;
                        case 9:
                                employee.pagibigNumber = newInfo;
                                break;
                        case 10:
                                employee.status = newInfo;
                                break;
                        case 11: 
                                employee.position = newInfo;
                                break;
                        case 12: 
                                employee.immediateSupervisor = newInfo;
                                break;
                        case 13: 
                                employee.basicSalary = Integer.parseInt(newInfo);
                                break;
                        case 14:
                                employee.hourlyRate = Integer.parseInt(newInfo);
                                break;
                        default:
                                break;
                }

        }

        public static void main(String[] args) {
                
                int userEmployeeID = 0;
                System.out.println("Welcome to MotorPH APS!");

                //Login page: This is a loop to keep the system continuously prompting user until it provides a valid employee ID.

                while (userEmployeeID == 0 || !employeeMap.containsKey(userEmployeeID)) {
                        System.out.println("Enter your Employee ID: ");
                        userEmployeeID = Integer.parseInt(promptScanner.nextLine());
                        if (!employeeMap.containsKey(userEmployeeID)) {
                                System.out.println("Invalid. Please try again");
                        }
                        
                }      

                //Post-login: A condition, if the employee ID is valid - the system will run the payroll system. 

                if(employeeMap.containsKey(userEmployeeID)) {
                        PayrollSystem employeeDetails = employeeMap.get(userEmployeeID);
                        String userPromptActivity = "";

                        //Text-display: Greeting message for the successful users. 

                        System.out.println("\nWelcome " + employeeDetails.firstName + " " + employeeDetails.lastName + "!");
                        System.out.println("Employee #" + employeeDetails.employeeID);
                        System.out.println("\nToday is " + dateNow +" "+ timeNow.format(timeFormat));
                                        
                                        //System features: Loop to keep the system prompting users to select specific actions in the system until they opt to logout.

                                        while (!userPromptActivity.equalsIgnoreCase("F")) {
                                                System.out.println("\nWhat would you like to do?");
                                                System.out.println("A. Punch Clock | B. View my profile | C. View my attendance | D. View my payslip | E. Admin Tools | F. Logout");
                                                
                                                userPromptActivity = promptScanner.nextLine().trim().toUpperCase();
                                
                                                //System decision making.
                                                switch (userPromptActivity.trim().toUpperCase()) {
                                                
                                                //User wants to log their attendance.
                                                case "A":
                                                        System.out.println("A. Clock-in or B. Clock-out? ");
                                                        userPromptActivity = promptScanner.nextLine();

                                                        switch (userPromptActivity.toUpperCase().trim()) {
                                                                case "A":
                                                                        System.out.println("\nYou've successfully clock-in at " + timeNow.format(timeFormat));
                                                                        break;
                                                                case "B":
                                                                        System.out.println("\nYou've successfully clock-out at " + timeNow.format(timeFormat));
                                                                        break;
                                                                default:
                                                                        System.out.println("Invalid prompt.");
                                                                        break;
                                                        }
                                                        break;
                                                
                                                //User wants to view their profile
                                                case "B":
                                                        employeeDetails.viewEmployeeData();
                                                        break;  
                                                
                                                //User wants to view their attendance
                                                case "C":
                                                        System.out.println("\n==== Attendance data available from June 2024 to December 2024 ====\n");
                                                        System.out.println("Select the month for your attendance (Input corresponding number):\n");

                                                        String[] dateSelection = {"June", "July", "August", "September", "October", "November", "December"};

                                                        int dateMonthSelect = 0;
                                                
                                                        for(dateMonthSelect = 0; dateMonthSelect < dateSelection.length; dateMonthSelect++) {
                                                                System.out.println(dateMonthSelect+6 + " " + dateSelection[dateMonthSelect]);
                                                        }
                                
                                                        String selectedMonth = promptScanner.nextLine();
                                                        String convertLogInEmployeeIdToString = String.valueOf(userEmployeeID);
                                                        viewAttendance(userEmployeeID, selectedMonth, convertLogInEmployeeIdToString);

                                                        break;
                                
                                                //User wants to view their payslip
                                                case "D":
                                                        System.out.println("\n==== Payslip data available from June 2024 to December 2024 ====\n");
                                                        System.out.println("Select the month for your payslip (Input corresponding number):\n");
                                                        
                                                        String[] dateSelectionForPayslip = {"June", "July", "August", "September", "October", "November", "December"};
                                                        
                                                        for(dateMonthSelect = 0; dateMonthSelect < dateSelectionForPayslip.length; dateMonthSelect++) {
                                                                System.out.println(dateMonthSelect+6 + " " + dateSelectionForPayslip[dateMonthSelect]);
                                                        }
                                
                                                        String selectedMonthPayslip = promptScanner.nextLine();
                                                        String convertLogInEmployeeIdToStringPayslip = String.valueOf(userEmployeeID);
                                                        calculateWorkHours(convertLogInEmployeeIdToStringPayslip, selectedMonthPayslip);
                                                        viewPayslip(userEmployeeID);
                                                        break;
                                                
                                                //User is an admin and would like to do admin specific features.     
                                                case "E": 
                                                        System.out.println("\nMotorPH APS Admin Tools");
                                                        String userPrompt = "";

                                                        //Admin tools: Loop to keep the system continuously prompt user until they opt to Exit.
                                                        while (!userPrompt.equalsIgnoreCase("E")) {
                                                                
                                                                System.out.println("\nA. Search Employee Information\nB. Update Employee Information\nC. Search Employee Payslip\nD. Sync and Approve Employee Payroll\nE. Exit");
                                                                userPrompt = promptScanner.nextLine();

                                                                //Payroll system decision making for Admin tools.
                                                                switch (userPrompt.trim().toUpperCase()) {
                                                                        
                                                                        //Admin wants to search an employee
                                                                        case "A":
                                                                                boolean searchIDValidator = false;
                                                                                int searchEmployeeByID = 0;
                                                                                
                                                                                while (!searchIDValidator) {
                                                                                        System.out.println("Enter Employee ID to search: ");
                                                                                        searchEmployeeByID = promptScanner.nextInt();
                                                                                        promptScanner.nextLine(); 

                                                                                        searchIDValidator = employeeMap.containsKey(searchEmployeeByID);
                                                                                        
                                                                                        if (!searchIDValidator) {
                                                                                        System.out.println("Employee ID not found. Please try again.");
                                                                                        }
                                                                                }
                                                                                searchEmployee(searchEmployeeByID);
                                                                                break;
                                                                        
                                                                        //Admin wants to update an employee information
                                                                        case "B":
                                                                                System.out.println("Enter Employee ID to update: \n");
                                                                                int updateEmployeeByID = promptScanner.nextInt();
                                                                                promptScanner.nextLine(); 
                                                                               
                                                                                if (employeeMap.containsKey(updateEmployeeByID)) {
                                                                                    System.out.println("\nYou've accessed " + employeeMap.get(updateEmployeeByID).firstName + " " + employeeMap.get(updateEmployeeByID).lastName + "'s  profile.\n");
                                                                                    System.out.println("\nWhat type of information would you like to update?");
                                                                                    
                                                                                    String[] fieldSelection = {
                                                                                        "First name", "Last Name", "Birthday", "Address", "Phone no.", "SSS no", "Philhealth no.", 
                                                                                        "Tin no.", "Pagibig no.", "Employment Status", "Position", "Supervisor", "Basic Salary", "Hourly rate"
                                                                                    };
                                                                                    
                                                                                    for (int i = 0; i < fieldSelection.length; i++) {
                                                                                        System.out.println((i + 1) + ". " + fieldSelection[i]);
                                                                                    }
                                                                                   
                                                                                    int updateEmployeePrompt = promptScanner.nextInt();
                                                                                    promptScanner.nextLine(); // Consume newline left-over
                                                                            
                                                                                   
                                                                                    if (updateEmployeePrompt >= 1 && updateEmployeePrompt <= fieldSelection.length) {
                                                                                        System.out.println("You're about to update Employee #" + updateEmployeeByID + "'s " + fieldSelection[updateEmployeePrompt - 1]);
                                                                                        
                                                                                        System.out.println("Type the new " + fieldSelection[updateEmployeePrompt - 1] + " information:");
                                                                                        String newInformation = promptScanner.nextLine();
                                                                            
                                                                                        updateEmployee(updateEmployeeByID, updateEmployeePrompt, newInformation);

                                                                                        System.out.println("Your changes are saved! Employee#" + updateEmployeeByID + "'s " + fieldSelection[updateEmployeePrompt - 1] + " is " + newInformation);
                                                                                    } else {
                                                                                        System.out.println("Invalid selection. Please choose a valid field.");
                                                                                    }
                                                                                } else {
                                                                                    System.out.println("Employee ID not found.");
                                                                                }
                                                                            
                                                                                break;
                                                                        
                                                                        //Admin wants to view an employee's payslip information
                                                                        case "C":
                                                                                System.out.println("Enter Employee ID: ");
                                                                                int userIDToViewPayslip = promptScanner.nextInt();
                                                                                promptScanner.nextLine();
                                                                                if (employeeMap.containsKey(userIDToViewPayslip)) {
                                                                                        viewPayslip(userIDToViewPayslip);
                                                                                }
                                                                                else {
                                                                                        System.out.println("Employee ID not found.");
                                                                                        promptScanner.nextLine();
                                                                                }
                                                                                break;

                                                                        //Admin wants to sync payroll and approve all pending payroll for the cycle. 
                                                                        case "D":
                                                                                System.out.println("DO YOU WISH TO SYNC AND APPROVE EMPLOYEE PAYROLL? Yes or No?");
                                                                                String userprompt = promptScanner.nextLine().trim();
                                                                            
                                                                                if (userprompt.equalsIgnoreCase("YES")) {
                                                                                    System.out.println("Syncing Payroll....\n");
                                                                                    System.out.println("Successfully synced on " + dateNow + "\n");
                                                                            
                                                                                    System.out.println("Approve Payroll? Please enter your Employee ID: \n");
                                                                            
                                                                                    if (promptScanner.hasNextInt()) {
                                                                                        int approvalEmployeeID = promptScanner.nextInt();
                                                                                        promptScanner.nextLine();
                                                                            
                                                                                        if (employeeMap.containsKey(approvalEmployeeID)) {
                                                                                            if (approvalEmployeeID == userEmployeeID) {
                                                                                                System.out.println("\nPayroll approval in progress....\n");
                                                                                                System.out.println("PAYROLL APPROVED! by " + employeeMap.get(approvalEmployeeID).firstName + " " + employeeMap.get(approvalEmployeeID).lastName);
                                                                                            } else {
                                                                                                System.out.println("\nPAYROLL NOT APPROVED! Employee ID entered did not match this account's ID.");
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("\nPAYROLL NOT APPROVED! Invalid Employee ID entered.");
                                                                                        }
                                                                                    } 
                                                                                    else {
                                                                                        System.out.println("Invalid input! Please enter a valid Employee ID.");
                                                                                        promptScanner.nextLine();
                                                                                    }
                                                                                }
                                                                                break;
                                                                                                                                                    
                                                                        
                                                                        default:
                                                                                break;
                                                                }                
                                                        };
                                                        System.out.println("Exiting Admin Tools...");
                                                        continue;
                                                case "F":
                                                        System.out.println("Logging out... Goodbye!");
                                                        break;
                                
                                                default:
                                                        System.out.println("Invalid input. Please try again.");
                                                }
                                        }
                }
                
                }
}