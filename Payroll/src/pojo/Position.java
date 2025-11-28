
package pojo;

import java.io.Serializable;

/**
 * Position
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Position implements Serializable {
    
    private int id;
    private int departmentId;
    private String position;

    /**
     * Constructor
     */
    public Position() {
    }

    /**
     * Constructor with parameter
     * 
     * @param id
     * @param departmentId 
     * @param position 
     */
    public Position(int id, int departmentId, String position) {
        this.id = id;
        this.departmentId = departmentId;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
