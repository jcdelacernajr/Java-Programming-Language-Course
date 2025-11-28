
package components;

import java.util.Comparator;
import pojo.Payroll;

/**
 * A class for sorting the working days data
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortPayroll implements Comparator<Payroll> { 

    @Override
    public int compare(Payroll o1, Payroll o2) {
        return o1.getId() - o2.getId();
    }
}
