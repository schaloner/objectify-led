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
package be.objectify.led.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test cases for {@link ContractUtils}.
 *
 * @author Steve Chaloner
 */
public class ContractUtilsTest
{
    @Test(expected = IllegalArgumentException.class)
    public void testNotNull_nullValue()
    {
        ContractUtils.notNull(null, "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotNull_nullName()
    {
        ContractUtils.notNull("object", null);
    }

    @Test
    public void testNotNull_validValue()
    {
        ContractUtils.notNull("object", "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotNull_validObject_nullName()
    {
        ContractUtils.notNull("object", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullArray_nullValue()
    {
        ContractUtils.nonNull((Object[])null, "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullArray_nullName()
    {
        ContractUtils.nonNull(new String[]{"object"}, null);
    }

    @Test
    public void testNonNullArray_validValue()
    {
        ContractUtils.nonNull(new String[]{"object"}, "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullArray_validObject_nullName()
    {
        ContractUtils.nonNull(new String[]{"object"}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullCollection_nullValue()
    {
        ContractUtils.nonNull((List)null, "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullCollection_nullName()
    {
        ContractUtils.nonNull(Arrays.asList("object"), null);
    }

    @Test
    public void testNonNullCollection_validValue()
    {
        ContractUtils.nonNull(Arrays.asList("object"), "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonNullCollection_validObject_nullName()
    {
        ContractUtils.nonNull(Arrays.asList("object"), null);
    }
}
