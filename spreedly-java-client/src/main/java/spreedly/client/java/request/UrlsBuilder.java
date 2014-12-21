package spreedly.client.java.request;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlsBuilder
{

    private static final Logger log = LoggerFactory.getLogger(UrlsBuilder.class); 

    private static final String V1_BASE_URL = "https://core.spreedly.com";

    public static URL showTransactionUrl(String token)
    {
        return buildUrl("%s/v1/transactions/%s.xml", V1_BASE_URL, token);
    }

    private static URL buildUrl(String url, Object... args)
    {
        String formattedUrl = String.format(url, args);
        try
        {
            return new URL(formattedUrl);
        }
        catch (MalformedURLException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
