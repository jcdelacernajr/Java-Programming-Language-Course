
package components;

import java.util.Comparator;
import pojo.Records;

/**
 * A class for sorting the working days data
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortRecords implements Comparator<Records> { 

    @Override
    public int compare(Records o1, Records o2) {
        return o1.getId() - o2.getId();
    }
}
