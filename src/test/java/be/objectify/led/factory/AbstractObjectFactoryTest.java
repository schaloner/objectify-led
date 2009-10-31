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
package be.objectify.led.factory;

import be.objectify.led.ObjectFactoryRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Steve Chaloner
 */
public abstract class AbstractObjectFactoryTest
{
    private ObjectFactoryRegistry registry;

    @Before
    public void setup()
    {
        registry = new ObjectFactoryRegistry();
    }

    @After
    public void tearDown()
    {
        registry = null;
    }

    @Test
    public void testDefaultFactory_nullPropertyValue()
    {
        Assert.assertNull(registry.getFactory(getTargetClass()).createObject(null));
    }

    @Test
    public void testDefaultFactory_zeroLengthPropertyValue()
    {
        Assert.assertNull(registry.getFactory(getTargetClass()).createObject(""));
    }

    @Test
    public void testDefaultFactory_emptyPropertyValue()
    {
        Assert.assertNull(registry.getFactory(getTargetClass()).createObject("     "));
    }

    @Test
    public void testDefaultFactory_populatedPropertyValue()
    {
        Object result = registry.getFactory(getTargetClass()).createObject(getPropertyValue());
        Assert.assertEquals(getResult(),
                            result);
    }

    protected ObjectFactoryRegistry getRegistry()
    {
        return registry;
    }

    protected abstract String getPropertyValue();

    protected abstract Object getResult();

    protected abstract Class getTargetClass();
}
