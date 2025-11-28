package components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Date format for the inputed string
 *
 * @author Juanito C. Dela Cerna Jr. October 4 - 7, 2021
 */
public class DateStringFormat implements DateValidator {

    private String dateFormat;

    public DateStringFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String strDate) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);
        try {
            format.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
