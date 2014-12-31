package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.CREATED_AT;
import static spreedly.client.java.model.Fields.TOKEN;
import static spreedly.client.java.model.Fields.UPDATED_AT;

import java.util.Date;

import org.simpleframework.xml.Element;

public class Base
{

    @Element(name = TOKEN)
    protected final String token;

    @Element(name = CREATED_AT)
    protected final Date createdAt;

    @Element(name = UPDATED_AT)
    protected final String updatedAt;

    protected Base(
            @Element(name = TOKEN) String token,
            @Element(name = UPDATED_AT) Date createdAt,
            @Element(name = UPDATED_AT)String updatedAt)
    {
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getToken()
    {
        return token;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

}
