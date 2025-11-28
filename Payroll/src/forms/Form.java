package forms;

import components.DateStringFormat;
import components.SortWorkingDays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import library.Read;
import pojo.Data;
import pojo.Day;
import pojo.Employee;

/**
 * Base form class
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Form extends javax.swing.JFrame {

    public Form() {
    }

    /**
     * Employee salary
     *
     * @param emloyeeId
     * @param totalHours
     * @param totalOverTime
     * @param totalAbsences
     * @return
     */
    public double employeeTeachingSalary(int emloyeeId, int totalHours,
            int totalOverTime, int totalAbsences) {
        double result = 0f;
        // Computation for employee type "Teaching"
        // Employee rate
        float rate = employeeRate(emloyeeId);
        // in 1 week is 40 hours
        double weeklyRate = rate * 40;
        System.out.println("rate: " + rate + " weekly: " + weeklyRate);
        // in 1 absent is 8 hours
        double deduction = rate * (totalAbsences * 8);
        // in 4 weeks is 160 hours
        double allowance = weeklyRate * 0.1;
        // overtime, total hour * 140% * overtime rate
        double overtime = (totalOverTime * 1.4) * getOvertimeRate(rate);
        // no. of hour
        int noOfHours = totalHours >= 40 ? 40 : totalHours;
        // Salary, if total hour greate than or equal to 40 set it to 40
        result = (noOfHours * rate + overtime + allowance) - deduction;

        //System.out.println("Deduction: " + deduction);
        //System.out.println("allowance: " + allowance);
        //System.out.println("overtime: " + overtime);
        //System.out.println("result: " + result);
        return result;
    }

    /**
     *
     * @param totalHours
     */
    public int getNoOfHoursTeaching(int totalHours) {
        int maximumHourPerweek = 40;
        if (totalHours <= maximumHourPerweek) {
            return totalHours;
        }
        return 40;
    }

    /**
     * This function is for teaching employee only
     *
     * @param totalHours
     * @return
     */
    public int getOverTimeForTeachingEmployee(int totalHours) {
        int maximumHourPerweek = 40, maximumOvertimeHour = 5;
        if (totalHours > maximumHourPerweek) {
            int overtime = totalHours - maximumHourPerweek;
            if (overtime > maximumOvertimeHour) {
                // If overtime is greated than 5
                // Return 5 for overtime if the acumulated overtime greater than 5
                // 5 is the maximum overtime for employee type teaching 
                return maximumOvertimeHour;
            }
            return overtime;
        }
        return 0;
    }

    /**
     * This function is for teaching employee only
     *
     * @param otRate
     * @param totalHours
     * @return
     */
    public double getOverTimeForTeachingEmployee(double otRate, int totalHours) {
        int maximumHourPerweek = 40, maximumOvertimeHour = 5;
        if (totalHours > maximumHourPerweek) {
            if (totalHours > maximumOvertimeHour) {
                totalHours = maximumOvertimeHour;
            }
        }
        return (totalHours * 1.4) * otRate;
    }

    /**
     * Get date from and to
     *
     * @param employeeId
     * @return
     */
    public String[] getDate(int employeeId) {
        String[] result = new String[2];
        ArrayList<Day> dayList = new Read(Data.WORKING_DAYS).workingDayList();
        Collections.sort(dayList, new SortWorkingDays().reversed());
        LinkedList<String> list = new LinkedList<>();
        for (Day day : dayList) {
            if (employeeId == day.getEmployeeId()) {
                list.add(day.getDate());
            }
        }
        result[0] = list.getFirst();
        result[1] = list.getLast();
        return result;
    }

    /**
     * Get employee allowance base in the amount of her/his salary
     *
     * @param salary
     * @return allowance
     */
    public double getEmployeeAllowance(double salary) {
        return salary * 0.1;
    }

    /**
     * @return
     */
    public double totalDeduction(double rate, int noOfAbsentHour) {
        return rate * noOfAbsentHour;
    }

    /**
     * Get total absences 1 month
     *
     * @param totalAbsences
     * @return
     */
    public int getTotalAbsencesInHour(int totalAbsences) {
        return (totalAbsences * 8);
    }

    /**
     * Get overtime
     *
     *
     * @param oTrate
     * @param totalOverTime
     * @return
     */
    public double getOvertime(double oTrate, int totalOverTime) {
        return (totalOverTime * 1.4) * oTrate;
    }

    /**
     * Get overtime rate
     *
     * Overtime rate is computed by rate * 140* / totalOvertime
     *
     * @param rate
     * @return overtime rate
     */
    public double getOvertimeRate(double rate) {
        return rate / 8;
    }

    /**
     * Get employee pay rate
     *
     * @param employeeId
     * @return
     */
    public float employeeRate(int employeeId) {
        // Get the list
        ArrayList<Employee> employeeList = new Read(Data.EMPLOYEE).employeeList();
        for (Employee e : employeeList) {
            if (employeeId == e.getEmployeeId()) {
                return e.getRate();
            }
        }
        return 0f;
    }

    /**
     * Get employee type
     *
     * @param employeeId
     * @return
     */
    public String employeeType(int employeeId) {
        // Get the list
        ArrayList<Employee> employeeList = new Read(Data.EMPLOYEE).employeeList();
        for (Employee e : employeeList) {
            if (employeeId == e.getEmployeeId()) {
                return e.getEmployeeTye();
            }
        }
        return "";
    }

    /**
     * Get employee
     *
     * @param employeeId
     * @return
     */
    public Employee getEmployee(int employeeId) {
        // Get the list
        ArrayList<Employee> employeeList = new Read(Data.EMPLOYEE).employeeList();
        for (Employee e : employeeList) {
            if (employeeId == e.getEmployeeId()) {
                return e;
            }
        }
        return null;
    }

    /**
     * Date field validation
     *
     * @param title
     * @param jTextField
     * @return
     */
    public String dateFieldValidation(String title, JTextField jTextField) {
        String result = "";
        if (new DateStringFormat("YYYY-dd-mm").isValid(jTextField.getText())) {
            // Return in string format
            result = jTextField.getText();
        } else {
            JOptionPane.showMessageDialog(this, "Please input a " + title + " format(YYYY-dd-mm)", "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        }
        return result;
    }

    /**
     * Working hours field validation
     *
     * @param title
     * @param jTextField
     * @param type
     * @return
     */
    public String workingHoursValidation(String title, String type, JTextField jTextField) {
        String result = "";
        try {
            int value = Integer.parseInt(jTextField.getText());
            if (type.equals("Teaching") && value < 6 && value != 0) {
                JOptionPane.showMessageDialog(this, "Manimum working hours per day "
                        + "is 6 for the employee type Teaching", "WARNING", JOptionPane.WARNING_MESSAGE);
                jTextField.requestFocus();
            } else if (type.equals("Non - Teaching") && value > 8 && value != 0) {
                JOptionPane.showMessageDialog(this, "Maximum of 8 hours working per day, "
                        + "Enter the hours excess on overtime", "WARNING", JOptionPane.WARNING_MESSAGE);
                jTextField.requestFocus();
            } else {
                // Return in string format
                result = String.valueOf(value);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a number for the " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        }
        return result;
    }

    /**
     * Number field validation
     *
     * @param title
     * @param jTextField
     * @param type
     * @return
     */
    public String oTValidation(String title, String type, JTextField jTextField) {
        String result = "";
        try {
            int value = Integer.parseInt(jTextField.getText());
            if (type.equals("Non - Teaching") && value > 4) {
                JOptionPane.showMessageDialog(this, "Maximum overtime per day is 4 hours only", "WARNING", JOptionPane.WARNING_MESSAGE);
                jTextField.requestFocus();
            } else {
                // Return in string format
                result = String.valueOf(value);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a number for the " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        }
        return result;
    }

    /**
     * Employee id field validation
     *
     * @param title
     * @param jTextField
     * @return
     */
    public String idFieldValidation(String title, JTextField jTextField) {
        String result = "";
        try {
            Long value = Long.parseLong(jTextField.getText());
            // Limit the employee id to Integer Max value
            if (value > Integer.MAX_VALUE - 1) {
                JOptionPane.showMessageDialog(this, jTextField.getText() + " Value is to large for the " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                // Return in string format
                result = String.valueOf(value);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a Integer number for the " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        }
        return result;
    }

    /**
     * Number field validation
     *
     * @param title
     * @param jTextField
     * @return
     */
    public String numberFieldValidation(String title, JTextField jTextField) {
        String result = "";
        try {
            float value = Float.parseFloat(jTextField.getText());
            // Return in string format
            result = String.valueOf(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input a number for the " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        }
        return result;
    }

    /**
     * Text field validation
     *
     * @param title
     * @param jTextField
     * @return
     */
    public String textFieldValidation(String title, JTextField jTextField) {
        String result = "";
        if (jTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please input a " + title, "WARNING", JOptionPane.WARNING_MESSAGE);
            jTextField.requestFocus();
        } else {
            result = jTextField.getText();
        }
        return result;
    }

}
