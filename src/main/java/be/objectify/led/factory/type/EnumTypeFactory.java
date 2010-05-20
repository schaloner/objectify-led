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

import be.objectify.led.CollectionType;
import be.objectify.led.MapTypes;
import be.objectify.led.TypeFactory;
import be.objectify.led.factory.object.EnumFactory;

import java.lang.reflect.Field;

/**
 * @author Steve Chaloner
 */
public class EnumTypeFactory implements TypeFactory<Enum>
{
    public EnumFactory createObjectFactory(Class[] classes,
                                           Field field)
    {
        return new EnumFactory(classes[0]);
    }

    public Class<Enum> getBoundClass()
    {
        return Enum.class;
    }

    public Class[] determineClassType(Class fieldType,
                                      CollectionType collectionsType,
                                      MapTypes mapTypes)
    {
        return new Class[]{ fieldType };
    }
}
