package spreedly.client.java;

import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;

import java.util.Map;

import spreedly.client.java.exception.SpreedlyClientException;
import spreedly.client.java.model.Fields;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.request.GatewayRequests;
import spreedly.client.java.request.PaymentMethodRequests;
import spreedly.client.java.request.TransactionRequests;

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
        return TransactionRequests.show(token, credentials);
    }

    public Transaction purchaseOnGateway(String gatewayToken, String paymentMethodToken, int amount, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(Fields.AMOUNT, String.valueOf(amount));
        return GatewayRequests.purchase(gatewayToken, options, credentials);
    }

    public Transaction verifyOnGateway(String gatewayToken, String paymentMethodToken, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        return GatewayRequests.verify(gatewayToken, options, credentials);
    }

}
