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

import be.objectify.led.DefaultFactoryResolver;
import be.objectify.led.FactoryResolver;
import be.objectify.led.NullPropertyContext;
import be.objectify.led.PropertyContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Steve Chaloner
 */
public abstract class AbstractObjectFactoryTest
{
    protected static final PropertyContext NULL_PROPERTY_CONTEXT = new NullPropertyContext();

    protected FactoryResolver factoryResolver;

    @Before
    public void setup()
    {
        factoryResolver = new DefaultFactoryResolver();
    }

    @After
    public void tearDown()
    {
        factoryResolver = null;
    }

    @Test
    public void testDefaultFactory_nullPropertyValue() throws Exception
    {
        Assert.assertNull(factoryResolver.resolveFactory(getTargetClass(),
                                                         getField()).createObject("propertyName",
                                                                                  null,
                                                                                  NULL_PROPERTY_CONTEXT));
    }

    @Test
    public void testDefaultFactory_zeroLengthPropertyValue() throws Exception
    {
        Assert.assertNull(factoryResolver.resolveFactory(getTargetClass(),
                                                         getField()).createObject("propertyName",
                                                                                  "",
                                                                                  NULL_PROPERTY_CONTEXT));
    }

    @Test
    public void testDefaultFactory_emptyPropertyValue() throws Exception
    {
        Assert.assertNull(factoryResolver.resolveFactory(getTargetClass(),
                                                         getField()).createObject("propertyName",
                                                                                  "     ",
                                                                                  NULL_PROPERTY_CONTEXT));
    }

    @Test
    public void testDefaultFactory_populatedPropertyValue() throws Exception
    {
        Object result = factoryResolver.resolveFactory(getTargetClass(),
                                                       getField()).createObject("propertyName",
                                                                                  getPropertyValue(),
                                                                                  NULL_PROPERTY_CONTEXT);
        Assert.assertEquals(getResult(),
                            result);
    }

    protected abstract String getPropertyValue();

    protected abstract Object getResult();

    protected abstract Class getTargetClass();

    protected Field getField() throws Exception
    {
        return null;
    }
}
