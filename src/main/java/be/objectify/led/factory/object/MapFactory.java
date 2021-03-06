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
import be.objectify.led.PropertyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Steve Chaloner
 */
public abstract class MapFactory<K, V> extends AbstractObjectFactory<Map>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MapFactory.class);

    private final Class keyClass;

    private final Class valueClass;

    private final Field field;

    private final FactoryResolver factoryResolver;

    public MapFactory(Class keyClass,
                      Class valueClass,
                      Field field,
                      FactoryResolver factoryResolver)
    {
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.field = field;
        this.factoryResolver = factoryResolver;
    }

    public Map<K, V> createObject(String propertyName,
                                  String propertyValue,
                                  PropertyContext propertyContext)
    {
        Map<K, V> map = createMap();
        ObjectFactory keyObjectFactory = factoryResolver.resolveFactory(keyClass,
                                                                        field);
        ObjectFactory valueObjectFactory = factoryResolver.resolveFactory(valueClass,
                                                                          field);
        if (keyObjectFactory == null || valueObjectFactory == null)
        {
            LOGGER.info("No factory available for map: key factory [{}]   value factory [{}]",
                        keyClass.getCanonicalName(),
                        valueClass.getCanonicalName());
        }
        else
        {
            Map<K, V> values = parse(propertyName,
                                     propertyValue,
                                     propertyContext,
                                     keyObjectFactory,
                                     valueObjectFactory);
            if (values != null)
            {
                map.putAll(values);
            }
            else
            {
                LOGGER.info("Property value [{}] did not parse into map of type [{}][{}]",
                            new Object[]{
                                    propertyValue,
                                    keyClass.getCanonicalName(),
                                    valueClass.getCanonicalName()});
            }
        }

        return map;
    }

    public Class<Map> getBoundClass()
    {
        return Map.class;
    }

    protected abstract Map<K, V> parse(String propertyName,
                                       String propertyValue,
                                       PropertyContext propertyContext,
                                       ObjectFactory<K> keyObjectFactory,
                                       ObjectFactory<V> valueObjectFactory);

    protected abstract Map<K, V> createMap();
}