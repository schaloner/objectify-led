package be.objectify.led.inheritance.grandchild;

import be.objectify.led.InheritProperties;
import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@InheritProperties
public class GrandchildClassWithAnnotation extends ChildClassWithoutAnnotation
{
    @Property("grandchild-static-property")
    public static String grandchildStaticProperty;

    @Property("grandchild-instance-property")
    public String grandchildInstanceProperty;
}
