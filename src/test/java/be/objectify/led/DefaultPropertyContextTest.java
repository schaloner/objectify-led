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

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Test cases for multiple property sources, for example provided Properties
 * and system properties.
 *
 * @author Steve Chaloner
 */
public class DefaultPropertyContextTest
{
    @Test
    public void testValueFromProperty()
    {
        PropertyContext propertyContext = new DefaultPropertyContext(new Properties());
        PropertySetter propertySetter = new PropertySetter(propertyContext);
        System.setProperty("string.property", "test value");

        StringTestObject o = new StringTestObject();

        propertySetter.process(o);
        Assert.assertEquals("test value",
                            o.value);
        System.clearProperty("string.property");
    }

    @Test
    public void testValueFromProvidedProperty()
    {
        Properties properties = new Properties();
        properties.setProperty("string.property", "test value");
        System.clearProperty("string.property");
        PropertyContext propertyContext = new DefaultPropertyContext(properties);
        PropertySetter propertySetter = new PropertySetter(propertyContext);

        StringTestObject o = new StringTestObject();

        propertySetter.process(o);
        Assert.assertEquals("test value",
                            o.value);
        System.clearProperty("string.property");
    }

    @Test
    public void testOverriddenByPropertyValue()
    {
        Properties properties = new Properties();
        properties.setProperty("string.property", "aaaaa");
        System.setProperty("string.property", "bbbbb");
        PropertyContext propertyContext = new DefaultPropertyContext(properties);
        PropertySetter propertySetter = new PropertySetter(propertyContext);

        StringTestObject o = new StringTestObject();

        propertySetter.process(o);
        Assert.assertEquals("bbbbb",
                            o.value);
        System.clearProperty("string.property");
    }


    @Test
    public void testOverriddenBySubsequentPropertyValue()
    {
        Properties propertiesA = new Properties();
        propertiesA.setProperty("string.property", "aaaaa");
        Properties propertiesB = new Properties();
        propertiesB.setProperty("string.property", "bbbbb");
        System.clearProperty("string.property");
        PropertyContext propertyContext = new DefaultPropertyContext(propertiesA,
                                                                     propertiesB);
        PropertySetter propertySetter = new PropertySetter(propertyContext);

        StringTestObject o = new StringTestObject();

        propertySetter.process(o);
        Assert.assertEquals("bbbbb",
                            o.value);
        System.clearProperty("string.property");
    }

    private class StringTestObject
    {
        @Property("string.property")
        private String value;
    }

}