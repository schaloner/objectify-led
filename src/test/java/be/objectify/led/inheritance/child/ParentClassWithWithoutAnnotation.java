package be.objectify.led.inheritance.child;

import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class ParentClassWithWithoutAnnotation
{
    @Property("parent-static-property")
    public static String parentStaticProperty;

    @Property("parent-instance-property")
    public String parentInstanceProperty;
}
