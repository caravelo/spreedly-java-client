package spreedly.client.java;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static spreedly.client.java.Spreedly.STATUS_OK;
import static spreedly.client.java.Spreedly.STATUS_OK_CREATED;
import static spreedly.client.java.Spreedly.STATUS_PAYMENT_REQUIRED;
import static spreedly.client.java.Spreedly.STATUS_TIMEOUT;
import static spreedly.client.java.Spreedly.STATUS_TOO_MANY_REQUESTS;
import static spreedly.client.java.Spreedly.STATUS_UNAUTHORIZED;
import static spreedly.client.java.Spreedly.STATUS_UNPROCESSABLE;
import static spreedly.client.java.http.Request.POST;
import static spreedly.client.java.model.Fields.AMOUNT;
import static spreedly.client.java.model.Fields.CURRENCY_CODE;
import static spreedly.client.java.model.Fields.DESCRIPTION;
import static spreedly.client.java.model.Fields.EMAIL;
import static spreedly.client.java.model.Fields.IP;
import static spreedly.client.java.model.Fields.MERCHANT_LOCATION_DESCRIPTOR;
import static spreedly.client.java.model.Fields.MERCHANT_NAME_DESCRIPTOR;
import static spreedly.client.java.model.Fields.ORDER_ID;
import static spreedly.client.java.model.Fields.RETAIN_ON_SUCCESS;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import spreedly.client.java.exception.AuthenticationException;
import spreedly.client.java.exception.HttpHandlingException;
import spreedly.client.java.exception.SpreedlyClientException;
import spreedly.client.java.exception.UnprocessableTransactionException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.Error;
import spreedly.client.java.model.Errors;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.XmlParser;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class SpreedlyTest
{

    @Mock
    private HttpHandler httpHandler;

    @Mock
    private XmlParser xmlParser;

    private final Spreedly mockClient;

    private final Spreedly client;

    @Rule
    public Recorder recorder = new Recorder();

    public SpreedlyTest()
    {
        httpHandler = Mockito.mock(HttpHandler.class);
        xmlParser = Mockito.mock(XmlParser.class);
        mockClient = new Spreedly(httpHandler, xmlParser, "hey", "jude");

        client = Spreedly.newEnvironment("IG8KIz0eOF4ECwpVtiRJDE0XBCD", "1ahpbV0VNUy55A3dBRVRT8EpXMpFJLZeoS4pjzu4wiGL5qfNLWyemoF6kjc7wPU3");
    }

    @Test (expected = SpreedlyClientException.class)
    public void handleUnprocessableTransactionErrorShouldThrowException() throws SpreedlyClientException
    {
        // Given
        Response response = prepareMockResponse(Mockito.any(Request.class), STATUS_UNPROCESSABLE);

        // When
        mockClient.handleUnprocessableTransactionError(response);

        // Then
        fail();
    }

    @Test (expected = UnprocessableTransactionException.class)
    public void handleUnprocessableTransactionErrorShouldThrowUnproccessableTransactionException() throws SpreedlyClientException
    {
        // Given
        Response response = prepareMockResponse(Mockito.any(Request.class), STATUS_UNPROCESSABLE);

        // When
        mockClient.handleUnprocessableTransactionError(response);

        // Then
        fail(format("Expected [{}] not thrown", UnprocessableTransactionException.class.getName()));
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

    @Test
    public void testExecuteRequestShouldReturnResponseWithResponseStatusCode200() throws SpreedlyClientException
    {
        testExecuteRequestShouldWork(STATUS_OK);
    }

    @Test
    public void testExecuteRequestShouldReturnResponseWithResponseStatusCode201() throws SpreedlyClientException
    {
        testExecuteRequestShouldWork(STATUS_OK_CREATED);
    }

    @Test (expected = AuthenticationException.class)
    public void testExecuteRequestShouldThrowAuthenticationException() throws SpreedlyClientException
    {
        testExecuteRequestShouldFail(STATUS_UNAUTHORIZED);
    }

    @Test (expected = SpreedlyClientException.class)
    public void testExecuteRequestShouldThrowExceptionWithResponseStatusCode402() throws SpreedlyClientException
    {
        testExecuteRequestShouldFail(STATUS_PAYMENT_REQUIRED);
    }

    @Test (expected = SpreedlyClientException.class)
    public void testExecuteRequestShouldThrowExceptionWithResponseStatusCode408() throws SpreedlyClientException
    {
        testExecuteRequestShouldFail(STATUS_TIMEOUT);
    }

    @Test (expected = SpreedlyClientException.class)
    public void testExecuteRequestShouldThrowExceptionWithResponseStatusCode429() throws SpreedlyClientException
    {
        testExecuteRequestShouldFail(STATUS_TOO_MANY_REQUESTS);
    }

    @Test (expected = SpreedlyClientException.class)
    public void testExecuteRequestShouldThrowExceptionWithResponseStatusCode503() throws SpreedlyClientException
    {
        testExecuteRequestShouldFail(Spreedly.STATUS_UNAVAILABLE);
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

    @Betamax(tape = "redact-payment-method")
    @Test
    public void testRedactPaymentMethod() throws Exception
    {
        // Given
        String paymentMethodToken = "QfoZx6FiL8fAZykd3HkL7l7bchC";

        // When
        Transaction t = client.redactPaymentMethod(paymentMethodToken);

        // Then
        assertNotNull(t);
        assertTrue(t.getSucceeded());
        assertEquals("RedactPaymentMethod", t.getTransactionType());
        assertNotNull(t.getPaymentMethod());
    }

    @Betamax(tape = "retain-payment-method")
    @Test
    public void testRetainPaymentMethod() throws Exception
    {
        // Given
        String paymentMethodToken = "MsBqngzrAY84i9MNvZ08scYX4w0";

        // When
        Transaction t = client.retainPaymentMethod(paymentMethodToken);

        // Then
        assertNotNull(t);
        assertTrue(t.getSucceeded());
        assertEquals("RetainPaymentMethod", t.getTransactionType());
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
        assertEquals("messages.transaction_succeeded", transaction.getMessageKey());
        assertEquals("Succeeded!", transaction.getMessage());
        assertEquals("HKu11ZWm4WJWS6t7hI0k0Qb6FvW", transaction.getPaymentMethod().getToken());
        assertEquals("visa", transaction.getPaymentMethod().getCardType());
    }

    @Betamax(tape = "transactions-index")
    @Test
    public void testListTransactionsShouldWork() throws Exception
    {
        // When
        List<Transaction> txs = client.listTransactions();

        // Then
        assertNotNull(txs);
        assertEquals(20, txs.size());
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

    private void prepareExecuteRequestTest(Request request, int responseStatusCode, List<Error> responseErrors) throws HttpHandlingException, XmlParserException
    {
        prepareMockResponse(request, responseStatusCode);

        if (responseErrors != null)
        {
            Errors errors = new Errors(responseErrors);
            when(xmlParser.parseErrors(Mockito.any(InputStream.class))).thenReturn(errors);
        }
    }

    private Response prepareMockResponse(Request request, int responseStatusCode)
            throws HttpHandlingException
    {
        Response response = new Response(responseStatusCode, null);
        when(httpHandler.execute(request)).thenReturn(response);

        return response;
    }

    private void testExecuteRequestShouldFail(int responseStatusCode) throws SpreedlyClientException
    {
        // Given
        Request request = new Request(UrlsBuilder.showPaymentMethod("any"), POST, null);

        List<Error> errorsList = new ArrayList<>();
        errorsList.add(new Error("", "error.key", "Request failed"));

        prepareExecuteRequestTest(request, responseStatusCode, errorsList);

        // When
        mockClient.executeRequest(request);

        // Then
        fail("Expected exception not thrown");
    }

    private void testExecuteRequestShouldWork(int responseStatusCode) throws SpreedlyClientException
    {
        // Given
        Request request = new Request(UrlsBuilder.showPaymentMethod("any"), POST, null);

        prepareExecuteRequestTest(request, responseStatusCode, null);

        // When
        Response response = mockClient.executeRequest(request);

        // Then
        assertNotNull(response);
        assertEquals(responseStatusCode, response.statusCode);
    }

}
