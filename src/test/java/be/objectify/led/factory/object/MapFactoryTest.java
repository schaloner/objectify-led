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

import org.junit.Assert;
import org.junit.Test;

import be.objectify.led.DefaultFactoryResolver;
import be.objectify.led.MapTypes;
import be.objectify.led.TypeFactoryRegistry;
import be.objectify.led.factory.type.MapTypeFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class MapFactoryTest extends AbstractObjectFactoryTest
{
    @MapTypes(key = String.class, value = Integer.class)
    private Map<String, Integer> map;

    @Override
    public void setup()
    {
        TypeFactoryRegistry typeFactoryRegistry = new TypeFactoryRegistry();
        this.factoryResolver = new DefaultFactoryResolver(typeFactoryRegistry);
        typeFactoryRegistry.register(new MapTypeFactory(factoryResolver));
    }

    @Test
    public void testDefaultFactory_nullPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject(null);
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof Map);
        Map map = (Map) o;
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testDefaultFactory_zeroLengthPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject("");
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof Map);
        Map map = (Map) o;
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testDefaultFactory_emptyPropertyValue() throws Exception
    {
        Object o = factoryResolver.resolveFactory(getTargetClass(),
                                                  getField()).createObject("    ");
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof Map);
        Map map = (Map) o;
        Assert.assertTrue(map.isEmpty());
    }

    protected Field getField() throws Exception
    {
        return MapFactoryTest.class.getDeclaredField("map");
    }

    protected Class getTargetClass()
    {
        return Map.class;
    }

    protected String getPropertyValue()
    {
        return "foo:1345";
    }

    protected Map getResult()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 1345);
        return map;
    }
}