package be.objectify.led.factory.object;

import be.objectify.led.PropertyContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the default Enum object factory.
 *
 * @author Steve Chaloner
 */
public class EnumFactoryTest
{
    private static final PropertyContext NULL_PROPERTY_CONTEXT = new PropertyContext()
    {
        public String getValue(String propertyName)
        {
            return null;
        }
    };

    public enum TestEnumA {A, B, C}

    public enum TestEnumB {c, d, e}

    @Test
    public void testMatchingEntry()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "A",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          " A",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "A ",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          " A ",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "\tA",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "A\t",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "\tA\t",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "\nA",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "A\n",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "\nA\n",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testIncorrectCase()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumB.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "D",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(TestEnumB.d,
                           e);
    }

    @Test
    public void testMissingValue()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumB.class);
        Enum e = enumFactory.createObject("propertyName",
                                          "G",
                                          NULL_PROPERTY_CONTEXT);
        Assert.assertNull(e);
    }
}
