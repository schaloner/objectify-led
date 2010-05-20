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

import org.apache.log4j.Logger;

import be.objectify.led.util.ContractUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.lang.annotation.Annotation;

/**
 * Registry of {@link TypeFactory} for creating objects of the correct type based on the field.
 *
 * @author Steve Chaloner
 */
public class TypeFactoryRegistry
{
    private static final Logger LOGGER = Logger.getLogger(TypeFactoryRegistry.class);

    private final Map<Class, TypeFactory> typeFactories = new LinkedHashMap<Class, TypeFactory>();

    /**
     * Registers the type factories with the registry.
     *
     * @param typeFactories the type factories.  None of the factories can be null.
     */
    public void register(TypeFactory... typeFactories)
    {
        ContractUtils.nonNull(typeFactories,
                              "typeFactories");

        register(Arrays.asList(typeFactories));
    }

    /**
     * Registers the type factories with the registry.
     *
     * @param typeFactories the type factories to register.  Must not be null; none of contents
     */
    public void register(List<TypeFactory> typeFactories)
    {
        ContractUtils.nonNull(typeFactories,
                              "typeFactories");

        for (TypeFactory typeFactory : typeFactories)
        {
            this.typeFactories.put(typeFactory.getBoundClass(),
                                   typeFactory);
        }
    }

    /**
     * Gets the type factory for the given class.
     *
     * @param clazz the class of the required object
     * @return the type factory, or null if no factory is registered for the class
     */
    public TypeFactory getFactory(Class clazz)
    {
        TypeFactory factory = typeFactories.get(clazz);
        if (factory == null)
        {
            Set<Class> keySet = typeFactories.keySet();
            for (Iterator<Class> it = keySet.iterator(); factory == null && it.hasNext();)
            {
                Class c = it.next();
                if (c.isAssignableFrom(clazz))
                {
                    factory = typeFactories.get(c);
                    if (LOGGER.isDebugEnabled())
                    {
                        LOGGER.debug(String.format("Found type factory [%s] for class [%s] using supertype [%s]",
                                                   factory,
                                                   clazz.getCanonicalName(),
                                                   c.getCanonicalName()));
                    }
                }
            }
            if (factory == null && LOGGER.isDebugEnabled())
            {
                LOGGER.debug(String.format("Could not find type factory for class [%s]",
                                           clazz.getCanonicalName()));
            }
        }
        else
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(String.format("Found type factory [%s] for class [%s]",
                                           factory,
                                           clazz.getCanonicalName()));
            }
        }

        return factory;
    }
}