package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class Read {

    private String filePath;
    private FileInputStream inputStream;
    private ObjectInputStream objectInputStream;

    /**
     * Constructor
     *
     * @param fileName  
     */
    public Read(String fileName) {
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
     * Read the data in file and return
     * 
     * @return edit working days list
     */
    public ArrayList<EditWorkingDays> editWorkingDaysList() {
        ArrayList<EditWorkingDays> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<EditWorkingDays>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Read the data in file and return
     * 
     * @return payroll list
     */
    public ArrayList<Payroll> payrollList() {
        ArrayList<Payroll> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Payroll>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Read the data in file and return
     * 
     * @return records list
     */
    public ArrayList<Records> recordList() {
        ArrayList<Records> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Records>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
     /**
     * Read the data in file and return
     * 
     * @return working days list
     */
    public ArrayList<Day> workingDayList() {
        ArrayList<Day> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Day>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Read the data in file and return
     * 
     * @return employee list
     */
    public ArrayList<Employee> employeeList() {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Employee>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Read the data in file and return
     * 
     * @return department list
     */
    public ArrayList<Department> departmentList() {
        ArrayList<Department> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Department>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    /**
     * Read the data in file and return
     * 
     * @return position list
     */
    public ArrayList<Position> positionList() {
        ArrayList<Position> result = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            result = (ArrayList<Position>) objectInputStream.readObject();
            // Close the stream connection
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
