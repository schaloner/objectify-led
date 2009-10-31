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
 * Test cases for all default object factory property types.
 *
 * @author Steve Chaloner
 */
public class StaticPropertySetterTest
{
    private PropertySetter propertySetter;

    @Before
    public void before()
    {
        propertySetter = new PropertySetter();
        StaticTestClass.VALUE = null;
        StaticAndInstanceTestClass.STATIC_VALUE = null;
    }

    @After
    public void after()
    {
        propertySetter = null;
    }

    @Test
    public void testStaticValue()
    {
        System.setProperty("string.property", "test value");
        propertySetter.process(StaticTestClass.class);
        Assert.assertEquals("test value",
                            StaticTestClass.VALUE);
        System.clearProperty("string.property");
    }

    @Test
    public void testStaticValueViaInstance()
    {
        StaticTestClass o = new StaticTestClass();
        System.setProperty("string.property", "test value");
        propertySetter.process(o);
        Assert.assertEquals("test value",
                            StaticTestClass.VALUE);
        System.clearProperty("string.property");
    }

    @Test
    public void testStaticAndInstanceValueViaInstance()
    {
        StaticAndInstanceTestClass o = new StaticAndInstanceTestClass();
        System.setProperty("string.property", "test value");
        System.setProperty("integer.property", "45");
        propertySetter.process(o);
        Assert.assertEquals("test value",
                            StaticAndInstanceTestClass.STATIC_VALUE);
        Assert.assertEquals(45,
                            o.intValue);
        System.clearProperty("string.property");
        System.clearProperty("integer.property");
    }

    @Test
    public void testStaticWithInstanceValueViaClass()
    {
        System.setProperty("string.property", "test value");
        System.setProperty("integer.property", "45");
        propertySetter.process(StaticAndInstanceTestClass.class);
        Assert.assertEquals("test value",
                            StaticAndInstanceTestClass.STATIC_VALUE);
        System.clearProperty("string.property");
        System.clearProperty("integer.property");
    }

    @Test
    public void testFinalProperty()
    {
        System.setProperty("string.property", "test value");
        propertySetter.process(FinalStaticTestClass.class);
        Assert.assertNull(FinalStaticTestClass.VALUE);
        System.clearProperty("string.property");
    }

    public static class StaticTestClass
    {
        @Property("string.property")
        private static String VALUE;
    }

    public static class FinalStaticTestClass
    {
        @Property("string.property")
        private static final String VALUE = null;
    }

    public static class StaticAndInstanceTestClass
    {
        @Property("string.property")
        private static String STATIC_VALUE;

        @Property("integer.property")
        private int intValue;
    }
}