package spreedly.client.java.http;

import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates an HTTP request.
 *
 * @author Kevin Litwack (kevin@kevinlitwack.com)
 */
public final class Request {

    ///// PUBLIC CONSTANTS /////

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    ///// PROPERTIES /////

    public final URL url;
    public final Map<String, String> properties;
    public final String method;
    public final BasicHttpAuth authorization;
    public final OutputSource body;
    public final Proxy proxy;

    ///// PUBLIC CONSTRUCTORS /////

    public Request(URL url, String method, BasicHttpAuth authorization) {
        this(url, method, authorization, null, null);
    }

    public Request(URL url, String method, BasicHttpAuth authorization, OutputSource body) {
        this(url, method, authorization, body, null);
    }

    public Request(URL url, String method, BasicHttpAuth authorization, OutputSource body, Proxy proxy) {
        this.url = url;
        this.properties = new HashMap<String, String>();
        this.method = method;
        this.authorization = authorization;
        this.body = body;
        this.proxy = proxy;
    }

    public Request property(String name, String value)
    {
        this.properties.put(name, value);
        return this;
    }

    public void removeProperty(String name)
    {
        this.properties.remove(name);
    }
}