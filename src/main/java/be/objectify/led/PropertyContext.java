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
package be.objectify.led;

/**
 * Property contexts are the access points to property sources, e.g. System, property files,
 * or any arbitrary source.
 *
 * @author Steve Chaloner
 */
public interface PropertyContext
{
    /**
     * Gets the named propety from the context.
     *
     * @param propertyName the name of the property
     * @return the value mapped to the property name, or null if no value exists
     */
    String getValue(String propertyName);
}
