
package pojo;

import java.io.Serializable;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Payroll implements Serializable {
    
    private int id;
    private int employeeId;
    private int recordsId;
    private String date;
    private int day;
    private int hours;
    private int overtime;

    public Payroll(int id, int employeeId, int recordsId, String date, int day, int hours, int overtime) {
        this.id = id;
        this.employeeId = employeeId;
        this.recordsId = recordsId;
        this.date = date;
        this.day = day;
        this.hours = hours;
        this.overtime = overtime;
    }

    public Payroll() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getRecordsId() {
        return recordsId;
    }

    public void setRecordsId(int recordsId) {
        this.recordsId = recordsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }
}
