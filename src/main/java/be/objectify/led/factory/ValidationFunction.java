/*******************************************************************************
 * Copyright (c) 2010 Toyota Manufacturing Europe. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 ******************************************************************************/
package be.objectify.led.factory;

import be.objectify.led.ValidationException;

/**
 * Functional objects are used to validate property values.
 *
 * @author Steve Chaloner
 */
public interface ValidationFunction
{
    /**
     * Validates the property value.
     *
     * @param propertyName the name of the property
     * @param propertyValue the value of the property
     * @throws ValidationException if the value isn't valid
     */
    void validate(String propertyName,
                  String propertyValue) throws ValidationException;
}
