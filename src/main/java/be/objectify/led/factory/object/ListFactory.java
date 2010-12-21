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

import be.objectify.led.FactoryResolver;
import be.objectify.led.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * @author Steve Chaloner
 */
public abstract class ListFactory<T> extends AbstractObjectFactory<List>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ListFactory.class);

    private final Class clazz;

    private final Field field;

    private final FactoryResolver factoryResolver;

    public ListFactory(Class clazz,
                       Field field,
                       FactoryResolver factoryResolver)
    {
        this.clazz = clazz;
        this.field = field;
        this.factoryResolver = factoryResolver;
    }

    public List<T> createObject(String propertyName,
                                String propertyValue)
    {
        List<T> list = createList();
        ObjectFactory objectFactory = factoryResolver.resolveFactory(clazz,
                                                                     field);
        if (objectFactory == null)
        {
            LOGGER.info("No factory available for type [{}]",
                        clazz.getCanonicalName());
        }
        else
        {
            Collection<T> values = parse(propertyName,
                                         propertyValue,
                                         objectFactory);
            if (values != null)
            {
                list.addAll(values);
            }
            else
            {
                LOGGER.info("Property value [{}] did not parse into collection of type [{}]",
                            propertyValue,
                            clazz.getCanonicalName());
            }
        }

        return list;
    }

    public Class<List> getBoundClass()
    {
        return List.class;
    }

    protected abstract Collection<T> parse(String propertyName,
                                           String propertyValue,
                                           ObjectFactory<T> objectFactory);

    protected abstract List<T> createList();
}
