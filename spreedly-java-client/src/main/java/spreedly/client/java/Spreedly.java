package spreedly.client.java;

import static spreedly.client.java.http.Request.GET;
import static spreedly.client.java.http.Request.POST;
import static spreedly.client.java.model.Fields.AMOUNT;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.Fields.TRANSACTION_TOKEN;

import java.net.URL;
import java.util.Map;

import spreedly.client.java.exception.SpreedlyClientException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.HttpHandlerFactory;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.RequestParameters;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.XmlOutputSource;
import spreedly.client.java.xml.XmlParser;
import spreedly.client.java.xml.XmlParserFactory;

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
        URL url = UrlsBuilder.showPaymentMethod(token);
        Request request = new Request(url, GET, credentials);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return XmlParserFactory.getXmlParser().parsePaymentMethod(response.body);
    }

    public Transaction findTransaction(String token) throws SpreedlyClientException
    {
        URL url = UrlsBuilder.showTransaction(token);
        Request request = new Request(url, GET, credentials);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return XmlParserFactory.getXmlParser().parseTransaction(response.body);
    }

    public Transaction purchaseOnGateway(String gatewayToken, String paymentMethodToken, int amount, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(AMOUNT, String.valueOf(amount));
        RequestParameters purchaseRequest = new RequestParameters(options);

        XmlParser xmlParser = XmlParserFactory.getXmlParser();

        URL url = UrlsBuilder.purchase(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, purchaseRequest);
        Request request = new Request(url, POST, credentials, body);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction refundTransaction(String transactionToken, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(TRANSACTION_TOKEN, transactionToken);
        RequestParameters creditRequest = new RequestParameters(options);

        XmlParser xmlParser = XmlParserFactory.getXmlParser();

        URL url = UrlsBuilder.credit(transactionToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, creditRequest);
        Request request = new Request(url, POST, credentials, body);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return xmlParser.parseTransaction(response.body);
    }

    public Transaction verifyOnGateway(String gatewayToken, String paymentMethodToken, Map<String, String> options) throws SpreedlyClientException
    {
        options.put(PAYMENT_METHOD_TOKEN, paymentMethodToken);
        RequestParameters verifyRequest = new RequestParameters(options);

        XmlParser xmlParser = XmlParserFactory.getXmlParser();

        URL url = UrlsBuilder.verifyPaymentMethod(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, verifyRequest);
        Request request = new Request(url, POST, credentials, body);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return xmlParser.parseTransaction(response.body);
    }

}
