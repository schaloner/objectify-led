package be.objectify.led;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gets the names of properties defined in a class.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
public final class PropertyDigger
{
    private PropertyDigger()
    {
        // no-op
    }

    public enum SortOrder { NATURAL, BY_NAME }

    /**
     * Gets the names of properties declared in the given class, listed
     * in the order by which reflection finds them.
     *
     * @param c the class
     * @return the property names
     */
    public static List<String> getPropertyNames(Class c)
    {
        return getPropertyNames(c,
                                SortOrder.NATURAL);
    }

    /**
     * Gets the names of properties declared in the given class.
     *
     * @param c the class
     * @param sortOrder the order in which the names should be placed
     * @return the property names
     */
    public static List<String> getPropertyNames(Class c,
                                                SortOrder sortOrder)
    {
        List<String> propertyNames = new ArrayList<String>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class))
            {
                propertyNames.add(field.getAnnotation(Property.class).value());
            }
        }

        if (sortOrder == SortOrder.BY_NAME)
        {
            Collections.sort(propertyNames);
        }

        return propertyNames;
    }
}
