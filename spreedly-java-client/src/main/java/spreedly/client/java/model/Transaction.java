package spreedly.client.java.model;

import java.util.Date;

import org.simpleframework.xml.Element;

public class Transaction extends Base
{

    protected static final String TRANSACTION_TYPE = "transaction_type";
    protected static final String SUCCEEDED = "succeeded";
    protected static final String MESSAGE = "message";

    @Element(name = TRANSACTION_TYPE)
    protected final String transactionType;

    @Element(name = SUCCEEDED)
    protected final boolean succeeded;

    @Element(name = MESSAGE)
    protected final String message;

    public Transaction(
            @Element(name = TOKEN) String token,
            @Element(name = CREATED_AT) Date createdAt,
            @Element(name = UPDATED_AT) String updatedAt,
            @Element(name = TRANSACTION_TYPE) String transactionType,
            @Element(name = SUCCEEDED) boolean succeeded,
            @Element(name = MESSAGE) String message)
    {
        super(token, createdAt, updatedAt);
        this.transactionType = transactionType;
        this.succeeded = succeeded;
        this.message = message;
    }

}
