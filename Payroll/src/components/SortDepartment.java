
package components;

import java.util.Comparator;
import pojo.Department;

/**
 * A class for sorting the department data
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortDepartment implements Comparator<Department> { 

    @Override
    public int compare(Department o1, Department o2) {
        return o1.getId() - o2.getId();
    }
}
