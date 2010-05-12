package be.objectify.led.factory;

import org.junit.Test;
import org.junit.Assert;

/**
 * Test cases for the default Enum object factory.
 *
 * @author Steve Chaloner
 */
public class EnumFactoryTest
{
    public enum TestEnumA {A, B, C}

    public enum TestEnumB {c, d, e}

    @Test
    public void testMatchingEntry()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("A");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject(" A");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("A ");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingSpaces()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject(" A ");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("\tA");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("A\t");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingTabs()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("\tA\t");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("\nA");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testTrailingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("A\n");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testLeadingAndTrailingNewlines()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumA.class);
        Enum e = enumFactory.createObject("\nA\n");
        Assert.assertEquals(TestEnumA.A,
                           e);
    }

    @Test
    public void testIncorrectCase()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumB.class);
        Enum e = enumFactory.createObject("D");
        Assert.assertEquals(TestEnumB.d,
                           e);
    }

    @Test
    public void testMissingValue()
    {
        EnumFactory enumFactory = new EnumFactory(TestEnumB.class);
        Enum e = enumFactory.createObject("G");
        Assert.assertNull(e);
    }
}
