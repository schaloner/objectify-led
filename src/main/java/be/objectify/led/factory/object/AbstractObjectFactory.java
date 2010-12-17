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

import be.objectify.led.ObjectFactory;
import be.objectify.led.ValidationException;
import be.objectify.led.factory.ValidationFunction;

/**
 * @author Steve Chaloner
 */
public abstract class AbstractObjectFactory<T> implements ObjectFactory<T>
{
    /**
     * Default implementation of validate() does nothing.
     *
     * @param propertyName the name of the property
     * @param propertyValue the value from the @{link PropertyContext}
     * @param validationFunctions functions to validate the property value
     * @throws ValidationException if the property value isn't valid
     */
    public void validate(String propertyName,
                         String propertyValue,
                         ValidationFunction... validationFunctions) throws ValidationException
    {
        if (validationFunctions != null)
        {
            for (ValidationFunction validationFunction : validationFunctions)
            {
                if (validationFunction != null)
                {
                    validationFunction.validate(propertyName,
                                                propertyValue);
                }
            }
        }
    }
}
