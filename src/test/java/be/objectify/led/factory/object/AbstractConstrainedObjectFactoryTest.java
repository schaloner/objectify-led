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
package be.objectify.led.factory.object;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for constrained object type, i.e. those that can only be derived from certain values such as numbers.
 *
 * @author Steve Chaloner
 */
public abstract class AbstractConstrainedObjectFactoryTest extends AbstractObjectFactoryTest
{
    @Test
    public void testDefaultFactory_invalidPropertyValue()
    {
        Object result = factoryResolver.resolveFactory(getTargetClass(), null).createObject(getInvalidPropertyValue());
        Assert.assertNull(result);
    }

    protected abstract String getInvalidPropertyValue();
}
