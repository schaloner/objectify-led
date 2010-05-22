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

import java.lang.reflect.Field;

/**
 * Resolves {@link ObjectFactory} instances through checking for an {@link ObjectFactory} for the required class,
 * followed by a {@link TypeFactory} if no object factory is defined.
 *
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class DefaultFactoryResolver implements FactoryResolver
{
    private final ObjectFactoryRegistry objectFactoryRegistry;

    private final TypeFactoryRegistry typeFactoryRegistry;

    public DefaultFactoryResolver()
    {
        this(new ObjectFactoryRegistry(),
             new TypeFactoryRegistry());
    }

    public DefaultFactoryResolver(ObjectFactoryRegistry objectFactoryRegistry)
    {
        this(objectFactoryRegistry,
             new TypeFactoryRegistry());
    }

    public DefaultFactoryResolver(TypeFactoryRegistry typeFactoryRegistry)
    {
        this(new ObjectFactoryRegistry(),
             typeFactoryRegistry);
    }

    public DefaultFactoryResolver(ObjectFactoryRegistry objectFactoryRegistry,
                                  TypeFactoryRegistry typeFactoryRegistry)
    {
        this.objectFactoryRegistry = objectFactoryRegistry;
        this.typeFactoryRegistry = typeFactoryRegistry;
    }

    public ObjectFactory resolveFactory(Class<?> type,
                                        Field field)
    {
        ObjectFactory objectFactory = objectFactoryRegistry.getFactory(type);
        if (objectFactory == null)
        {
            GenericTypes genericTypes = field.getAnnotation(GenericTypes.class);
            TypeFactory factory = typeFactoryRegistry.getFactory(type);
            if (factory != null)
            {
                objectFactory = factory.createObjectFactory(factory.determineClassType(type,
                                                                                       genericTypes),
                                                            field);
            }
        }

        return objectFactory;
    }
}
