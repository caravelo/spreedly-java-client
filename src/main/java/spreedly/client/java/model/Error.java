package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ATTRIBUTE;
import static spreedly.client.java.model.Fields.KEY;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Error extends Message
{

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

    public String getAttribute()
    {
        return attribute;
    }

    @Override
    public String toString()
    {
        return "Error [" + super.toString()
                + "attribute=" + attribute + "]";
    }

}
