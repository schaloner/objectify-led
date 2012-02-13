package be.objectify.led.inheritance.parent;

import be.objectify.led.InheritProperties;
import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@InheritProperties
public class ParentClassWithAnnotation
{
    @Property("parent-static-property")
    public static String parentStaticProperty;

    @Property("parent-instance-property")
    public String parentInstanceProperty;
}
