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
package be.objectify.led.util;

import java.util.Collection;

/**
 * Utility methods for asserting contracts.
 *
 * @author Steve Chaloner
 */
public class ContractUtils
{
    private ContractUtils()
    {
    }

    /**
     * Checks if <i>object</i> is null.  An IllegalArgumentException is thrown
     * if it is null.
     *
     * @param object the object
     * @param objectName   the name to reference the object by for exception messages
     */
    public static void notNull(Object object,
                               String objectName)
    {
        if (objectName == null)
        {
            throw new IllegalArgumentException("objectName must not be null");
        }
        if (object == null)
        {
            throw new IllegalArgumentException(objectName + " must not be null");
        }
    }

    /**
     * Checks if <i>objects</i> is null, and if any of its contents are null.  An
     * IllegalArgumentException is thrown if either of these cases are true.
     *
     * @param objects the array of objects
     * @param objectName  the name to reference the array by for exception messages
     */
    public static void nonNull(Object[] objects,
                               String objectName)
    {
        notNull(objects,
                     objectName);

        for (Object object : objects)
        {
            if (object == null)
            {
                throw new IllegalArgumentException("All items of " + objectName + " must be non-null");
            }
        }
    }

    /**
     * Checks if <i>objects</i> is null, and if any of its contents are null.  An
     * IllegalArgumentException is thrown if either of these cases are true.
     *
     * @param objects the collection of objects
     * @param objectName    the name to reference the collection by for exception messages
     */
    public static void nonNull(Collection objects,
                               String objectName)
    {
        notNull(objects,
                     objectName);

        for (Object object : objects)
        {
            if (object == null)
            {
                throw new IllegalArgumentException("All items of " + objectName + " must be non-null");
            }
        }
    }
}