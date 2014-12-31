package spreedly.client.java.request;

import static spreedly.client.java.http.Request.POST;

import java.net.URL;
import java.util.Map;

import spreedly.client.java.Credentials;
import spreedly.client.java.exception.HttpHandlingException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.HttpHandlerFactory;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.model.VerifyPaymentMethodRequest;
import spreedly.client.java.xml.XmlOutputSource;
import spreedly.client.java.xml.XmlParser;
import spreedly.client.java.xml.XmlParserFactory;

public class GatewayRequests
{

    public static Transaction verify(String gatewayToken, Map<String, String> options, Credentials credentials) throws XmlParserException, HttpHandlingException
    {
        VerifyPaymentMethodRequest verifyRequest = new VerifyPaymentMethodRequest(options);

        XmlParser xmlParser = XmlParserFactory.getXmlParser();

        URL url = UrlsBuilder.verifyPaymentMethod(gatewayToken);
        XmlOutputSource body = new XmlOutputSource(xmlParser, verifyRequest);
        Request request = new Request(url, POST, credentials, body);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return xmlParser.parseTransaction(response.body);
    }

}
