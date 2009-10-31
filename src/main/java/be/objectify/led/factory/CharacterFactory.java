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
package be.objectify.led.factory;

import be.objectify.led.ObjectFactory;
import be.objectify.led.util.StringUtils;

/**
 * Object factory for Character instances.
 *
 * @author Steve Chaloner
 */
public class CharacterFactory implements ObjectFactory<Character>
{
    /**
     * {@inheritDoc}
     */
    public Character createObject(String propertyValue)
    {
        Character c = null;

        if (!StringUtils.isEmpty(propertyValue))
        {
            c = propertyValue.charAt(0);
        }

        return c;
    }

    /**
     * {@inheritDoc}
     */
    public Class<Character> getBoundClass()
    {
        return Character.class;
    }
}