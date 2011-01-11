package be.objectify.led;

import java.util.List;

/**
 * Allows for user-defined property sorting.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
public interface PropertySorter
{
    /**
     * Sort the list of properties into the required order.
     *
     * @param properties the properties
     */
    void sort(List<Property> properties);
}
