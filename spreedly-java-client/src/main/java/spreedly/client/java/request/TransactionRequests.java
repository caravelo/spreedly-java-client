package spreedly.client.java.request;

import static spreedly.client.java.http.Request.GET;
import static spreedly.client.java.http.Request.POST;
import static spreedly.client.java.model.Fields.TRANSACTION_TOKEN;

import java.net.URL;
import java.util.Map;

import spreedly.client.java.Credentials;
import spreedly.client.java.exception.HttpHandlingException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.HttpHandlerFactory;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.RequestParameters;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.XmlOutputSource;
import spreedly.client.java.xml.XmlParser;
import spreedly.client.java.xml.XmlParserFactory;

public class TransactionRequests
{

    public static Transaction credit(String transactionToken, Map<String, String> options, Credentials credentials) throws XmlParserException, HttpHandlingException
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

    public static Transaction show(String token, Credentials credentials) throws XmlParserException, HttpHandlingException
    {
        URL url = UrlsBuilder.showTransaction(token);
        Request request = new Request(url, GET, credentials);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return XmlParserFactory.getXmlParser().parseTransaction(response.body);
    }

}
