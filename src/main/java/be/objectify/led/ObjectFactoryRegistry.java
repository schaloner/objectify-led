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

import be.objectify.led.factory.object.BooleanFactory;
import be.objectify.led.factory.object.ByteFactory;
import be.objectify.led.factory.object.CharacterFactory;
import be.objectify.led.factory.object.DoubleFactory;
import be.objectify.led.factory.object.FloatFactory;
import be.objectify.led.factory.object.IntegerFactory;
import be.objectify.led.factory.object.LongFactory;
import be.objectify.led.factory.object.ShortFactory;
import be.objectify.led.factory.object.StringFactory;
import be.objectify.led.util.ContractUtils;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry of object factories for creating objects of the correct type based on the field.
 *
 * @author Steve Chaloner
 */
public class ObjectFactoryRegistry
{
    private static final Logger LOGGER = Logger.getLogger(ObjectFactoryRegistry.class);

    private final Map<Class, ObjectFactory> objectFactories = new HashMap<Class, ObjectFactory>();

    private static final Map<Class, Class> PRIMITIVE_TO_WRAPPER_MAP = new HashMap<Class, Class>();

    static
    {
        PRIMITIVE_TO_WRAPPER_MAP.put(boolean.class,
                                     Boolean.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(byte.class,
                                     Byte.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(char.class,
                                     Character.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(double.class,
                                     Double.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(float.class,
                                     Float.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(int.class,
                                     Integer.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(long.class,
                                     Long.class);
        PRIMITIVE_TO_WRAPPER_MAP.put(short.class,
                                     Short.class);
    }

    /**
     * Initialises a new instance of this class by registering the default object factories.
     */
    public ObjectFactoryRegistry()
    {
        ObjectFactory<Boolean> booleanFactory = new BooleanFactory();
        objectFactories.put(booleanFactory.getBoundClass(),
                            booleanFactory);
        ObjectFactory<Byte> byteFactory = new ByteFactory();
        objectFactories.put(byteFactory.getBoundClass(),
                            byteFactory);
        ObjectFactory<Character> characterFactory = new CharacterFactory();
        objectFactories.put(characterFactory.getBoundClass(),
                            characterFactory);
        ObjectFactory<Double> doubleFactory = new DoubleFactory();
        objectFactories.put(doubleFactory.getBoundClass(),
                            doubleFactory);
        ObjectFactory<Float> floatFactory = new FloatFactory();
        objectFactories.put(floatFactory.getBoundClass(),
                            floatFactory);
        ObjectFactory<Integer> integerFactory = new IntegerFactory();
        objectFactories.put(integerFactory.getBoundClass(),
                            integerFactory);
        ObjectFactory<Long> longFactory = new LongFactory();
        objectFactories.put(longFactory.getBoundClass(),
                            longFactory);
        ObjectFactory<Short> shortFactory = new ShortFactory();
        objectFactories.put(shortFactory.getBoundClass(),
                            shortFactory);
        ObjectFactory<String> stringFactory = new StringFactory();
        objectFactories.put(stringFactory.getBoundClass(),
                            stringFactory);
    }

    /**
     * Registers the object factories with the registry.
     *
     * @param objectFactories the object factories.  None of the factories can be null.
     */
    public void register(ObjectFactory... objectFactories)
    {
        ContractUtils.nonNull(objectFactories,
                                   "objectFactories");

        register(Arrays.asList(objectFactories));
    }

    /**
     * Registers the object factories with the registry.
     *
     * @param objectFactories the object factories to register.  Must not be null; none of contents
     */
    public void register(List<ObjectFactory> objectFactories)
    {
        ContractUtils.nonNull(objectFactories,
                                   "objectFactories");

        for (ObjectFactory objectFactory : objectFactories)
        {
            this.objectFactories.put(objectFactory.getBoundClass(),
                                     objectFactory);
        }
    }

    /**
     * Gets the object factory for the given class.
     *
     * @param clazz the class of the required object
     * @return the object factory, or null if no factory is registered for the class
     */
    public ObjectFactory getFactory(Class clazz)
    {
        if (clazz.isPrimitive())
        {
            clazz = PRIMITIVE_TO_WRAPPER_MAP.get(clazz);
        }

        ObjectFactory factory = objectFactories.get(clazz);

        if (LOGGER.isDebugEnabled())
        {
            if (factory != null)
            {
                LOGGER.debug(String.format("Found object factory [%s] for class [%s]",
                                           factory,
                                           clazz.getCanonicalName()));
            }
            else
            {
                LOGGER.debug("Could not find object factory for " + clazz.getCanonicalName());
            }
        }

        return factory;
    }
}
