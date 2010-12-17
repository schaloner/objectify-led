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
package be.objectify.led.factory.type;

import be.objectify.led.FactoryResolver;
import be.objectify.led.GenericTypes;
import be.objectify.led.ObjectFactory;
import be.objectify.led.TypeFactory;
import be.objectify.led.factory.object.SetFactory;
import be.objectify.led.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class SetTypeFactory implements TypeFactory<Set>
{
    private final FactoryResolver factoryResolver;

    public SetTypeFactory(FactoryResolver factoryResolver)
    {
        this.factoryResolver = factoryResolver;
    }

    public SetFactory createObjectFactory(Class[] classes,
                                          Field field)
    {
        return new SetFactory(classes[0],
                              field,
                              factoryResolver)
        {
            protected Collection parse(String propertyName,
                                       String propertyValue,
                                       ObjectFactory objectFactory)
            {
                List list = new ArrayList();
                if (!StringUtils.isEmpty(propertyValue))
                {
                    for (StringTokenizer stringTokenizer = new StringTokenizer(propertyValue); stringTokenizer.hasMoreTokens();)
                    {
                        list.add(objectFactory.createObject(propertyName,
                                                            stringTokenizer.nextToken()));
                    }
                }
                return list;
            }

            protected Set createSet()
            {
                return new HashSet();
            }
        };
    }

    public Class<Set> getBoundClass()
    {
        return Set.class;
    }

    public Class[] determineClassType(Class fieldType,
                                      GenericTypes genericTypes)
    {
        if (genericTypes == null || genericTypes.value().length != 1)
        {
            throw new RuntimeException("GenericTypes(value class) annotation required to determine Collection value type");
        }

        return genericTypes.value();
    }
}