package be.objectify.led.inheritance.child;

import be.objectify.led.InheritProperties;
import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@InheritProperties
public class ChildClassWithAnnotation extends ParentClassWithWithoutAnnotation
{
    @Property("child-static-property")
    public static String childStaticProperty;

    @Property("child-instance-property")
    public String childInstanceProperty;
}
