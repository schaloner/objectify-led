package be.objectify.led.factory.object;

import be.objectify.led.PropertyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Default supported date formats
 * <ul>
 *     <li>yyyyMMdd</li>
 *     <li>yyyyMMdd HH:mm:ss</li>
 *     <li>yyyy-MM-dd</li>
 *     <li>yyyy-MM-dd HH:mm:ss</li>
 *     <li>dd/MM/yyyy</li>
 *     <li>dd/MM/yyyy HH:mm:ss</li>
 *     <li>dd/MM/yy</li>
 *     <li>dd/MM/yy HH:mm:ss</li>
 * </ul>
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
public class DateFactory extends AbstractObjectFactory<Date>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ListFactory.class);

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DD_MM_YY = "dd/MM/yy";
    public static final String HH_MM_SS = "HH:mm:ss";

    public static final String YYYYMMDD_REGEX = "[0-9]{8}";
    public static final String YYYY_MM_DD_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    public static final String DD_MM_YYYY_REGEX = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
    public static final String DD_MM_YY_REGEX = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}";
    public static final String HH_MM_SS_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    private final Map<String, Pattern> dateFormats = new HashMap<String, Pattern>(8);

    public DateFactory()
    {
        addFormat(YYYY_MM_DD, YYYY_MM_DD_REGEX);
        addFormat(YYYY_MM_DD + ' ' + HH_MM_SS, YYYY_MM_DD_REGEX + ' ' + HH_MM_SS_REGEX);
        addFormat(DD_MM_YYYY, DD_MM_YYYY_REGEX);
        addFormat(DD_MM_YYYY + ' ' + HH_MM_SS, DD_MM_YYYY_REGEX + ' ' + HH_MM_SS_REGEX);
        addFormat(DD_MM_YY, DD_MM_YY_REGEX);
        addFormat(DD_MM_YY + ' ' + HH_MM_SS, DD_MM_YY_REGEX + ' ' + HH_MM_SS_REGEX);
        addFormat(YYYYMMDD, YYYYMMDD_REGEX);
        addFormat(YYYYMMDD + ' ' + HH_MM_SS, YYYYMMDD_REGEX + ' ' + HH_MM_SS_REGEX);
    }

    /** {@inheritDoc} */
    public Date createObject(String propertyName,
                             String propertyValue,
                             PropertyContext propertyContext)
    {
        // this might seem inefficient, but it allows you to deal with multiple date formats

        Date date = null;
        for (Iterator<Map.Entry<String, Pattern>> iterator = dateFormats.entrySet().iterator(); date == null && iterator.hasNext();)
        {
            Map.Entry<String, Pattern> entry = iterator.next();
            if (entry.getValue().matcher(propertyValue).matches())
            {
                try
                {
                    DateFormat dateFormat = new SimpleDateFormat(entry.getKey());
                    date = dateFormat.parse(propertyValue);
                }
                catch (ParseException e)
                {
                    LOGGER.error(String.format("Unable to convert %s using format [%s]",
                                               propertyValue,
                                               entry.getValue()));
                }
            }
        }

        if (date == null)
        {
            LOGGER.error("Unable to find a match for property [{}] with value [{}]",
                         propertyName,
                         propertyValue);
        }

        return date;

    }

    public boolean removeFormat(String dateFormat)
    {
        Pattern pattern = dateFormats.remove(dateFormat);
        if (pattern != null)
        {
            LOGGER.info("Removed pattern [{}]",
                        dateFormat);
        }
        else
        {
            LOGGER.info("Couldn't remove pattern [{}] - nothing matched",
                        dateFormat);
        }

        return pattern != null;
    }

    /**
     * Add or override an existing date format.
     * @param dateFormat the date format as described by {@SimpleDateFormat}
     * @param regexPattern the regex pattern matching the date you pass into the factory
     */
    public void addFormat(String dateFormat,
                          String regexPattern)
    {
        dateFormats.put(dateFormat,
                        Pattern.compile(regexPattern));
    }

    public Class<Date> getBoundClass()
    {
        return Date.class;
    }
}
