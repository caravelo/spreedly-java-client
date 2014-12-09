package spreedly.client.java.http;

import javax.xml.bind.DatatypeConverter;

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
        String encodedAuthString = DatatypeConverter.printBase64Binary(plainAuthString.getBytes());
        return "Basic " + encodedAuthString;
    }
}
