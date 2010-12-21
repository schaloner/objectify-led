package be.objectify.led;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public class PropertySetterConfigurationTest
{
    @Test
    public void testAllowFinalSetting_defaultValue()
    {
        PropertySetterConfiguration configuration = new PropertySetterConfiguration();
        Assert.assertFalse(configuration.isAllowFinalSetting());
    }

    @Test
    public void testAllowFinalSetting()
    {
        PropertySetterConfiguration configuration = new PropertySetterConfiguration();
        Assert.assertFalse(configuration.isAllowFinalSetting());

        configuration.setAllowFinalSetting(true);
        Assert.assertTrue(configuration.isAllowFinalSetting());

        configuration.setAllowFinalSetting(false);
        Assert.assertFalse(configuration.isAllowFinalSetting());
    }
}
