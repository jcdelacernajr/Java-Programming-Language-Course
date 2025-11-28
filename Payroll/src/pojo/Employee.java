
package pojo;

import java.io.Serializable;

/**
 * Employee
 * 
 * @author Juanito C. Dela Cerna Jr. September 2021
 */
public class Employee implements Serializable {
    
    private int id;
    private String firstName;
    private String sureName;
    private int employeeId;
    private String employeeTye;
    private float rate;
    private int departmentId;
    private int positionId;
    private String createAt;

    /** Constructor */
    public Employee() {
    }

    /** 
     * Constructor with parameter
     * 
     * @param id
     * @param firstName 
     * @param  sureName 
     * @param employeeId 
     * @param employeeTye 
     * @param rate
     * @param departmentId 
     * @param positionId 
     * @param createAt 
     */
    public Employee(int id, String firstName, String sureName, int employeeId, 
            String employeeTye, float rate, int departmentId, int positionId, 
            String createAt) {
        this.id = id;
        this.firstName = firstName;
        this.sureName = sureName;
        this.employeeId = employeeId;
        this.employeeTye = employeeTye;
        this.rate = rate;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeTye() {
        return employeeTye;
    }

    public void setEmployeeTye(String employeeTye) {
        this.employeeTye = employeeTye;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
    
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    
}
