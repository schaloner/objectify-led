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
package be.objectify.led.validation;

/**
 * Functional objects are used to validate property values.
 *
 * @author Steve Chaloner
 */
public interface ValidationFunction
{
    /**
     * Validates the property value.
     *
     * @param propertyName the name of the property
     * @param propertyValue the value of the property
     * @throws ValidationException if the value isn't valid
     */
    void validate(String propertyName,
                  String propertyValue) throws ValidationException;
}
