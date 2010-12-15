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
package be.objectify.led.factory.type;

import be.objectify.led.FactoryResolver;
import be.objectify.led.GenericTypes;
import be.objectify.led.ObjectFactory;
import be.objectify.led.TypeFactory;
import be.objectify.led.factory.object.MapFactory;
import be.objectify.led.util.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class MapTypeFactory implements TypeFactory<Map>
{
    private static final Logger LOGGER = Logger.getLogger(MapTypeFactory.class);

    private final FactoryResolver factoryResolver;

    public MapTypeFactory(FactoryResolver factoryResolver)
    {
        this.factoryResolver = factoryResolver;
    }

    public MapFactory createObjectFactory(Class[] classes,
                                          Field field)
    {
        return new MapFactory(classes[0],
                              classes[1],
                              field,
                              factoryResolver)
        {
            protected Map parse(String propertyName,
                                String propertyValue,
                                ObjectFactory keyObjectFactory,
                                ObjectFactory valueObjectFactory)
            {
                Map map = new HashMap();
                if (!StringUtils.isEmpty(propertyValue))
                {
                    for (StringTokenizer stringTokenizer = new StringTokenizer(propertyValue); stringTokenizer.hasMoreTokens();)
                    {
                        String s = stringTokenizer.nextToken();
                        String[] strings = s.split(":");
                        if (strings.length == 2)
                        {
                            map.put(keyObjectFactory.createObject(propertyName,
                                                                  strings[0]),
                                    valueObjectFactory.createObject(propertyName,
                                                                    strings[1]));
                        }
                        else
                        {
                            LOGGER.error(String.format("Could not separate [%s] into key:value",
                                                       propertyValue));
                        }
                    }
                }
                return map;
            }

            protected Map createMap()
            {
                return new HashMap();
            }
        };
    }

    public Class<Map> getBoundClass()
    {
        return Map.class;
    }

    public Class[] determineClassType(Class fieldType,
                                      GenericTypes genericTypes)
    {
        if (genericTypes == null || genericTypes.value().length != 2)
        {
            throw new RuntimeException("GenericTypes(key class, value class) annotation required to determine Map key:value types");
        }
        Class[] classes = genericTypes.value();
        return new Class[]{ classes[0], classes[1] };
    }
}