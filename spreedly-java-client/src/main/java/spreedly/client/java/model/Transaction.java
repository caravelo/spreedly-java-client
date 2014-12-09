package spreedly.client.java.model;

import java.util.Date;

public class Transaction extends Base {

    protected final String transactionType;

    protected Transaction(String transactionType, String token, Date createdAt, String updatedAt)
    {
        super(token, createdAt, updatedAt);
        this.transactionType = transactionType;
    }
}
