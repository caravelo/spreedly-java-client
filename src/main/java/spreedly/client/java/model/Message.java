package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.KEY;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Message
{

    @Attribute(name = KEY, required = false)
    protected final String key;

    @Text(required = false)
    protected final String message;

    // Required to support empty <message nil="true" /> elements
    public Message(){ this(null, null); }

    public Message(
            @Attribute(name = KEY) String key,
            @Text String message)
    {
        this.key = key;
        this.message = message;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Message other = (Message) obj;
        if (key == null) {
            if (other.key != null) return false;
        } else if (!key.equals(other.key)) return false;
        if (message == null) {
            if (other.message != null) return false;
        } else if (!message.equals(other.message)) return false;
        return true;
    }

    public String getKey()
    {
        return key;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Message [key=" + key + ", message=" + message + "]";
    }

}
