package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Day;
import pojo.Department;
import pojo.EditWorkingDays;
import pojo.Employee;
import pojo.Payroll;
import pojo.Position;
import pojo.Records;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class Writer {
    
    private String filePath;
    private FileOutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    
    /**
     * Constructor
     * 
     * @param fileName 
     */
    public Writer(String fileName) {
        try {
            filePath = Path.uri().toString() + "/" + fileName;
            File file = new File(filePath);
            if(file.createNewFile()) {
                System.out.println("Created "+ file.getAbsolutePath());
            } 
        } catch (URISyntaxException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Reset
     * 
     * This function will clear the edit working days list data
     * into a file
     * 
     * @param clearEditWorkingDaysList 
     */
    public void clearEditWorkingDaysList(ArrayList<EditWorkingDays> clearEditWorkingDaysList) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(clearEditWorkingDaysList);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Reset
     * 
     * This function will add the clearDayList data
     * into a file
     * 
     * @param clearDayList 
     */
    public void clearDayList(ArrayList<Day> clearDayList) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(clearDayList);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     * This function will add the data
     * into a file
     * 
     * @param editWorkingsDayList 
     */
    public void addEditWorkingsDay(ArrayList<EditWorkingDays> editWorkingsDayList) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(editWorkingsDayList);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For payroll
     * 
     * This function will add the payroll data
     * into a file
     * 
     * @param payroll 
     */
    public void addPayroll(ArrayList<Payroll> payroll) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(payroll);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For records
     * 
     * This function will add the records data
     * into a file
     * 
     * @param records  
     */
    public void addRecords(ArrayList<Records> records) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(records);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For day
     * 
     * This function will add the day data
     * into a file
     * 
     * @param day 
     */
    public void addWorkingDay(ArrayList<Day> day) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(day);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For Employee
     * 
     * This function will add the employee data
     * into a file
     * 
     * @param employee 
     */
    public void addEmployee(ArrayList<Employee> employee) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(employee);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For department
     * 
     * This function will add the department data
     * into a file
     * 
     * @param departments
     */
    public void addDepartment(ArrayList<Department> departments) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(departments);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * For position
     * 
     * This function will add the position data
     * into a file
     * 
     * @param positions
     */
    public void addPosition(ArrayList<Position> positions) {
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // Write the data
            objectOutputStream.writeObject(positions);
            // Close the stream connection
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
