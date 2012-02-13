package be.objectify.led.inheritance.grandchild;

import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class ParentClassWithoutAnnotation
{
    @Property("parent-static-property")
    public static String parentStaticProperty;

    @Property("parent-instance-property")
    public String parentInstanceProperty;
}
