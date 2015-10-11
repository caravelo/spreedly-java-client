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
    protected final Date updatedAt;

    protected Base(
            @Element(name = TOKEN) String token,
            @Element(name = UPDATED_AT) Date createdAt,
            @Element(name = UPDATED_AT) Date updatedAt)
    {
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Base other = (Base) obj;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (token == null) {
            if (other.token != null) return false;
        } else if (!token.equals(other.token)) return false;
        if (updatedAt == null) {
            if (other.updatedAt != null) return false;
        } else if (!updatedAt.equals(other.updatedAt)) return false;
        return true;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getToken()
    {
        return token;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Base [token=" + token + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
