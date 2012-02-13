package be.objectify.led.inheritance.grandchild;

import be.objectify.led.Property;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class ChildClassWithoutAnnotation extends ParentClassWithoutAnnotation
{
    @Property("child-static-property")
    public static String childStaticProperty;

    @Property("child-instance-property")
    public String childInstanceProperty;
}
