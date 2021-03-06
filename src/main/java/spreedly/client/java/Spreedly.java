package spreedly.client.java;

import static spreedly.client.java.http.Request.GET;
import static spreedly.client.java.http.Request.POST;
import static spreedly.client.java.http.Request.PUT;
import static spreedly.client.java.model.Fields.AMOUNT;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.Fields.TRANSACTION_TOKEN;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spreedly.client.java.exception.AuthenticationException;
import spreedly.client.java.exception.SpreedlyClientException;
import spreedly.client.java.exception.UnprocessableTransactionException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.HttpHandlerFactory;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.Errors;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.RequestParameters;
import spreedly.client.java.model.RequestParametersWithGatewaySpecificFields;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.XmlOutputSource;
import spreedly.client.java.xml.XmlParser;
import spreedly.client.java.xml.XmlParserFactory;

public class Spreedly
{

    static final int STATUS_OK = 200;
    static final int STATUS_OK_CREATED = 201;
    static final int STATUS_UNAUTHORIZED = 401;
    static final int STATUS_PAYMENT_REQUIRED = 402;
    static final int STATUS_NOT_FOUND = 404;
    static final int STATUS_TIMEOUT = 408;
    static final int STATUS_UNPROCESSABLE = 422;
    static final int STATUS_TOO_MANY_REQUESTS = 429;
    static final int STATUS_UNAVAILABLE = 503;

    private final HttpHandler httpHandler;
    private final XmlParser xmlParser;

    private final Credentials credentials;

    public static Spreedly newEnvironment(String environmentKey, String accessSecret)
    {
        return new Spreedly(environmentKey, accessSecret);
    }

    protected Spreedly(HttpHandler httpHandler, XmlParser xmlParser,
            String environmentKey, String accessSecret)
    {
        this.httpHandler = httpHandler;
        this.xmlParser = xmlParser;

        this.credentials = new Credentials(environmentKey, accessSecret);
    }

    private Spreedly(String environmentKey, String accessSecret)
    {
        this(HttpHandlerFactory.getHttpHandler(),
                XmlParserFactory.getXmlParser(),
                environmentKey, accessSecret);
    }

    public PaymentMethod findPaymentMethod(String token) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.showPaymentMethod(token);
        Request request = new Request(url, GET, credentials);

        Response response = executeRequest(request);

