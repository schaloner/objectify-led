package be.objectify.led.digger;

import be.objectify.led.PropertyDigger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class InheritedDiggingTest
{
    @Test
    public void testInheritedProperties()
    {
        List<String> propertyNames = PropertyDigger.getPropertyNames(InheritedPropertiesClass.class,
                                                                     PropertyDigger.SortOrder.NATURAL);
        Assert.assertEquals(4,
                            propertyNames.size());
        Assert.assertTrue(propertyNames.contains("a"));
        Assert.assertTrue(propertyNames.contains("b"));
        Assert.assertTrue(propertyNames.contains("c"));
        Assert.assertTrue(propertyNames.contains("d"));
    }

    @Test
    public void testNoInheritedProperties()
    {
        List<String> propertyNames = PropertyDigger.getPropertyNames(NoInheritedPropertiesClass.class,
                                                                     PropertyDigger.SortOrder.NATURAL);
        Assert.assertEquals(1,
                            propertyNames.size());
        Assert.assertTrue(propertyNames.contains("d"));
    }


}
