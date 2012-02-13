package be.objectify.led.inheritance.parent;

import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class GrandchildClassWithoutAnnotation extends ChildClassWithoutAnnotation
{
    @Property("grandchild-static-property")
    public static String grandchildStaticProperty;

    @Property("grandchild-instance-property")
    public String grandchildInstanceProperty;
}
