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
package be.objectify.led.validation;

/**
 * Indicates validation has failed.
 *
 * @author Steve Chaloner
 */
public class ValidationException extends RuntimeException
{
    private String fieldName;

    private String reason;

    /**
     * Initialises a new instance with the target field name and reason for the validation failure.
     *
     * @param fieldName the field name
     * @param reason the reason
     */
    public ValidationException(String fieldName,
                               String reason)
    {
        super(String.format("Validation failed on field [%s] because [%s]",
                            fieldName,
                            reason));
        this.fieldName = fieldName;
        this.reason = reason;
    }

    /**
     * Initialises a new instance with the target field name, reason for the validation failure and
     * a root cause.
     *
     * @param fieldName the field name
     * @param reason the reason
     * @param cause an associated exception
     */
    public ValidationException(String fieldName,
                               String reason,
                               Throwable cause)
    {
        super(String.format("Validation failed on field [%s] because [%s]",
                            fieldName,
                            reason),
              cause);
        this.fieldName = fieldName;
        this.reason = reason;
    }


    public String getFieldName()
    {
        return fieldName;
    }

    public String getReason()
    {
        return reason;
    }
}
