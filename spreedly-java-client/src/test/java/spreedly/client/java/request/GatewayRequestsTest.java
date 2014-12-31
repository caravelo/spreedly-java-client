package spreedly.client.java.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
        String paymentMethodToken = "EL8G2Njs9LdUDKlNSbonADvcxJV";
        String retainOnSuccess = "true";
        String currencyCode = "EUR";
        String orderId = "Order ID";
        String description = "Description";
        String ip = "192.168.1.10";
        String email = "email@example.com";
        String merchantNameDescriptor = "Descriptor name";
        String merchantLocationDescriptor = "Descriptor location";

        Map<String, String> options = new HashMap<String, String>();
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        options.put(RETAIN_ON_SUCCESS, retainOnSuccess);
        options.put(CURRENCY_CODE, currencyCode);
        options.put(ORDER_ID, orderId);
        options.put(DESCRIPTION, description);
        options.put(IP, "192.168.1.10");
        options.put(EMAIL, email);
        options.put(MERCHANT_NAME_DESCRIPTOR, merchantNameDescriptor);
        options.put(MERCHANT_LOCATION_DESCRIPTOR, merchantLocationDescriptor);

        // When
        Transaction t = GatewayRequests.verify(gatewayToken, options, AUTH);

        // Then
        assertNotNull(t);
        assertTrue(t.getOnTestGateway());
        assertTrue(t.getSucceeded());
        assertEquals("Verification", t.getTransactionType());
        assertEquals(orderId, t.getOrderId());
        assertEquals(ip, t.getIp());
        assertEquals(description, t.getDescription());
        assertEquals(email, t.getEmail());
        assertEquals(merchantNameDescriptor, t.getMerchantNameDescriptor());
        assertEquals(merchantLocationDescriptor, t.getMerchantLocationDescriptor());
        assertNotNull(t.getPaymentMethod());
    }

}
