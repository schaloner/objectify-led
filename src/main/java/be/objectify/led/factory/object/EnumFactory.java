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

import org.apache.log4j.Logger;

/**
 * Object factory for Enums.
 *
 * @author Steve Chaloner
 */
public class EnumFactory extends AbstractObjectFactory<Enum>
{
    private static final Logger LOGGER = Logger.getLogger(EnumFactory.class);

    private final Class<? extends Enum> clazz;

    public EnumFactory(Class<? extends Enum> clazz)
    {
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    public Enum createObject(String propertyName,
                             String propertyValue)
    {
        Enum value = null;

        if (propertyValue != null)
        {
            propertyValue = propertyValue.trim();
            try
            {
                value = getValue(clazz,
                                 propertyValue);
            }
            catch (IllegalArgumentException e)
            {
                LOGGER.error(String.format("Unable to parse %s to enum of type [%s]",
                                           propertyValue,
                                           clazz),
                             e);
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public Class<Enum> getBoundClass()
    {
        return Enum.class;
    }

    private Enum getValue(Class<? extends Enum> clazz,
                          String value) throws IllegalArgumentException
    {
        Enum e;
        try
        {
            e = Enum.valueOf(clazz,
                             value);
        }
        catch (IllegalArgumentException e1)
        {
            try
            {
                e = Enum.valueOf(clazz,
                                 value.toUpperCase());
            }
            catch (IllegalArgumentException e2)
            {
                e = Enum.valueOf(clazz,
                                 value.toLowerCase());
            }
        }

        return e;
    }
}