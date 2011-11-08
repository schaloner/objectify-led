package be.objectify.led.factory.object;

import be.objectify.led.NullPropertyContext;
import be.objectify.led.PropertyContext;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class DateFactoryTest
{
    private static final PropertyContext NULL_PROPERTY_CONTEXT = new NullPropertyContext();

    @Test
    public void testYYYYMMDD()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "20111108",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2011, 10, 8);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testYYYYMMDD_HHMMSS()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "20121108 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2012, 10, 8, 5, 52, 12);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }


    @Test
    public void testYYYY_MM_DD()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "2013-11-08",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2013, 10, 8);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testYYYY_MM_DD_HHMMSS()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "2014-11-08 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2014, 10, 8, 5, 52, 12);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testDDMMYYYY()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08/11/2015",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2015, 10, 8);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testDDMMYYYY_HHMMSS()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08/11/2016 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2016, 10, 8, 5, 52, 12);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testDDMMYY()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08/11/17",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2017, 10, 8);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testDDMMYY_HHMMSS()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08/11/18 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2018, 10, 8, 5, 52, 12);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testNonMatchingDate()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08-11-19 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNull(date);
    }

    @Test
    public void testAddNewFormat()
    {
        DateFactory dateFactory = new DateFactory();
        Date date = dateFactory.createObject("propertyName",
                                             "08-11-20 05:52:12",
                                             NULL_PROPERTY_CONTEXT);

        Assert.assertNull(date);

        boolean removeResult = dateFactory.removeFormat(DateFactory.DD_MM_YY + ' ' + DateFactory.HH_MM_SS);
        Assert.assertTrue(removeResult);

        dateFactory.addFormat("MM/dd/yy " + DateFactory.HH_MM_SS,
                              "[0-9]{1,2}/[0-9]{1,2}/[0-9]{2} " + DateFactory.HH_MM_SS_REGEX);
        date = dateFactory.createObject("propertyName",
                                        "08/11/20 05:52:12",
                                        NULL_PROPERTY_CONTEXT);

        Assert.assertNotNull(date);

        Calendar cal = new GregorianCalendar(2020, 7, 11, 5, 52, 12);
        Assert.assertTrue(date.compareTo(cal.getTime()) == 0);
    }

    @Test
    public void testRemoveFormat_existing()
    {
        DateFactory dateFactory = new DateFactory();
        boolean removeResult = dateFactory.removeFormat(DateFactory.DD_MM_YY + ' ' + DateFactory.HH_MM_SS);
        Assert.assertTrue(removeResult);
    }

    @Test
    public void testRemoveFormat_nonExisting()
    {
        DateFactory dateFactory = new DateFactory();
        boolean removeResult = dateFactory.removeFormat("foo");
        Assert.assertFalse(removeResult);
    }
}
