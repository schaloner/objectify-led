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

import be.objectify.led.util.ContractUtils;
import be.objectify.led.util.StringUtils;
import be.objectify.led.validation.ValidationFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Steve Chaloner
 */
public class PropertySetter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertySetter.class);

    private final FactoryResolver factoryResolver;

    private final PropertyContext propertyContext;

    private final PropertySetterConfiguration configuration;

    /**
     * Initialises a new instance with a default {@link PropertyContext} and {@link FactoryResolver}.
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
     * Initialises a new instance with the given property context and a object factory registry, and a default type factory registry.
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
        this.configuration = new PropertySetterConfiguration();
    }

    /**
     * Processes any {@link Property} annotations in the given class.  This
     * will not affect any instance members.
     *
     * @param target the class to check
     * @param validationFunctions validation checks
     */
    public void process(Class target,
                        ValidationFunction... validationFunctions)
    {
        Field[] fields = target.getDeclaredFields();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class) &&
                Modifier.isStatic(field.getModifiers()))
            {
                setProperty(target,
                            field,
                            validationFunctions);
            }
        }
    }

    /**
     * Processes any {@link Property} annotations in the given object, including
     * static members.
     *
     * @param target the object to check
     * @param validationFunctions validation checks
     */
    public void process(Object target,
                        ValidationFunction... validationFunctions)
    {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Property.class))
            {
                setProperty(target,
                            field,
                            validationFunctions);
            }
        }
    }

    /**
     * Sets any system property defined for the field.
     *
     * @param target the instance to work on
     * @param field  the field to use to set the property
     * @param validationFunctions validation checks
     */
    private void setProperty(Object target,
                             Field field,
                             ValidationFunction... validationFunctions)
    {
        String propertyValue = getProperty(field);
        boolean finalField = Modifier.isFinal(field.getModifiers());
        if (!StringUtils.isEmpty(propertyValue) &&
            (!finalField || configuration.isAllowFinalSetting()))
        {
            Class<?> type = field.getType();
            ObjectFactory objectFactory = factoryResolver.resolveFactory(type,
                                                                         field);
            if (objectFactory != null)
            {
                String fieldName = field.getAnnotation(Property.class).value();
                objectFactory.validate(fieldName,
                                       propertyValue,
                                       validationFunctions);
                setValue(target,
                         field,
                         objectFactory.createObject(fieldName,
                                                    propertyValue));
            }
            else
            {
                LOGGER.info("No factory available for type [{}]",
                            type);
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

        if (!StringUtils.isEmpty(propertyValue))
        {
            LOGGER.debug("Found value [{}] for system property [{}] on [{}]",
                         new Object[] {
                                 propertyValue,
                                 propertyName,
                                 accessibleObject.toString()
                         });
        }

        return propertyValue;
    }

    public PropertySetterConfiguration getConfiguration()
    {
        return configuration;
    }
}
