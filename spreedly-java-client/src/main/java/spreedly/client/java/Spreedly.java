package spreedly.client.java;

import spreedly.client.java.exception.SpreedlyClientException;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.request.PaymentMethodRequests;
import spreedly.client.java.request.TransactionRequest;

public class Spreedly
{

    public static Spreedly newEnvironment(String environmentKey, String accessSecret)
    {
        return new Spreedly(environmentKey, accessSecret);
    }

    private final Credentials credentials;

    private Spreedly(String environmentKey, String accessSecret)
    {
        this.credentials = new Credentials(environmentKey, accessSecret);
    }

    public PaymentMethod findPaymentMethod(String token) throws SpreedlyClientException
    {
        return PaymentMethodRequests.show(token, credentials);
    }

    public Transaction findTransaction(String token) throws SpreedlyClientException
    {
        return TransactionRequest.show(token, credentials);
    }

}
