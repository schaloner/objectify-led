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
package be.objectify.led.factory.type;

import be.objectify.led.PropertyContext;
import be.objectify.led.factory.object.EnumFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class EnumTypeFactoryTest
{
    public enum EnumTypeFactoryTestEnum
    {
        A,
        B,
        C
    }

    @Test
    public void testEnumClassification()
    {
        EnumTypeFactory factory = new EnumTypeFactory();
        EnumFactory enumFactory = factory.createObjectFactory(new Class[]{ EnumTypeFactoryTestEnum.class },
                                                              null);
        Assert.assertNotNull(enumFactory);
        Enum e = enumFactory.createObject("propertyName",
                                          "A",
                                          new PropertyContext()
                                          {
                                              public String getValue(String propertyName)
                                              {
                                                  return null;
                                              }
                                          });
        Assert.assertNotNull(e);
        Assert.assertEquals(EnumTypeFactoryTestEnum.A,
                            e);
    }
}
