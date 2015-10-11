package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ATTRIBUTE;
import static spreedly.client.java.model.Fields.KEY;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Error extends Message
{

    public static final Error EMPTY = new Error("", "", "");

    @Attribute(name = ATTRIBUTE, required = false)
    private final String attribute;

    public Error(
            @Attribute(name = ATTRIBUTE) String attribute,
            @Attribute(name = KEY) String key,
            @Text String message)
    {
        super(key, message);

        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public String getAttribute()
    {
        return attribute;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "Error [" + super.toString()
                + "attribute=" + attribute + "]";
    }

}
