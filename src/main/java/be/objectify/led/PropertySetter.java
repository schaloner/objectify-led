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

import org.apache.log4j.Logger;

import be.objectify.led.util.ContractUtils;
import be.objectify.led.util.StringUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Steve Chaloner
 */
public class PropertySetter
{
    private static final Logger LOGGER = Logger.getLogger(PropertySetter.class);

    private final FactoryResolver factoryResolver;

    private final PropertyContext propertyContext;

    /**
     * Initialses a new instance with a default {@link PropertyContext} and {@link FactoryResolver}.
     */
    public PropertySetter()
    {
        this(new DefaultFactoryResolver(),
             new DefaultPropertyContext());
    }

    /**
     * Initialses a new instance with the given {@link PropertyContext} and default {@link FactoryResolver}.
     *
     * @param propertyContext the property context.  Must not be null
     */
    public PropertySetter(PropertyContext propertyContext)
    {
        this(new DefaultFactoryResolver(),
             propertyContext);
    }

    /**
     * Initialses a new instance with the given property context and a object factory registry, and a default type factory registry.
     *
     * @param factoryResolver the object factory resolver.  Must not be null
     * @param propertyContext the property context.  Must not be null
     */
    public PropertySetter(FactoryResolver factoryResolver,
                          PropertyContext propertyContext)
    {
        ContractUtils.notNull(factoryResolver, "factoryResolver");
        ContractUtils.notNull(propertyContext, "propertyContext");

        this.factoryResolver = factoryResolver;
        this.propertyContext = propertyContext;
    }

    /**
     * Processes any {@link Property} annotations in the given class.  This
     * will not affect any instance members.
     *
     * @param target the class to check
     */
    public void process(Class target)
    {
        Field[] fields = target.getDeclaredFields();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class) &&
                Modifier.isStatic(field.getModifiers()))
            {
                setProperty(target,
                            field);
            }
        }
    }

    /**
     * Processes any {@link Property} annotations in the given object, including
     * static members.
     *
     * @param target the object to check
     */
    public void process(Object target)
    {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class))
            {
                setProperty(target,
                            field);
            }
        }
    }

    /**
     * Sets any system property defined for the field.
     *
     * @param target the instance to work on
     * @param field  the field to use to set the property
     */
    private void setProperty(Object target,
                             Field field)
    {
        String propertyValue = getProperty(field);
        if (!StringUtils.isEmpty(propertyValue) &&
            !Modifier.isFinal(field.getModifiers()))
        {
            Class<?> type = field.getType();
            ObjectFactory objectFactory = factoryResolver.resolveFactory(type,
                                                                         field);
            if (objectFactory != null)
            {
                setValue(target,
                         field,
                         objectFactory.createObject(propertyValue));
            }
            else if (LOGGER.isInfoEnabled())
            {
                LOGGER.info(String.format("No factory available for type [%s]",
                                          type));
            }
        }
    }

    /**
     * Sets the value on the target.
     *
     * @param target the target object/class
     * @param field  the field to set
     * @param value  the value to set
     */
    private void setValue(Object target,
                          Field field,
                          Object value)
    {
        if (value != null)
        {
            try
            {
                boolean accessibleState = field.isAccessible();
                if (!accessibleState)
                {
                    field.setAccessible(true);
                }
                field.set(target,
                          value);
                field.setAccessible(accessibleState);
            }
            catch (IllegalAccessException e)
            {
                LOGGER.error("Unable to set property",
                             e);
            }
        }
    }

    private String getProperty(AccessibleObject accessibleObject)
    {
        Property annotation = accessibleObject.getAnnotation(Property.class);
        String propertyName = annotation.value();
        String propertyValue = propertyContext.getValue(propertyName);

        if (LOGGER.isDebugEnabled() && !StringUtils.isEmpty(propertyValue))
        {
            LOGGER.debug(String.format("Found value [%s] for system property [%s] on [%s]",
                                       propertyValue,
                                       propertyName,
                                       accessibleObject.toString()));
        }

        return propertyValue;
    }
}
