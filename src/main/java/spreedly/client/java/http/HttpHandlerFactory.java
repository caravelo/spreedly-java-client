package spreedly.client.java.http;

public class HttpHandlerFactory
{

    private HttpHandlerFactory(){}

    public static HttpHandler getHttpHandler()
    {
        return new UrlConnectionHttpHandler();
    }

}
