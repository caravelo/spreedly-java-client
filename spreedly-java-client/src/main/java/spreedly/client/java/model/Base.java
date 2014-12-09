package spreedly.client.java.model;

import java.util.Date;

public class Base {

    protected final String token;
    protected final Date createdAt;
    protected final String updatedAt;

    protected Base(String token, Date createdAt, String updatedAt)
    {
    	this.token = token;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
}
