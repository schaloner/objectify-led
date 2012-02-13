package be.objectify.led.inheritance.parent;

import be.objectify.led.DefaultPropertyContext;
import be.objectify.led.PropertyContext;
import be.objectify.led.PropertySetter;
import be.objectify.led.inheritance.child.GrandchildClassWithoutAnnotation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class ParentInheritanceTest
{
    @Test
    public void testProperties()
    {
        Properties props = new Properties();
        props.setProperty("parent-static-property", "parentStaticProperty");
        props.setProperty("parent-instance-property", "parentChildProperty");
        props.setProperty("child-static-property", "childStaticProperty");
        props.setProperty("child-instance-property", "childInstanceProperty");
        props.setProperty("grandchild-static-property", "grandchildStaticProperty");
        props.setProperty("grandchild-instance-property", "grandchildStaticProperty");

        PropertyContext propertyContext = new DefaultPropertyContext(props);
        PropertySetter propertySetter = new PropertySetter(propertyContext);

        GrandchildClassWithoutAnnotation target = new GrandchildClassWithoutAnnotation();
        propertySetter.process(target);

        Assert.assertNotNull(GrandchildClassWithoutAnnotation.parentStaticProperty);
        Assert.assertNotNull(GrandchildClassWithoutAnnotation.childStaticProperty);
        Assert.assertNotNull(GrandchildClassWithoutAnnotation.grandchildStaticProperty);
        Assert.assertEquals(GrandchildClassWithoutAnnotation.parentStaticProperty,
                            "parentStaticProperty");
        Assert.assertEquals(GrandchildClassWithoutAnnotation.childStaticProperty,
                            "childStaticProperty");
        Assert.assertEquals(GrandchildClassWithoutAnnotation.grandchildStaticProperty,
                            "grandchildStaticProperty");


        Assert.assertNotNull(target.parentInstanceProperty);
        Assert.assertNotNull(target.childInstanceProperty);
        Assert.assertNotNull(target.grandchildInstanceProperty);
        Assert.assertEquals(target.parentInstanceProperty,
                            "parentChildProperty");
        Assert.assertEquals(target.childInstanceProperty,
                            "childInstanceProperty");
        Assert.assertEquals(target.grandchildInstanceProperty,
                            "grandchildStaticProperty");
    }
}
