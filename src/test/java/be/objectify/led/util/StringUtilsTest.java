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

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link StringUtils}.
 *
 * @author Steve Chaloner
 */
public class StringUtilsTest
{
    @Test
    public void testIsEmpty_nullString()
    {
        Assert.assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void testIsEmpty_zeroLengthString()
    {
        Assert.assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_emptyString()
    {
        Assert.assertTrue(StringUtils.isEmpty("   "));
    }

    @Test
    public void testIsEmpty_tab()
    {
        Assert.assertTrue(StringUtils.isEmpty("\t"));
    }

    @Test
    public void testIsEmpty_lineFeed()
    {
        Assert.assertTrue(StringUtils.isEmpty("\n"));
    }

    @Test
    public void testIsEmpty_carriageReturn()
    {
        Assert.assertTrue(StringUtils.isEmpty("\r"));
    }

    @Test
    public void testIsEmpty_variousWhitespaceCharacters()
    {
        Assert.assertTrue(StringUtils.isEmpty("   \r     \n      \t"));
    }

    @Test
    public void testIsEmpty_populatedString()
    {
        Assert.assertFalse(StringUtils.isEmpty("content"));
    }
}