        return xmlParser.parsePaymentMethod(response.body);
    }

    public Transaction findTransaction(String token) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.showTransaction(token);
        Request request = new Request(url, GET, credentials);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public List<PaymentMethod> listPaymentMethods() throws SpreedlyClientException
    {
        URL url = UrlsBuilder.indexPaymentMethods();
        return listPaymentMethods(url);
    }

    public List<PaymentMethod> listPaymentMethods(String sinceToken) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.indexPaymentMethods(sinceToken);
        return listPaymentMethods(url);
    }

    public List<Transaction> listTransactions() throws SpreedlyClientException
    {
        URL url = UrlsBuilder.indexTransactions();
        return listTransactions(url);
    }

    public List<Transaction> listTransactions(String sinceToken) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.indexTransactions(sinceToken);
        return listTransactions(url);
    }

    public Transaction purchaseOnGateway(String gatewayToken, String paymentMethodToken, int amount, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        options.put(AMOUNT, String.valueOf(amount));

        RequestParameters purchaseRequest = new RequestParameters(options);

        URL url = UrlsBuilder.purchase(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, purchaseRequest);
        Request request = new Request(url, POST, credentials, body);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction purchaseOnGatewayWithSpecificFields(
            String gatewayToken,
            String paymentMethodToken,
            int amount, Map<String,
            String> options,
            String gatewayType,
            Map<String, String> specificFields) throws SpreedlyClientException
    {
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        options.put(AMOUNT, String.valueOf(amount));

        RequestParametersWithGatewaySpecificFields purchaseRequest = new RequestParametersWithGatewaySpecificFields(
                options, gatewayType, specificFields);

        URL url = UrlsBuilder.purchase(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, purchaseRequest);
        Request request = new Request(url, POST, credentials, body);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction redactPaymentMethod(String paymentMethodToken) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.redactPaymentMethod(paymentMethodToken);
        Request request = new Request(url, PUT, credentials);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction refundTransaction(String transactionToken) throws SpreedlyClientException
    {
        return refundTransaction(transactionToken, new HashMap<String, String>());
    }

    public Transaction refundTransaction(String transactionToken, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(TRANSACTION_TOKEN, transactionToken);
        RequestParameters creditRequest = new RequestParameters(options);

        URL url = UrlsBuilder.credit(transactionToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, creditRequest);
        Request request = new Request(url, POST, credentials, body);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction retainPaymentMethod(String paymentMethodToken) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.retainPaymentMethod(paymentMethodToken);
        Request request = new Request(url, PUT, credentials);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction verifyOnGateway(String gatewayToken, String paymentMethodToken, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        RequestParameters verifyRequest = new RequestParameters(options);

        URL url = UrlsBuilder.verifyPaymentMethod(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, verifyRequest);
        Request request = new Request(url, POST, credentials, body);

        Response response = executeRequest(request);

        return xmlParser.parseTransaction(response.body);
    }

    // TODO: isolate into RequestsExecutor or similar
    protected Response executeRequest(Request request) throws SpreedlyClientException
    {
        Response response = httpHandler.execute(request);
        int statusCode = response.statusCode;
        Errors errors;

        switch (statusCode)
        {
            // Do nothing special
            case STATUS_OK:
            case STATUS_OK_CREATED:
                break;

            case STATUS_UNAUTHORIZED:
                errors = xmlParser.parseErrors(response.body);
                throw new AuthenticationException(errors
                        .getSingleError()
                        .getMessage());

            case STATUS_PAYMENT_REQUIRED:
            case STATUS_NOT_FOUND:
                errors = xmlParser.parseErrors(response.body);
                throw new SpreedlyClientException(errors
                        .getSingleError()
                        .getMessage());

            case STATUS_TIMEOUT:
                // TODO: find out if there is a response's body that allows to provide a more specific message
                throw new SpreedlyClientException("Request timeout");

            case STATUS_UNPROCESSABLE:
                handleUnprocessableTransactionError(response);

            case STATUS_TOO_MANY_REQUESTS:
                // TODO: find out if there is a response's body that allows to provide a more specific message
                throw new SpreedlyClientException("Too many requests");

            case STATUS_UNAVAILABLE:
                // TODO: find out if there is a response's body that allows to provide a more specific message
                throw new SpreedlyClientException("Service unavailable");

            // This might be serious dude!
            default:
                String msg = String.format("Unexpected response code [%s]", statusCode);
                throw new SpreedlyClientException(msg);
        }

        return response;
    }

    protected void handleUnprocessableTransactionError(Response response) throws SpreedlyClientException
    {
        // According to Spreedly API's docs there are 2 different types of response bodies...
        InputStream body = response.body;

        // 1. The transaction was created thus the transaction details are available (most likely scenario)
        try
        {
            Transaction transaction = xmlParser.parseTransaction(body);
            throw new UnprocessableTransactionException(transaction);
        }
        catch (XmlParserException e)
        {
            // 2. The transaction wasn't created and the response body contains just error details
            Errors errors = xmlParser.parseErrors(response.body);
            throw new SpreedlyClientException(errors
                    .getSingleError()
                    .getMessage());
        }
    }

    private List<PaymentMethod> listPaymentMethods(URL url)
            throws SpreedlyClientException, XmlParserException
    {
        Request request = new Request(url, GET, credentials);

        Response response = executeRequest(request);

        return xmlParser.parsePaymentMethods(response.body);
    }

    private List<Transaction> listTransactions(URL url)
            throws SpreedlyClientException, XmlParserException
    {
        Request request = new Request(url, GET, credentials);

        Response response = executeRequest(request);

        return xmlParser.parseTransactions(response.body);
    }

}
