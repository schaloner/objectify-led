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

/**
 * Test cases for the default Long object factory.
 *
 * @author Steve Chaloner
 */
public class LongFactoryTest extends AbstractConstrainedObjectFactoryTest
{
    protected Class getTargetClass()
    {
        return Long.class;
    }

    protected String getInvalidPropertyValue()
    {
        return "foo";
    }

    protected String getPropertyValue()
    {
        return "9223372036854775807";
    }

    protected Object getResult()
    {
        return 9223372036854775807L;
    }
}