
package pojo;

import java.io.Serializable;

/**
 * Department
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Department implements Serializable {
    
    private int id;
    private String department;

    /** Constructor*/
    public Department() {
    }
    
    /**
     * Constructor
     * 
     * @param id
     * @param department 
     */
    public Department(int id, String department) {
        this.id = id;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    
}
