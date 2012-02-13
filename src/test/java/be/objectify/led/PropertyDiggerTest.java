package be.objectify.led;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assert.assertTrue(propertyNames.contains("c"));
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
        Assert.assertTrue(propertyNames.contains("c"));
    }

    @Test
    public void testMeta_default()
    {
        List<Property> properties = PropertyDigger.getProperties(MetaTestObject.class);
        Assert.assertEquals(3,
                            properties.size());

        Map<String, Property> mappedProperties = new HashMap<String, Property>(3);
        for (Property property : properties)
        {
            mappedProperties.put(property.value(),
                                 property);
        }

        String[] meta = mappedProperties.get("no-meta").meta();
        Assert.assertNotNull(meta);
        Assert.assertEquals(0,
                            meta.length);
    }

    @Test
    public void testMeta_singleMeta()
    {
        List<Property> properties = PropertyDigger.getProperties(MetaTestObject.class);
        Assert.assertEquals(3,
                            properties.size());

        Map<String, Property> mappedProperties = new HashMap<String, Property>(3);
        for (Property property : properties)
        {
            mappedProperties.put(property.value(),
                                 property);
        }

        String[] meta = mappedProperties.get("single-meta").meta();
        Assert.assertNotNull(meta);
        Assert.assertEquals(1,
                            meta.length);
        Assert.assertNotNull(meta[0]);
        Assert.assertEquals("foo",
                            meta[0]);
    }

    @Test
    public void testMeta_multiMeta()
    {
        List<Property> properties = PropertyDigger.getProperties(MetaTestObject.class);
        Assert.assertEquals(3,
                            properties.size());

        Map<String, Property> mappedProperties = new HashMap<String, Property>(3);
        for (Property property : properties)
        {
            mappedProperties.put(property.value(),
                                 property);
        }

        String[] meta = mappedProperties.get("multi-meta").meta();
        Assert.assertNotNull(meta);
        Assert.assertEquals(2,
                            meta.length);
        Assert.assertNotNull(meta[0]);
        Assert.assertEquals("foo",
                            meta[0]);
        Assert.assertNotNull(meta[1]);
        Assert.assertEquals("bar",
                            meta[1]);
    }

    public static class OrderingTestObject
    {
        @Property("a")
        private String valueA;

        @Property("c")
        private String valueC;

        @Property("b")
        private String valueB;
    }

    public static class MetaTestObject
    {
        @Property("no-meta")
        private String noMeta;

        @Property(value = "single-meta", meta = "foo")
        private String valueB;

        @Property(value = "multi-meta", meta = { "foo", "bar" })
        private String valueC;
    }
}
