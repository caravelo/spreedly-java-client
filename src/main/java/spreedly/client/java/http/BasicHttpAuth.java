package spreedly.client.java.http;

import static javax.xml.bind.DatatypeConverter.printBase64Binary;

import java.nio.charset.StandardCharsets;

public class BasicHttpAuth
{
 
    private final String username;
    private final String password;

    public BasicHttpAuth(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getAuthString()
    {
        String plainAuthString = username + ":" +password;
        String encodedAuthString = printBase64Binary(plainAuthString
                .getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedAuthString;
    }
}
