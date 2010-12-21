package be.objectify.led;

/**
 * Controls behaviour of the property setter.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
public class PropertySetterConfiguration
{
    private boolean allowFinalSetting = false;

    public boolean isAllowFinalSetting()
    {
        return allowFinalSetting;
    }

    public void setAllowFinalSetting(boolean allowFinalSetting)
    {
        this.allowFinalSetting = allowFinalSetting;
    }
}
