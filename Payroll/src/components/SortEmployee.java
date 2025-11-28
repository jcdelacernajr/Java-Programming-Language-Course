
package components;

import java.util.Comparator;
import pojo.Employee;

/**
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortEmployee implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getId() - o2.getId();
    }
    
}
