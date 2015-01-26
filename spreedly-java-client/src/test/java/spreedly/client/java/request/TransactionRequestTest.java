package spreedly.client.java.request;

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

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

import spreedly.client.java.Credentials;
import spreedly.client.java.model.Transaction;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class TransactionRequestTest
{

    private static final Credentials AUTH =
            new Credentials("test", "test");

    @Rule
    public Recorder recorder = new Recorder();

    @Betamax(tape = "credit-transaction")
    @Test
    public void testCreditTransactionShouldWork() throws Exception
    {
        // Given
        String token = "3bRZFo0aySutu5BSopqRQjh5nmO";

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
        Transaction t = TransactionRequests.credit(token, options, AUTH);

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
        assertEquals(token, t.getReferenceToken());
    }

    @Betamax(tape = "show-transaction")
    @Test
    public void testShowTransactionShouldWork() throws Exception
    {
        // Given
        String token = "Jsa3OQ6vkevAUpzwfQg4CHO8EdS";

        // When
        Transaction transaction = TransactionRequests.show(token, AUTH);

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

}
