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

import be.objectify.led.factory.type.ListTypeFactory;
import be.objectify.led.validation.ValidationFunction;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class DefaultFactoryResolverTest
{
    @GenericTypes(Boolean.class)
    private List<Boolean> list;

    /**
     * Tests the default factory resolver with a default {@link TypeFactoryRegistry}
     * and {@link ObjectFactoryRegistry}.
     *
     * @throws Exception if an exception occurs
     */
    @Test
    public void testDefaultValues_defaultConstructor() throws Exception
    {
        FactoryResolver factoryResolver = new DefaultFactoryResolver();
        ObjectFactory stringFactory = factoryResolver.resolveFactory(String.class,
                                                                     null);
        Assert.assertNotNull(stringFactory);

        ObjectFactory listFactory = factoryResolver.resolveFactory(List.class,
                                                                   DefaultFactoryResolverTest.class.getDeclaredField("list"));
        Assert.assertNull(listFactory);
    }

    /**
     * Tests the default factory resolver with a custom {@link TypeFactoryRegistry}.
     *
     * @throws Exception if an exception occurs
     */
    @Test
    public void testResolverWithPopulatedTypeFactoryRegistry_tfrConstructor() throws Exception
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

    /**
     * Tests the default factory resolver with a custom {@link ObjectFactoryRegistry}.
     *
     * @throws Exception if an exception occurs
     */
    @Test
    public void testResolverWithPopulatedTypeFactoryRegistry_ofrConstructor() throws Exception
    {
        ObjectFactoryRegistry registry = new ObjectFactoryRegistry();
        FactoryResolver factoryResolver = new DefaultFactoryResolver(registry);
        registry.register(new ObjectFactory<String>()
        {
            public String createObject(String propertyName,
                                       String propertyValue,
                                       PropertyContext propertyContext)
            {
                return "foo";
            }

            public Class<String> getBoundClass()
            {
                return String.class;
            }

            public void validate(String propertyName,
                                 String propertyValue,
                                 ValidationFunction... validationFunctions)
            {
            }
        });
        ObjectFactory stringFactory = factoryResolver.resolveFactory(String.class,
                                                                     null);
        Assert.assertNotNull(stringFactory);
        Assert.assertEquals("foo",
                            stringFactory.createObject("",
                                                       "",
                                                       new PropertyContext()
                                                       {
                                                           public String getValue(String propertyName)
                                                           {
                                                               return null;
                                                           }
                                                       }
                                                      )
                           );
        Assert.assertEquals(String.class,
                            stringFactory.getBoundClass());
    }

    /**
     * Tests the default factory resolver with a custom {@link TypeFactoryRegistry}
     * and {@link ObjectFactoryRegistry}.
     *
     * @throws Exception if an exception occurs
     */
    @Test
    public void testResolverWithPopulatedTypeFactoryRegistry_ofrTfrConstructor() throws Exception
    {
        TypeFactoryRegistry typeFactoryRegistry = new TypeFactoryRegistry();
        ObjectFactoryRegistry objectFactoryRegistry = new ObjectFactoryRegistry();
        FactoryResolver factoryResolver = new DefaultFactoryResolver(objectFactoryRegistry,
                                                                     typeFactoryRegistry);


        objectFactoryRegistry.register(new ObjectFactory<String>()
        {
            public String createObject(String propertyName,
                                       String propertyValue,
                                       PropertyContext propertyContext)
            {
                return "foo";
            }

            public Class<String> getBoundClass()
            {
                return String.class;
            }

            public void validate(String propertyName,
                                 String propertyValue,
                                 ValidationFunction... validationFunctions)
            {
            }
        });
        typeFactoryRegistry.register(new ListTypeFactory(factoryResolver));
        ObjectFactory stringFactory = factoryResolver.resolveFactory(String.class,
                                                                     null);
        Assert.assertNotNull(stringFactory);
        Assert.assertEquals("foo",
                            stringFactory.createObject("",
                                                       "",
                                                       new PropertyContext()
                                                       {
                                                           public String getValue(String propertyName)
                                                           {
                                                               return null;
                                                           }
                                                       }
                                                      )
                           );
        Assert.assertEquals(String.class,
                            stringFactory.getBoundClass());

        ObjectFactory listFactory = factoryResolver.resolveFactory(List.class,
                                                                   DefaultFactoryResolverTest.class.getDeclaredField("list"));
        Assert.assertNotNull(listFactory);
    }
}
