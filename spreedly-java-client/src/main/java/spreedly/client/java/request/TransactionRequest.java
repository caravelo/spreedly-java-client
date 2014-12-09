package spreedly.client.java.request;

import java.io.IOException;
import java.net.URL;

import spreedly.client.java.Credentials;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.http.UrlConnectionHttpHandler;
import spreedly.client.java.model.Transaction;

public class TransactionRequest
{

    public static Transaction show(String token, Credentials credentials) throws IOException
    {
        String url = Urls.showTransactionUrl(token);

        Request request = new Request(new URL(url), "GET", credentials, null);

        HttpHandler httpHandler = new UrlConnectionHttpHandler();
        Response response = httpHandler.execute(request);
        
        return null;
    }
}
