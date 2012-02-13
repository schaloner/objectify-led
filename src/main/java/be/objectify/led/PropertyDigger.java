package be.objectify.led;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public enum SortOrder implements PropertySorter
    {
        NATURAL {
            public void sort(List<Property> properties)
            {
                // no-op
            }
        },
        BY_NAME {
            public void sort(List<Property> properties)
            {
                Collections.sort(properties,
                                 new Comparator<Property>()
                                 {
                                     public int compare(Property propertyA,
                                                        Property propertyB)
                                     {
                                         return propertyA.value().compareTo(propertyB.value());
                                     }
                                 });
            }
        }
    }

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
     * @param propertySorter sorts the properties into the required order
     * @return the property names
     */
    public static List<String> getPropertyNames(Class c,
                                                PropertySorter propertySorter)
    {
        List<Property> properties = getProperties(c,
                                                  propertySorter);
        List<String> propertyNames = new ArrayList<String>();
        for (Property property : properties)
        {
            propertyNames.add(property.value());
        }

        return propertyNames;
    }

    public static List<Property> getProperties(Class c)
    {
        return getProperties(c,
                             SortOrder.NATURAL);
    }

    public static List<Property> getProperties(Class c,
                                               PropertySorter propertySorter)
    {
        List<Property> properties = new ArrayList<Property>();
        Field[] fields;
        if (c.isAnnotationPresent(InheritProperties.class))
        {
            fields = c.getFields();
        }
        else
        {
            fields = c.getDeclaredFields();
        }
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class))
            {
                properties.add(field.getAnnotation(Property.class));
            }
        }

        propertySorter.sort(properties);

        return properties;
    }
}
