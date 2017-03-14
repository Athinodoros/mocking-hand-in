package testex;

import java.util.Date;

/**
 * Created by Athinodoros on 3/14/2017.
 */
public interface IDateFormatter {

    public String getFormattedDate(String timeZone,Date Date) throws InvalidTimeZomeException;
}
