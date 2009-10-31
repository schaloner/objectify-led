/*
 * Copyright 2009 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.objectify.led;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for primitive property bindings.
 *
 * @author Steve Chaloner
 */
public class PropertySetterPrimitiveTest
{
    private PropertySetter propertySetter;

    @Before
    public void before()
    {
        propertySetter = new PropertySetter();
    }

    @After
    public void after()
    {
        propertySetter = null;
    }

    @Test
    public void testBoolean_noPropertySet()
    {
        class BooleanTestObject
        {
            @Property("boolean.property")
            private boolean value = false;
        }
        BooleanTestObject o = new BooleanTestObject();
        propertySetter.process(o);
        Assert.assertFalse(o.value);
    }

    @Test
    public void testBoolean_propertySet()
    {
        class BooleanTestObject
        {
            @Property("boolean.property")
            private boolean value = false;
        }
        BooleanTestObject o = new BooleanTestObject();
        System.setProperty("boolean.property", "true");
        propertySetter.process(o);
        Assert.assertTrue(o.value);
        System.clearProperty("boolean.property");
    }

    @Test
    public void testByte_noPropertySet()
    {
        class ByteTestObject
        {
            @Property("byte.property")
            private byte value = 0;
        }
        ByteTestObject o = new ByteTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0,
                            o.value);
    }

    @Test
    public void testByte_propertySet()
    {
        class ByteTestObject
        {
            @Property("byte.property")
            private byte value;
        }
        ByteTestObject o = new ByteTestObject();
        System.setProperty("byte.property", "5");
        propertySetter.process(o);
        Assert.assertEquals((byte)5,
                            o.value);
        System.clearProperty("byte.property");
    }

    @Test
    public void testShort_noPropertySet()
    {
        class ShortTestObject
        {
            @Property("short.property")
            private short value = 0;
        }
        ShortTestObject o = new ShortTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0,
                            o.value);
    }

    @Test
    public void testShort_propertySet()
    {
        class ShortTestObject
        {
            @Property("short.property")
            private short value;
        }
        ShortTestObject o = new ShortTestObject();
        System.setProperty("short.property", "2767");
        propertySetter.process(o);
        Assert.assertEquals((short)2767,
                            o.value);
        System.clearProperty("short.property");
    }

    @Test
    public void testInteger_noPropertySet()
    {
        class IntegerTestObject
        {
            @Property("integer.property")
            private int value = 0;
        }
        IntegerTestObject o = new IntegerTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0,
                            o.value);
    }

    @Test
    public void testInteger_propertySet()
    {
        class IntegerTestObject
        {
            @Property("integer.property")
            private int value;
        }
        IntegerTestObject o = new IntegerTestObject();
        System.setProperty("integer.property", "40767");
        propertySetter.process(o);
        Assert.assertEquals(40767,
                            o.value);
        System.clearProperty("integer.property");
    }

    @Test
    public void testLong_noPropertySet()
    {
        class LongTestObject
        {
            @Property("long.property")
            private long value = 0;
        }
        LongTestObject o = new LongTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0,
                            o.value);
    }

    @Test
    public void testLong_propertySet()
    {
        class LongTestObject
        {
            @Property("long.property")
            private long value;
        }
        LongTestObject o = new LongTestObject();
        System.setProperty("long.property", "140767");
        propertySetter.process(o);
        Assert.assertEquals(140767,
                            o.value);
        System.clearProperty("long.property");
    }

    @Test
    public void testFloat_noPropertySet()
    {
        class FloatTestObject
        {
            @Property("float.property")
            private float value = 0.0f;
        }
        FloatTestObject o = new FloatTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0.0f,
                            o.value,
                            0);
    }

    @Test
    public void testFloat_propertySet()
    {
        class FloatTestObject
        {
            @Property("float.property")
            private float value;
        }
        FloatTestObject o = new FloatTestObject();
        System.setProperty("float.property", "40767.312f");
        propertySetter.process(o);
        Assert.assertEquals(40767.312f,
                            o.value,
                            0);
        System.clearProperty("float.property");
    }

    @Test
    public void testDouble_noPropertySet()
    {
        class DoubleTestObject
        {
            @Property("double.property")
            private double value = 0;
        }
        DoubleTestObject o = new DoubleTestObject();
        propertySetter.process(o);
        Assert.assertEquals(0,
                            o.value,
                            0);
    }

    @Test
    public void testDouble_propertySet()
    {
        class DoubleTestObject
        {
            @Property("double.property")
            private double value;
        }
        DoubleTestObject o = new DoubleTestObject();
        System.setProperty("double.property", "140767.312");
        propertySetter.process(o);
        Assert.assertEquals(140767.312,
                            o.value,
                            0);
        System.clearProperty("double.property");
    }

    @Test
    public void testCharacter_noPropertySet()
    {
        class CharacterTestObject
        {
            @Property("character.property")
            private char value = 'z';
        }
        CharacterTestObject o = new CharacterTestObject();
        propertySetter.process(o);
        Assert.assertEquals('z',
                            o.value);
    }

    @Test
    public void testCharacter_propertySet()
    {
        class CharacterTestObject
        {
            @Property("character.property")
            private char value;
        }
        CharacterTestObject o = new CharacterTestObject();
        System.setProperty("character.property", "aaaaaa");
        propertySetter.process(o);
        Assert.assertEquals('a',
                            o.value);
        System.clearProperty("character.property");
    }

    @Test
    public void testFinalProperty()
    {
        class FinalTestObject
        {
            @Property("integer.property")
            private final int value = -1;
        }
        FinalTestObject o = new FinalTestObject();
        System.setProperty("integer.property", "13");
        propertySetter.process(o);
        Assert.assertEquals(-1,
                            o.value);
        System.clearProperty("integer.property");
    }

    @Test
    public void testMultipleProperties()
    {
        class MultipleTestObject
        {
            @Property("boolean.property")
            private boolean booleanValue;
            @Property("byte.property")
            private byte byteValue;
            @Property("character.property")
            private char characterValue;
            @Property("double.property")
            private double doubleValue;
            @Property("float.property")
            private float floatValue;
            @Property("integer.property")
            private int integerValue;
            @Property("long.property")
            private long longValue;
            @Property("short.property")
            private short shortValue;
        }
        MultipleTestObject o = new MultipleTestObject();
        System.setProperty("boolean.property", "true");
        System.setProperty("byte.property", "5");
        System.setProperty("character.property", "aaaaaa");
        System.setProperty("double.property", "140767.312");
        System.setProperty("float.property", "40767.312f");
        System.setProperty("integer.property", "40767");
        System.setProperty("long.property", "140767");
        System.setProperty("short.property", "2767");

        propertySetter.process(o);
        Assert.assertEquals(true,
                            o.booleanValue);
        Assert.assertEquals((byte)5,
                            o.byteValue);
        Assert.assertEquals('a',
                            o.characterValue);
        Assert.assertEquals(140767.312,
                            o.doubleValue,
                            0);
        Assert.assertEquals(40767.312f,
                            o.floatValue,
                            0);
        Assert.assertEquals(40767,
                            o.integerValue);
        Assert.assertEquals(140767L,
                            o.longValue);
        Assert.assertEquals((short)2767,
                            o.shortValue);

        System.clearProperty("boolean.property");
        System.clearProperty("byte.property");
        System.clearProperty("character.property");
        System.clearProperty("double.property");
        System.clearProperty("float.property");
        System.clearProperty("integer.property");
        System.clearProperty("long.property");
        System.clearProperty("short.property");
    }

    @Test
    public void testMultiplePrimitiveAndNonPrimitiveProperties()
    {
        class MultipleTestObject
        {
            @Property("boolean.property")
            private boolean booleanValue;
            @Property("byte.property")
            private Byte byteValue;
            @Property("character.property")
            private char characterValue;
            @Property("double.property")
            private Double doubleValue;
            @Property("float.property")
            private float floatValue;
            @Property("integer.property")
            private Integer integerValue;
            @Property("long.property")
            private long longValue;
            @Property("short.property")
            private Short shortValue;
        }
        MultipleTestObject o = new MultipleTestObject();
        System.setProperty("boolean.property", "true");
        System.setProperty("byte.property", "5");
        System.setProperty("character.property", "aaaaaa");
        System.setProperty("double.property", "140767.312");
        System.setProperty("float.property", "40767.312f");
        System.setProperty("integer.property", "40767");
        System.setProperty("long.property", "140767");
        System.setProperty("short.property", "2767");

        propertySetter.process(o);
        Assert.assertEquals(true,
                            o.booleanValue);
        Assert.assertEquals(Byte.valueOf("5"),
                            o.byteValue);
        Assert.assertEquals('a',
                            o.characterValue);
        Assert.assertEquals(Double.valueOf("140767.312"),
                            o.doubleValue,
                            0);
        Assert.assertEquals(40767.312f,
                            o.floatValue,
                            0);
        Assert.assertEquals(Integer.valueOf(40767),
                            o.integerValue);
        Assert.assertEquals(140767L,
                            o.longValue);
        Assert.assertEquals(Short.valueOf("2767"),
                            o.shortValue);

        System.clearProperty("boolean.property");
        System.clearProperty("byte.property");
        System.clearProperty("character.property");
        System.clearProperty("double.property");
        System.clearProperty("float.property");
        System.clearProperty("integer.property");
        System.clearProperty("long.property");
        System.clearProperty("short.property");
    }
}