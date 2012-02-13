package be.objectify.led.inheritance.child;

import be.objectify.led.InheritProperties;
import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class GrandchildClassWithoutAnnotation extends ChildClassWithAnnotation
{
    @Property("grandchild-static-property")
    public static String grandchildStaticProperty;

    @Property("grandchild-instance-property")
    public String grandchildInstanceProperty;
}
