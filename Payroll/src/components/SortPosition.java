
package components;

import java.util.Comparator;
import pojo.Position;

/**
 * A class for sorting the position data
 * 
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class SortPosition implements Comparator<Position> { 

    @Override
    public int compare(Position o1, Position o2) {
        return o1.getId() - o2.getId();
    }
}
