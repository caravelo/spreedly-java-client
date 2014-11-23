package spreedly.client.java.http;

import java.net.Proxy;
import java.net.URL;
import java.util.Map;

/**
 * Encapsulates an HTTP request.
 *
 * @author Kevin Litwack (kevin@kevinlitwack.com)
 */
public final class Request {

    ///// PROPERTIES /////

    public final URL url;
    public final Map<String, String> properties;
    public final String method;
    public final String authorization;
    public final OutputSource body;
    public final Proxy proxy;

    ///// PUBLIC CONSTRUCTORS /////

    public Request(URL url, Map<String, String> properties, String method, String authorization, OutputSource body) {
        this(url, properties, method, authorization, body, null);
    }

    public Request(URL url, Map<String, String> properties, String method, String authorization, OutputSource body, Proxy proxy) {
        this.url = url;
        this.properties = properties;
        this.method = method;
        this.authorization = authorization;
        this.body = body;
        this.proxy = proxy;
    }

}