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
package be.objectify.led.factory.object;

import be.objectify.led.DefaultFactoryResolver;
import be.objectify.led.GenericTypes;
import be.objectify.led.TypeFactoryRegistry;
import be.objectify.led.factory.type.ListTypeFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class ListFactoryTest extends AbstractObjectFactoryTest
{
    @GenericTypes(String.class)
    private List<String> list;

    @Override
    public void setup()
    {
        TypeFactoryRegistry typeFactoryRegistry = new TypeFactoryRegistry();
        this.factoryResolver = new DefaultFactoryResolver(typeFactoryRegistry);
        typeFactoryRegistry.register(new ListTypeFactory(factoryResolver));
    }

    @Test
    public void testDefaultFactory_nullPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject("propertyName",
                                                                           null);
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof List);
        List list = (List)o;
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testDefaultFactory_zeroLengthPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject("propertyName",
                                                                           "");
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof List);
        List list = (List)o;
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testDefaultFactory_emptyPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject("propertyName",
                                                                           "    ");
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof List);
        List list = (List)o;
        Assert.assertTrue(list.isEmpty());
    }

    protected Field getField() throws Exception
    {
        return ListFactoryTest.class.getDeclaredField("list");
    }

    protected Class getTargetClass()
    {
        return List.class;
    }

    protected String getPropertyValue()
    {
        return "foo bar";
    }

    protected List getResult()
    {
        return Arrays.asList("foo", "bar");
    }
}