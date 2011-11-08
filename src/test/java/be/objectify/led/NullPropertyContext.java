package be.objectify.led;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class NullPropertyContext implements PropertyContext
{
    public String getValue(String propertyName)
    {
        return null;
    }
}
