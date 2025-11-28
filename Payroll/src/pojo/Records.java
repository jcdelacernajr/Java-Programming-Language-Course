
package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Records extends Employee implements Serializable {
    
    private String dateFrom;
    private String dateTo;
    private double salary;
    private String employeName;
    private float rateOfPay;
    private double overtimeRate;
    private double allowance;
    private int noOvertimeHours;
    private int hoursAbsence;
    
    public Records() {}

    public Records(String dateFrom, String dateTo, double salary, String employeName, 
            float rateOfPay, double overtimeRate, double allowance, int noOvertimeHours, 
            int hoursAbsence, int id,
            String firstName, String sureName, int employeeId, String employeeTye, 
            float rate, int departmentId, int positionId, String createAt) {
        super(id, firstName, sureName, employeeId, employeeTye, rate, departmentId, positionId, createAt);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.salary = salary;
        this.employeName = employeName;
        this.rateOfPay = rateOfPay;
        this.overtimeRate = overtimeRate;
        this.allowance = allowance;
        this.noOvertimeHours = noOvertimeHours;
        this.hoursAbsence = hoursAbsence;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmployeName() {
        return employeName;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }

    public float getRateOfPay() {
        return rateOfPay;
    }

    public void setRateOfPay(float rateOfPay) {
        this.rateOfPay = rateOfPay;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public int getNoOvertimeHours() {
        return noOvertimeHours;
    }

    public void setNoOvertimeHours(int noOvertimeHours) {
        this.noOvertimeHours = noOvertimeHours;
    }

    public int getHoursAbsence() {
        return hoursAbsence;
    }

    public void setHoursAbsence(int hoursAbsence) {
        this.hoursAbsence = hoursAbsence;
    }

}
