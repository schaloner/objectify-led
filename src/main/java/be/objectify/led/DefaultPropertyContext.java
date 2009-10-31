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

import be.objectify.led.util.ContractUtils;
import be.objectify.led.util.StringUtils;

import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Default implementation of {@link PropertyContext} which
 *
 * @author Steve Chaloner
 */
public class DefaultPropertyContext implements PropertyContext
{
    private static final Logger LOGGER = Logger.getLogger(DefaultPropertyContext.class);

    private final Properties properties = new Properties();

    public DefaultPropertyContext(Properties... properties)
    {
        ContractUtils.nonNull(properties, "properties");

        for (Properties propertiesInstance : properties)
        {
            this.properties.putAll(propertiesInstance);
        }
    }

    public String getValue(String propertyName)
    {
        String value = null;

        if (properties.containsKey(propertyName))
        {
            value = properties.getProperty(propertyName);
        }

        String property = System.getProperty(propertyName);
        if (!StringUtils.isEmpty(property))
        {
            value = property;
        }

        if (value != null && LOGGER.isDebugEnabled())
        {
            LOGGER.debug(String.format("Found value [%s] for property [%s]",
                                       value,
                                       propertyName));
        }

        return value;
    }
}
