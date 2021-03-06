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
package be.objectify.led.factory.object;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the default Double object factory.
 *
 * @author Steve Chaloner
 */
public class DoubleFactoryTest extends AbstractConstrainedObjectFactoryTest
{
    protected Class getTargetClass()
    {
        return Double.class;
    }

    protected String getInvalidPropertyValue()
    {
        return "foo";
    }

    protected String getPropertyValue()
    {
        return "12341234.234";
    }

    protected Object getResult()
    {
        return 12341234.234;
    }

    @Test
    public void testDoubleWithComma()
    {
        Object result = factoryResolver.resolveFactory(getTargetClass(),
                                                       null).createObject("propertyName",
                                                                          "12,3456.789",
                                                                          NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(123456.789,
                            result);
    }
}