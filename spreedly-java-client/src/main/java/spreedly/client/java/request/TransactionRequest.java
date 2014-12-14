package spreedly.client.java.request;

import static spreedly.client.java.http.Request.GET;

import java.net.URL;

import spreedly.client.java.Credentials;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.http.UrlConnectionHttpHandler;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.SimpleXmlParser;

public class TransactionRequest
{

    // XXX: PROPER EXCEPTIONS AND EXCEPTION HANDLING!!!
    public static Transaction show(String token, Credentials credentials) throws Exception
    {
        String url = Urls.showTransactionUrl(token);

        Request request = new Request(new URL(url), GET, credentials);

        HttpHandler httpHandler = new UrlConnectionHttpHandler();
        Response response = httpHandler.execute(request);

        return new SimpleXmlParser().parseTransaction(response.body);
    }
}
