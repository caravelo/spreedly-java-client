package spreedly.client.java.request;

import static spreedly.client.java.http.Request.GET;

import java.net.URL;

import spreedly.client.java.Credentials;
import spreedly.client.java.exception.HttpHandlingException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.http.UrlConnectionHttpHandler;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.SimpleXmlParser;

public class TransactionRequest
{

    public static Transaction show(String token, Credentials credentials) throws XmlParserException, HttpHandlingException
    {
        URL url = UrlsBuilder.showTransactionUrl(token);
        Request request = new Request(url, GET, credentials);

        // XXX: Use interface created by appropriate factory
        HttpHandler httpHandler = new UrlConnectionHttpHandler();
        Response response = httpHandler.execute(request);

        // XXX: Use interface created by appropriate factory
        return new SimpleXmlParser().parseTransaction(response.body);
    }

}
