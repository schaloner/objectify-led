/*******************************************************************************
 * Copyright (c) 2010 Toyota Manufacturing Europe. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 ******************************************************************************/
package be.objectify.led;

/**
 * @author Steve Chaloner
 */
public class ValidationException extends RuntimeException
{
    public ValidationException(String fieldName,
                               String reason)
    {
        super(String.format("Validation failed on field [%s] because [%s]",
                            fieldName,
                            reason));
    }

    public ValidationException(String fieldName,
                               String reason,
                               Throwable cause)
    {
        super(String.format("Validation failed on field [%s] because [%s]",
                            fieldName,
                            reason),
              cause);
    }
}
