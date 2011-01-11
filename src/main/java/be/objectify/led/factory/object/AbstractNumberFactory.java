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

import be.objectify.led.PropertyContext;
import be.objectify.led.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for parsing numbers from Strings.
 * 
 * @author Steve Chaloner
 */
public abstract class AbstractNumberFactory<T> extends AbstractObjectFactory<T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNumberFactory.class);

    /**
     * {@inheritDoc}
     */
    public T createObject(String propertyName,
                          String propertyValue,
                          PropertyContext propertyContext)
    {
        T t = null;

        if (!StringUtils.isEmpty(propertyValue))
        {
            try
            {
                t = parse(propertyValue);
            }
            catch (NumberFormatException e)
            {
                LOGGER.error("Unable to parse [{}] to number",
                             propertyValue,
                             e);
            }
        }

        return t;
    }

    /**
     * Parse the property value to extract an object of the correct type from it.
     * 
     * @param propertyValue the source value.  Must not be null
     * @return the parsed value
     */
    protected abstract T parse(String propertyValue);
}
