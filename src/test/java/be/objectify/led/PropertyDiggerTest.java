package be.objectify.led;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public class PropertyDiggerTest
{
    @Test
    public void testByNameOrdering()
    {
        List<String> propertyNames = PropertyDigger.getPropertyNames(OrderingTestObject.class,
                                                                     PropertyDigger.SortOrder.BY_NAME);
        Assert.assertEquals(3,
                            propertyNames.size());
        Assert.assertEquals("a",
                            propertyNames.get(0));
        Assert.assertEquals("b",
                            propertyNames.get(1));
        Assert.assertEquals("c",
                            propertyNames.get(2));
    }

    @Test
    public void testDefaultNaturalOrdering()
    {

        List<String> propertyNames = PropertyDigger.getPropertyNames(OrderingTestObject.class);
        Assert.assertEquals(3,
                            propertyNames.size());
        Assert.assertTrue(propertyNames.contains("a"));
        Assert.assertTrue(propertyNames.contains("b"));
        Assert.assertTrue(propertyNames.contains("b"));
    }

    @Test
    public void testExplicitNaturalOrdering()
    {

        List<String> propertyNames = PropertyDigger.getPropertyNames(OrderingTestObject.class,
                                                                     PropertyDigger.SortOrder.NATURAL);
        Assert.assertEquals(3,
                            propertyNames.size());
        Assert.assertTrue(propertyNames.contains("a"));
        Assert.assertTrue(propertyNames.contains("b"));
        Assert.assertTrue(propertyNames.contains("b"));
    }

    public static class OrderingTestObject
    {
        @Property("a")
        private String valueA;

        @Property("c")
        private String valueB;

        @Property("b")
        private String valueC;
    }
}
