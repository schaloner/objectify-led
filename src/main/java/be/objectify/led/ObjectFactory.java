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

/**
 * Object factories are used to convert string property values into objects of an specific type.
 *
 * @author Steve Chaloner
 */
public interface ObjectFactory<T>
{
    /**
     * Creates an object based on propertyValue.  If propertyValue is null
     * or no object can be based on it, null should be returned.
     *
     * @param propertyValue the value of the property
     * @return an object (probably) based on propertyValue, or null
     */
    T createObject(String propertyValue);

    /**
     * Gets the class type this factory produces objects for.
     *
     * @return the class type
     */
    Class<T> getBoundClass();
}
