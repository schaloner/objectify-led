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

import java.lang.reflect.Field;

/**
 * Implementors create {@link ObjectFactory} instances that require runtime information.
 * 
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public interface TypeFactory<T>
{
    /**
     * Creates a new instance of the target class with the correct runtime types.
     *
     * @param classes the generic parameter types
     * @param field the target field
     * @return a new instance of the target class
     */
    ObjectFactory<T> createObjectFactory(Class[] classes,
                                         Field field);

    /**
     * Get the class type handled by this factory.
     *
     * @return the class
     */
    Class<T> getBoundClass();

    /**
     * Determines the runtime generic parameter of the target class.
     *
     * @param fieldType the class type of the field
     * @param genericTypes the generic types declared for the field
     * @return the runtime generic values
     */
    Class[] determineClassType(Class fieldType,
                               GenericTypes genericTypes);
}
