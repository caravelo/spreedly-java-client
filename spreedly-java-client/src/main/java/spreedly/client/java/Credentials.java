package spreedly.client.java;

import spreedly.client.java.http.BasicHttpAuth;

public class Credentials extends BasicHttpAuth
{

    public Credentials(String environmentKey, String accessSecret)
    {
        super(environmentKey, accessSecret);
    }

}
