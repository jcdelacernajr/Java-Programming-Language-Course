
package components;

import java.util.Comparator;
import pojo.Day;

/**
 * A class for sorting the working days data
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortWorkingDays implements Comparator<Day> { 

    @Override
    public int compare(Day o1, Day o2) {
        return o1.getId() - o2.getId();
    }
}
