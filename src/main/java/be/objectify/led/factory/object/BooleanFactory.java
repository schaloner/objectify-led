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

/**
 * Object factory for Boolean instances.
 *
 * @author Steve Chaloner
 */
public class BooleanFactory extends AbstractObjectFactory<Boolean>
{
    /**
     * {@inheritDoc}
     */
    public Boolean createObject(String propertyName,
                                String propertyValue,
                                PropertyContext propertyContext)
    {
        Boolean b = null;
        if (!StringUtils.isEmpty(propertyValue))
        {
            b = Boolean.valueOf(propertyValue);
        }
        return b;
    }

    /**
     * {@inheritDoc}
     */
    public Class<Boolean> getBoundClass()
    {
        return Boolean.class;
    }
}