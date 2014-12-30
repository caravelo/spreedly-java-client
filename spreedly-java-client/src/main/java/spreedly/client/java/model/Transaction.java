package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.CREATED_AT;
import static spreedly.client.java.model.Fields.MESSAGE;
import static spreedly.client.java.model.Fields.SUCCEEDED;
import static spreedly.client.java.model.Fields.TOKEN;
import static spreedly.client.java.model.Fields.TRANSACTION_TYPE;
import static spreedly.client.java.model.Fields.UPDATED_AT;

import java.util.Date;

import org.simpleframework.xml.Element;

public class Transaction extends Base
{
    // XXX: generate getters

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
