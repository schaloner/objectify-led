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

import org.apache.log4j.Logger;

import be.objectify.led.ObjectFactory;
import be.objectify.led.FactoryResolver;

import java.util.Collection;
import java.util.Set;
import java.lang.reflect.Field;

/**
 * @author Steve Chaloner
 */
public abstract class SetFactory<T> implements ObjectFactory<Set>
{
    private static final Logger LOGGER = Logger.getLogger(SetFactory.class);

    private final Class clazz;

    private final Field field;

    private final FactoryResolver factoryResolver;

    public SetFactory(Class clazz,
                       Field field,
                       FactoryResolver factoryResolver)
    {
        this.clazz = clazz;
        this.field = field;
        this.factoryResolver = factoryResolver;
    }

    public Set<T> createObject(String propertyValue)
    {
        Set<T> set = createSet();
        ObjectFactory objectFactory = factoryResolver.resolveFactory(clazz,
                                                                     field);
        if (objectFactory == null)
        {
            if (LOGGER.isInfoEnabled())
            {
                LOGGER.info(String.format("No factory available for type [%s]",
                                          clazz.getCanonicalName()));
            }
        }
        else
        {
            Collection<T> values = parse(propertyValue,
                                         objectFactory);
            if (values != null)
            {
                set.addAll(values);
            }
            else if (LOGGER.isInfoEnabled())
            {
                LOGGER.info(String.format("Property value [%s] did not parse into collection of type [%s]",
                                          propertyValue,
                                          clazz.getCanonicalName()));
            }
        }

        return set;
    }

    public Class<Set> getBoundClass()
    {
        return Set.class;
    }

    protected abstract Collection<T> parse(String propertyValue,
                                           ObjectFactory<T> objectFactory);

    protected abstract Set<T> createSet();
}