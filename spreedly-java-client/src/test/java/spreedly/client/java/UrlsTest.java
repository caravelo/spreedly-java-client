package spreedly.client.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import spreedly.client.java.request.Urls;

public class UrlsTest
{
    private static final String ANY_TOKEN = "JlyLp8cy1aAu7RRDGnAk83UZi2Z";

    @Test
    public void testFindTransactionUrl()
    {
        // Given
        String token = ANY_TOKEN;
        String expectedUrl = "https://core.spreedly.com/v1/transactions/JlyLp8cy1aAu7RRDGnAk83UZi2Z.xml";

        // When
        String url = Urls.showTransactionUrl(token);

        // Then
        assertEquals(expectedUrl, url);
    }
}
