package spreedly.client.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static spreedly.client.java.model.Fields.AMOUNT;
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

import spreedly.client.java.model.Error;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class SpreedlyTest
{

    private final Spreedly client;

    @Rule
    public Recorder recorder = new Recorder();

    public SpreedlyTest()
    {
        client = Spreedly.newEnvironment("test", "test");
    }

    @Betamax(tape = "credit-transaction")
    @Test
    public void testCreditTransactionShouldWork() throws Exception
    {
        // Given
        String transactionToken = "3bRZFo0aySutu5BSopqRQjh5nmO";

        Integer amount = 1234; // 12.34
        String currencyCode = "EUR";
        String orderId = "Credit";
        String description = "Credit transaction";
        String ip = "10.213.44.51";
        String email = "email@example.com";
        String merchantNameDescriptor = "Descriptor name";
        String merchantLocationDescriptor = "Descriptor location";

        Map<String, String> options = new HashMap<String, String>();
        options.put(AMOUNT, amount.toString());
        options.put(CURRENCY_CODE, currencyCode);
        options.put(ORDER_ID, orderId);
        options.put(DESCRIPTION, description);
        options.put(IP, ip);
        options.put(EMAIL, email);
        options.put(MERCHANT_NAME_DESCRIPTOR, merchantNameDescriptor);
        options.put(MERCHANT_LOCATION_DESCRIPTOR, merchantLocationDescriptor);

        // When
        Transaction t = client.refundTransaction(transactionToken, options);

        // Then
        assertNotNull(t);
        assertEquals("Aj2MSgxvxksJHldLnUjeIM5FUD1", t.getToken());
        assertTrue(t.getSucceeded());
        assertEquals("Credit", t.getTransactionType());
        assertEquals(amount, t.getAmount());
        assertEquals(currencyCode, t.getCurrencyCode());
        assertEquals(orderId, t.getOrderId());
        assertEquals(ip, t.getIp());
//      assertEquals(email, t.getEmail()); email doesn't show up even though it was sent
        assertEquals(description, t.getDescription());
        assertEquals(merchantNameDescriptor, t.getMerchantNameDescriptor());
        assertEquals(merchantLocationDescriptor, t.getMerchantLocationDescriptor());
        assertNull(t.getPaymentMethod());
        assertEquals(transactionToken, t.getReferenceToken());
    }

    @Betamax(tape = "purchase")
    @Test
    public void testPurchaseAndRetain() throws Exception
    {
        // Given
        int amount = 1234; // 12.34

        String gatewayToken = "XKqtfVWFvZgbwmrN5ZFdMZpB1XN";
        String paymentMethodToken = "U6LMHXfN6ZkOPUdXWKx6xO8DydG";
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
        options.put(IP, ip);
        options.put(EMAIL, email);
        options.put(MERCHANT_NAME_DESCRIPTOR, merchantNameDescriptor);
        options.put(MERCHANT_LOCATION_DESCRIPTOR, merchantLocationDescriptor);

        // When
        Transaction t = client.purchaseOnGateway(gatewayToken, paymentMethodToken, amount, options);

        // Then
        assertNotNull(t);
        assertTrue(t.getOnTestGateway());
        assertTrue(t.getSucceeded());
        assertEquals("Purchase", t.getTransactionType());
        assertEquals(orderId, t.getOrderId());
        assertEquals(ip, t.getIp());
        assertEquals(description, t.getDescription());
        assertEquals(email, t.getEmail());
        assertEquals(merchantNameDescriptor, t.getMerchantNameDescriptor());
        assertEquals(merchantLocationDescriptor, t.getMerchantLocationDescriptor());
        assertNotNull(t.getPaymentMethod());
    }

    @Betamax(tape = "show-payment-method")
    @Test
    public void testShowPaymentMethodShouldWork() throws Exception
    {
        // Given
        String token = "D9Smhdu9z5ijCc0RjAQIjOJ9MOy";

        // When
        PaymentMethod paymentMethod = client.findPaymentMethod(token);

        // Then
        assertNotNull(paymentMethod);
        assertEquals("D9Smhdu9z5ijCc0RjAQIjOJ9MOy", paymentMethod.getToken());
        assertEquals("visa", paymentMethod.getCardType());
        assertEquals("4242", paymentMethod.getLastFourDigits());
        assertEquals("Test", paymentMethod.getFirstName());
        assertEquals("1417080633043", paymentMethod.getLastName());
        assertEquals("credit_card", paymentMethod.getPaymentMethodType());
        assertTrue(1 == paymentMethod.getMonth());
        assertTrue(2016 == paymentMethod.getYear());
        assertNotNull(paymentMethod.getErrors());
        assertTrue(paymentMethod.getErrors().size() == 1);
        Error e = paymentMethod.getErrors().iterator().next();
        assertEquals("number", e.getAttribute());
        assertEquals("errors.blank", e.getKey());
        assertEquals("Number can't be blank", e.getMessage());
    }

    @Betamax(tape = "show-transaction")
    @Test
    public void testShowTransactionShouldWork() throws Exception
    {
        // Given
        String token = "Jsa3OQ6vkevAUpzwfQg4CHO8EdS";

        // When
        Transaction transaction = client.findTransaction(token);

        // Then
        assertNotNull(transaction);
        assertEquals("Jsa3OQ6vkevAUpzwfQg4CHO8EdS", transaction.getToken());
        assertTrue(transaction.getSucceeded());
        assertEquals("succeeded", transaction.getState());
        assertEquals("RedactPaymentMethod", transaction.getTransactionType());
        assertEquals("Succeeded!", transaction.getMessage());
        assertEquals("HKu11ZWm4WJWS6t7hI0k0Qb6FvW", transaction.getPaymentMethod().getToken());
        assertEquals("visa", transaction.getPaymentMethod().getCardType());
    }

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
        options.put(IP, ip);
        options.put(EMAIL, email);
        options.put(MERCHANT_NAME_DESCRIPTOR, merchantNameDescriptor);
        options.put(MERCHANT_LOCATION_DESCRIPTOR, merchantLocationDescriptor);

        // When
        Transaction t = client.verifyOnGateway(gatewayToken, paymentMethodToken, options);

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
