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

import be.objectify.led.util.ContractUtils;
import be.objectify.led.util.StringUtils;
import be.objectify.led.factory.EnumFactory;

import org.apache.log4j.Logger;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Steve Chaloner
 */
public class PropertySetter
{
    private static final Logger LOGGER = Logger.getLogger(PropertySetter.class);

    private final ObjectFactoryRegistry objectFactoryRegistry;

    private final PropertyContext propertyContext;

    private boolean automaticEnumAssignment = false;

    /**
     * Initialses a new instance with a default property context and object factory registry.
     */
    public PropertySetter()
    {
        this(new ObjectFactoryRegistry(),
             new DefaultPropertyContext());
    }

    /**
     * Initialses a new instance with the given property context and object factory registry.
     *
     * @param propertyContext the property context.  Must not be null
     */
    public PropertySetter(PropertyContext propertyContext)
    {
        this(new ObjectFactoryRegistry(),
             propertyContext);
    }

    /**
     * Initialses a new instance with the given object factory registry and a default property context.
     *
     * @param objectFactoryRegistry the object factory registry.  Must not be null
     */
    public PropertySetter(ObjectFactoryRegistry objectFactoryRegistry)
    {
        this(objectFactoryRegistry,
             new DefaultPropertyContext());
    }

    /**
     * Initialses a new instance with the given object factory registry and property context.
     *
     * @param objectFactoryRegistry the object factory registry.  Must not be null
     * @param propertyContext       the property context.  Must not be null
     */
    public PropertySetter(ObjectFactoryRegistry objectFactoryRegistry,
                          PropertyContext propertyContext)
    {
        ContractUtils.notNull(objectFactoryRegistry, "objectFactoryRegistry");
        ContractUtils.notNull(propertyContext, "propertyContext");

        this.objectFactoryRegistry = objectFactoryRegistry;
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
            ObjectFactory objectFactory = objectFactoryRegistry.getFactory(type);
            if (objectFactory != null)
            {
                setValue(target,
                         field,
                         objectFactory.createObject(propertyValue));
            }
            else if (automaticEnumAssignment && Enum.class.isAssignableFrom(type))
            {
                Class<? extends Enum> enumType = (Class<? extends Enum>)field.getType();
                ObjectFactory factory = new EnumFactory(enumType);
                setValue(target,
                         field,
                         factory.createObject(propertyValue));
            }
            else
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

    public boolean isAutomaticEnumAssignment()
    {
        return automaticEnumAssignment;
    }

    public void setAutomaticEnumAssignment(boolean automaticEnumAssignment)
    {
        this.automaticEnumAssignment = automaticEnumAssignment;
    }
}
