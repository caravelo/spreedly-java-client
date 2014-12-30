package spreedly.client.java.request;

import static org.junit.Assert.assertNotNull;
import static spreedly.client.java.model.Fields.CURRENCY_CODE;
import static spreedly.client.java.model.Fields.DESCRIPTION;
import static spreedly.client.java.model.Fields.EMAIL;
import static spreedly.client.java.model.Fields.IP;
import static spreedly.client.java.model.Fields.MERCHANT_LOCATION_DESCRIPTOR;
import static spreedly.client.java.model.Fields.MERCHANT_NAME_DESCRIPTOR;
import static spreedly.client.java.model.Fields.ORDER_ID;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.Fields.RETAIN_ON_SUCCESS;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

import spreedly.client.java.Credentials;
import spreedly.client.java.model.Transaction;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class GatewayRequestsTest
{

    private static final Credentials AUTH = 
            new Credentials("test", "test");

    @Rule public Recorder recorder = new Recorder();

    @Betamax(tape = "verify-payment-method")
    @Test
    public void testVerifyAndRetain() throws Exception
    {
        // Given
        String gatewayToken = "XKqtfVWFvZgbwmrN5ZFdMZpB1XN";

        Map<String, String> options = new HashMap<String, String>();
        options.put(PAYMENT_METHOD_TOKEN, "EL8G2Njs9LdUDKlNSbonADvcxJV");
        options.put(RETAIN_ON_SUCCESS, "true");
        options.put(CURRENCY_CODE, "EUR");
        options.put(ORDER_ID, "Order ID");
        options.put(DESCRIPTION, "Description");
        options.put(IP, "192.168.1.10");
        options.put(EMAIL, "email@example.com");
        options.put(MERCHANT_NAME_DESCRIPTOR, "Descriptor name");
        options.put(MERCHANT_LOCATION_DESCRIPTOR, "Descriptor location");

        // When
        Transaction t = GatewayRequests.verify(gatewayToken, options, AUTH);

        // Then
        assertNotNull(t);
        // TODO: assert transaction fields & payment method retained
    }

}
