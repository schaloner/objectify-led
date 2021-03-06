/*
 * Copyright 2009-2010 Steve Chaloner
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

import be.objectify.led.factory.type.EnumTypeFactory;
import be.objectify.led.factory.type.ListTypeFactory;
import be.objectify.led.validation.ValidationException;
import be.objectify.led.validation.ValidationFunction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test cases for all default object factory property types.
 *
 * @author Steve Chaloner
 */
public class PropertySetterTest
{
    private PropertySetter propertySetter;

    @Before
    public void before()
    {
        TypeFactoryRegistry typeFactoryRegistry = new TypeFactoryRegistry();
        FactoryResolver factoryResolver = new DefaultFactoryResolver(typeFactoryRegistry);
        typeFactoryRegistry.register(new EnumTypeFactory());
        typeFactoryRegistry.register(new ListTypeFactory(factoryResolver));
        propertySetter = new PropertySetter(factoryResolver,
                                            new DefaultPropertyContext());
    }

    @After
    public void after()
    {
        propertySetter = null;
    }

    @Test
    public void testString_noPropertySet()
    {
        class StringTestObject
        {
            @Property("string.property")
            private String value;
        }
        StringTestObject o = new StringTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testString_propertySet()
    {
        class StringTestObject
        {
            @Property("string.property")
            private String value;
        }
        StringTestObject o = new StringTestObject();
        System.setProperty("string.property",
                           "test value");
        propertySetter.process(o);
        Assert.assertEquals("test value",
                            o.value);
        System.clearProperty("string.property");
    }

    @Test
    public void testBoolean_noPropertySet()
    {
        class BooleanTestObject
        {
            @Property("boolean.property")
            private Boolean value;
        }
        BooleanTestObject o = new BooleanTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testBoolean_propertySet()
    {
        class BooleanTestObject
        {
            @Property("boolean.property")
            private Boolean value;
        }
        BooleanTestObject o = new BooleanTestObject();
        System.setProperty("boolean.property",
                           "true");
        propertySetter.process(o);
        Assert.assertEquals(Boolean.TRUE,
                            o.value);
        System.clearProperty("boolean.property");
    }

    @Test
    public void testByte_noPropertySet()
    {
        class ByteTestObject
        {
            @Property("byte.property")
            private Byte value;
        }
        ByteTestObject o = new ByteTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testByte_propertySet()
    {
        class ByteTestObject
        {
            @Property("byte.property")
            private Byte value;
        }
        ByteTestObject o = new ByteTestObject();
        System.setProperty("byte.property",
                           "5");
        propertySetter.process(o);
        Assert.assertEquals(Byte.valueOf("5"),
                            o.value);
        System.clearProperty("byte.property");
    }

    @Test
    public void testShort_noPropertySet()
    {
        class ShortTestObject
        {
            @Property("short.property")
            private Short value;
        }
        ShortTestObject o = new ShortTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testShort_propertySet()
    {
        class ShortTestObject
        {
            @Property("short.property")
            private Short value;
        }
        ShortTestObject o = new ShortTestObject();
        System.setProperty("short.property",
                           "2767");
        propertySetter.process(o);
        Assert.assertEquals(Short.valueOf("2767"),
                            o.value);
        System.clearProperty("short.property");
    }

    @Test
    public void testInteger_noPropertySet()
    {
        class IntegerTestObject
        {
            @Property("integer.property")
            private Integer value;
        }
        IntegerTestObject o = new IntegerTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testInteger_propertySet()
    {
        class IntegerTestObject
        {
            @Property("integer.property")
            private Integer value;
        }
        IntegerTestObject o = new IntegerTestObject();
        System.setProperty("integer.property",
                           "40767");
        propertySetter.process(o);
        Assert.assertEquals(Integer.valueOf(40767),
                            o.value);
        System.clearProperty("integer.property");
    }

    @Test
    public void testLong_noPropertySet()
    {
        class LongTestObject
        {
            @Property("long.property")
            private Long value;
        }
        LongTestObject o = new LongTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testLong_propertySet()
    {
        class LongTestObject
        {
            @Property("long.property")
            private Long value;
        }
        LongTestObject o = new LongTestObject();
        System.setProperty("long.property",
                           "140767");
        propertySetter.process(o);
        Assert.assertEquals(Long.valueOf(140767),
                            o.value);
        System.clearProperty("long.property");
    }

    @Test
    public void testFloat_noPropertySet()
    {
        class FloatTestObject
        {
            @Property("float.property")
            private Float value;
        }
        FloatTestObject o = new FloatTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testFloat_propertySet()
    {
        class FloatTestObject
        {
            @Property("float.property")
            private Float value;
        }
        FloatTestObject o = new FloatTestObject();
        System.setProperty("float.property",
                           "40767.312f");
        propertySetter.process(o);
        Assert.assertEquals(Float.valueOf(40767.312f),
                            o.value);
        System.clearProperty("float.property");
    }

    @Test
    public void testDouble_noPropertySet()
    {
        class DoubleTestObject
        {
            @Property("double.property")
            private Double value;
        }
        DoubleTestObject o = new DoubleTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testDouble_propertySet()
    {
        class DoubleTestObject
        {
            @Property("double.property")
            private Double value;
        }
        DoubleTestObject o = new DoubleTestObject();
        System.setProperty("double.property",
                           "140767.312");
        propertySetter.process(o);
        Assert.assertEquals(Double.valueOf(140767.312),
                            o.value);
        System.clearProperty("double.property");
    }

    @Test
    public void testCharacter_noPropertySet()
    {
        class CharacterTestObject
        {
            @Property("character.property")
            private Character value;
        }
        CharacterTestObject o = new CharacterTestObject();
        propertySetter.process(o);
        Assert.assertNull(o.value);
    }

    @Test
    public void testCharacter_propertySet()
    {
        class CharacterTestObject
        {
            @Property("character.property")
            private Character value;
        }
        CharacterTestObject o = new CharacterTestObject();
        System.setProperty("character.property",
                           "aaaaaa");
        propertySetter.process(o);
        Assert.assertEquals(Character.valueOf('a'),
                            o.value);
        System.clearProperty("character.property");
    }

    @Test
    public void testFinalProperty_defaultValue()
    {
        class FinalTestObject
        {
            @Property("string.property")
            private final String value = null;
        }
        FinalTestObject o = new FinalTestObject();
        System.setProperty("string.property",
                           "test value");
        propertySetter.process(o);
        Assert.assertNull(o.value);
        System.clearProperty("string.property");
    }

    @Test
    public void testFinalProperty_explicitlyDisabled()
    {
        class FinalTestObject
        {
            @Property("string.property")
            private final String value = null;
        }
        FinalTestObject o = new FinalTestObject();
        System.setProperty("string.property",
                           "test value");
        propertySetter.getConfiguration().setAllowFinalSetting(false);
        propertySetter.process(o);
        Assert.assertNull(o.value);
        System.clearProperty("string.property");
    }

    @Test
    public void testFinalProperty_explicitlyEnabled()
    {
        class FinalTestObject
        {
            @Property("string.property")
            private final String value = null;
        }
        FinalTestObject o = new FinalTestObject();
        System.setProperty("string.property",
                           "test value");
        propertySetter.getConfiguration().setAllowFinalSetting(true);
        propertySetter.process(o);
        Assert.assertEquals("test value",
                            o.value);
        System.clearProperty("string.property");
    }

    @Test(expected = ValidationException.class)
    public void testValidationFailure()
    {
        class TestObject
        {
            @Property("string.property")
            private String value = null;
        }
        TestObject o = new TestObject();
        System.setProperty("string.property",
                           "test value");
        try
        {
            propertySetter.process(o,
                                   new ValidationFunction()
                                   {
                                       public void validate(String propertyName,
                                                            String propertyValue) throws ValidationException
                                       {
                                           throw new ValidationException("string.property",
                                                                         "purely for test purposes");
                                       }
                                   });
            Assert.fail();
        }
        catch (ValidationException e)
        {
            Assert.assertEquals("string.property",
                                e.getFieldName());
            Assert.assertEquals("purely for test purposes",
                                e.getReason());
            Assert.assertNull(e.getCause());
            throw e;
        }
        System.clearProperty("string.property");
    }


    @Test(expected = ValidationException.class)
    public void testValidationFailureWithCause()
    {
        class TestObject
        {
            @Property("string.property")
            private String value = null;
        }
        TestObject o = new TestObject();
        System.setProperty("string.property",
                           "test value");
        try
        {
            propertySetter.process(o,
                                   new ValidationFunction()
                                   {
                                       public void validate(String propertyName,
                                                            String propertyValue) throws ValidationException
                                       {
                                           throw new ValidationException("string.property",
                                                                         "purely for test purposes",
                                                                         new RuntimeException());
                                       }
                                   });
            Assert.fail();
        }
        catch (ValidationException e)
        {
            Assert.assertEquals("string.property",
                                e.getFieldName());
            Assert.assertEquals("purely for test purposes",
                                e.getReason());
            Assert.assertNotNull(e.getCause());
            throw e;
        }
        System.clearProperty("string.property");
    }

    enum TestEnum
    {
        A,
        B
    }

    @Test
    public void testEnum()
    {
        class EnumTestObject
        {
            @Property("enum.property")
            private TestEnum value;
        }
        EnumTestObject o = new EnumTestObject();
        System.setProperty("enum.property",
                           "A");
        propertySetter.process(o);
        Assert.assertEquals(TestEnum.A,
                            o.value);

        System.clearProperty("string.property");
    }

    @Test
    public void testStringList()
    {
        class StringListTestObject
        {
            @Property("list.string.property")
            @GenericTypes(String.class)
            private List<String> value;
        }
        StringListTestObject o = new StringListTestObject();
        System.setProperty("list.string.property",
                           "A B C");
        propertySetter.process(o);
        Assert.assertEquals(Arrays.asList("A",
                                          "B",
                                          "C"),
                            o.value);

        System.clearProperty("list.string.property");
    }

    @Test
    public void testIntegerList()
    {
        class StringListTestObject
        {
            @Property("list.integer.property")
            @GenericTypes(Integer.class)
            private List<Integer> value;
        }
        StringListTestObject o = new StringListTestObject();
        System.setProperty("list.integer.property",
                           "-4532 1 45 34587");
        propertySetter.process(o);
        Assert.assertEquals(Arrays.asList(-4532,
                                          1,
                                          45,
                                          34587),
                            o.value);

        System.clearProperty("list.integer.property");
    }

    @Test
    public void testIntList()
    {
        class StringListTestObject
        {
            @Property("list.int.property")
            @GenericTypes(int.class)
            private List<Integer> value;
        }
        StringListTestObject o = new StringListTestObject();
        System.setProperty("list.int.property",
                           "-4532 1 45 34587");
        propertySetter.process(o);
        Assert.assertEquals(Arrays.asList(-4532,
                                          1,
                                          45,
                                          34587),
                            o.value);

        System.clearProperty("list.int.property");
    }

    @Test
    public void testEnumList()
    {
        class StringListTestObject
        {
            @Property("list.enum.property")
            @GenericTypes(TestEnum.class)
            private List<TestEnum> value;
        }
        StringListTestObject o = new StringListTestObject();
        System.setProperty("list.enum.property",
                           "A B");
        propertySetter.process(o);
        Assert.assertEquals(Arrays.asList(TestEnum.A,
                                          TestEnum.B),
                            o.value);

        System.clearProperty("list.enum.property");
    }

    @Test
    public void testMultipleProperties()
    {
        class MultipleTestObject
        {
            @Property("boolean.property")
            private Boolean   booleanValue;
            @Property("byte.property")
            private Byte      byteValue;
            @Property("character.property")
            private Character characterValue;
            @Property("double.property")
            private Double    doubleValue;
            @Property("float.property")
            private Float     floatValue;
            @Property("integer.property")
            private Integer   integerValue;
            @Property("long.property")
            private Long      longValue;
            @Property("short.property")
            private Short     shortValue;
            @Property("string.property")
            private String    stringValue;
        }
        MultipleTestObject o = new MultipleTestObject();
        System.setProperty("boolean.property",
                           "true");
        System.setProperty("byte.property",
                           "5");
        System.setProperty("character.property",
                           "aaaaaa");
        System.setProperty("double.property",
                           "140767.312");
        System.setProperty("float.property",
                           "40767.312f");
        System.setProperty("integer.property",
                           "40767");
        System.setProperty("long.property",
                           "140767");
        System.setProperty("short.property",
                           "2767");
        System.setProperty("string.property",
                           "test value");

        propertySetter.process(o);
        Assert.assertEquals(Boolean.TRUE,
                            o.booleanValue);
        Assert.assertEquals(Byte.valueOf("5"),
                            o.byteValue);
        Assert.assertEquals(Character.valueOf('a'),
                            o.characterValue);
        Assert.assertEquals(Double.valueOf(140767.312),
                            o.doubleValue);
        Assert.assertEquals(Float.valueOf(40767.312f),
                            o.floatValue);
        Assert.assertEquals(Integer.valueOf(40767),
                            o.integerValue);
        Assert.assertEquals(Long.valueOf(140767),
                            o.longValue);
        Assert.assertEquals(Short.valueOf("2767"),
                            o.shortValue);
        Assert.assertEquals("test value",
                            o.stringValue);

        System.clearProperty("boolean.property");
        System.clearProperty("byte.property");
        System.clearProperty("character.property");
        System.clearProperty("double.property");
        System.clearProperty("float.property");
        System.clearProperty("integer.property");
        System.clearProperty("long.property");
        System.clearProperty("short.property");
        System.clearProperty("string.property");
    }
}
