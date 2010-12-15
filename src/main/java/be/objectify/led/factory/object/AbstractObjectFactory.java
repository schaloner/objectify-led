/*******************************************************************************
 * Copyright (c) 2010 Toyota Manufacturing Europe. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 ******************************************************************************/
package be.objectify.led.factory.object;

import be.objectify.led.ObjectFactory;
import be.objectify.led.ValidationException;
import be.objectify.led.factory.ValidationFunction;

/**
 * @author Steve Chaloner
 */
public abstract class AbstractObjectFactory<T> implements ObjectFactory<T>
{
    /**
     * Default implementation of validate() does nothing.
     *
     * @param propertyName the name of the property
     * @param propertyValue the value from the @{link PropertyContext}
     * @param validationFunctions functions to validate the property value
     * @throws ValidationException if the property value isn't valid
     */
    public void validate(String propertyName,
                         String propertyValue,
                         ValidationFunction... validationFunctions) throws ValidationException
    {
        if (validationFunctions != null)
        {
            for (ValidationFunction validationFunction : validationFunctions)
            {
                if (validationFunction != null)
                {
                    validationFunction.validate(propertyName,
                                                propertyValue);
                }
            }
        }
    }
}
