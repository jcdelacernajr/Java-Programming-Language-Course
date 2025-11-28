/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class EditWorkingDays extends Day implements Serializable {
    
    private int recordId;

    public EditWorkingDays(int recordId, int id, int employeeId, String date, int day, int hours, int overtime) {
        super(id, employeeId, date, day, hours, overtime);
        this.recordId = recordId;
    }

    public EditWorkingDays(int recordId) {
        this.recordId = recordId;
    }

    public EditWorkingDays() {
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    
}
