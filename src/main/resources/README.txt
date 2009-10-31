** objectify-led 1.0 **

objectify-led is a small Java library for loading system and arbitrary values into objects at runtime.

Instead of having chunks of code such as

-----
public class Foo
{
    private static String BLAH = "default-value";

    private String myString;

    private int myInt = -1;

    public static void main(String[] args)
    {
        if (System.getProperty("blah.value) != null)
        {
            BLAH = System.getProperty("blah.value");
        }

        Foo foo = new Foo();
        if (System.getProperty("mystring.value) != null)
        {
            foo.myString = System.getProperty("mystring.value");
        }
        if (System.getProperty("myint.value) != null)
        {
            try
            {
                 String intValue = System.getProperty("myint.value");
                 foo.myInt = Integer.parseInt(intValue;
            }
            catch (NumberFormatException e)
            {
                ...
            }
        }
    }

    ...
}
-----

you can instead use objectify-led to bind the properties for you :

-----
public class Foo
{
    @Property("blah.value");
    private static String BLAH = "default-value";

    @Property("mystring.value");
    private String myString;

    @Property("myint.value");
    private int myInt = -1;

    public static void main(String[] args)
    {
        Foo foo = new Foo();
        new PropertySetter().process(foo);
    }

    ...
}
-----

Properties are obtained from the System, from any Properties objects you have provided, or
from any arbitrary source if you implement your own property context;

Objects are created from the property value using a pluggable factory, so it's possible
to have any type of object created based on the target field type.  By default, Strings and
all primitives/wrappers are supported.


Where can I get it?
-------------------

The latest version of objectify-led can be found at

    http://www.objectify.be


Requirements
------------

objectify-led uses log4j, which you will need to supply yourself.  Log4j can be downloaded from
http://logging.apache.org/log4j

Further questions
-----------------

You can contact the author at http://www.objectify.be or at openNOSPAMsource@objectify.be
if you have further questions or suggestions.
