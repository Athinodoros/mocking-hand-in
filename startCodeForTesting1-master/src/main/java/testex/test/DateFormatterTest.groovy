package testex.test

import org.junit.Assert
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import testex.DateFormatter
import testex.InvalidTimeZomeException

import java.text.SimpleDateFormat

/**
 * Created by Athinodoros on 3/14/2017.
 */
@RunWith(MockitoJUnitRunner.class)
class DateFormatterTest {
    @Test(expected = InvalidTimeZomeException.class)
    public void testGetFormattedDateInvalid() throws Exception {

        String timeZone = "I am not valid at all";//not valid timeZome
        Date date = new Date();
        DateFormatter df = new DateFormatter();
        String expResult = df.getFormattedDate(timeZone, date);
        Date time = new Date();
        String dateTimeFormat = "dd MMM yyyy hh:mm aa";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateTimeFormat);
        simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String finalTime = simpleFormat.format(time);
        System.out.println("result : " + finalTime);
        assertEquals(expResult, finalTime);
    }


    @Test
    public void testGetFormattedDate() throws Exception {
        String timeZone = TimeZone.getDefault().getID();//Valid across the globe :P
        Date date = new Date();
        DateFormatter df = new DateFormatter();
        String expResult = df.getFormattedDate(timeZone, date);
        Date time = new Date();
        String dateTimeFormat = "dd MMM yyyy hh:mm aa";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateTimeFormat);
        simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String finalTime = simpleFormat.format(time);
        System.out.println("result : " + finalTime);
        Assert.assertEquals(expResult, finalTime);
    }
}
