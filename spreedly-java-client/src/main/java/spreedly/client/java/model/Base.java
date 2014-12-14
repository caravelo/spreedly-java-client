package spreedly.client.java.model;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction")
public class Base
{

    protected static final String TOKEN = "token";
    protected static final String CREATED_AT = "created_at";
    protected static final String UPDATED_AT = "updated_at";

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

}
