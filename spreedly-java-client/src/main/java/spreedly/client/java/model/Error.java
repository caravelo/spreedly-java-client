package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ATTRIBUTE;
import static spreedly.client.java.model.Fields.KEY;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Error
{

    @Attribute(name = ATTRIBUTE, required = false)
    private final String attribute;

    @Attribute(name = KEY)
    private final String key;

    @Text
    private final String message;

    public Error(
            @Attribute(name = ATTRIBUTE) String attribute,
            @Attribute(name = KEY) String key,
            @Text String message)
    {
        this.attribute = attribute;
        this.key = key;
        this.message = message;
    }

    public String getAttribute()
    {
        return attribute;
    }

    public String getKey()
    {
        return key;
    }

    public String getMessage()
    {
        return message;
    }

}
