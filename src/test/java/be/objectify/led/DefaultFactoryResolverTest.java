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

import org.junit.Assert;
import org.junit.Test;

import be.objectify.led.factory.type.ListTypeFactory;

import java.util.List;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class DefaultFactoryResolverTest
{
    @GenericTypes(Boolean.class)
    private List<Boolean> list;

    @Test
    public void testDefaultValues() throws Exception
    {
        FactoryResolver factoryResolver = new DefaultFactoryResolver();
        ObjectFactory stringFactory = factoryResolver.resolveFactory(String.class,
                                                                     null);
        Assert.assertNotNull(stringFactory);

        ObjectFactory listFactory = factoryResolver.resolveFactory(List.class,
                                                                     DefaultFactoryResolverTest.class.getDeclaredField("list"));
        Assert.assertNull(listFactory);
    }

    @Test
    public void testResolverWithPopulatedTypeFactoryRegistry() throws Exception
    {
        TypeFactoryRegistry registry = new TypeFactoryRegistry();
        FactoryResolver factoryResolver = new DefaultFactoryResolver(registry);
        registry.register(new ListTypeFactory(factoryResolver));
        ObjectFactory stringFactory = factoryResolver.resolveFactory(String.class,
                                                                     null);
        Assert.assertNotNull(stringFactory);

        ObjectFactory listFactory = factoryResolver.resolveFactory(List.class,
                                                                     DefaultFactoryResolverTest.class.getDeclaredField("list"));
        Assert.assertNotNull(listFactory);
    }
}
