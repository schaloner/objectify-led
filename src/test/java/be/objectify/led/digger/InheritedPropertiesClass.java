package be.objectify.led.digger;

import be.objectify.led.InheritProperties;
import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@InheritProperties
public class InheritedPropertiesClass extends BaseClass
{
    @Property("d")
    public String valueD;
}
