package spreedly.client.java.request;

public class Urls
{

    private static final String V1_BASE_URL = "https://core.spreedly.com";

    public static String showTransactionUrl(String token)
    {
        return String.format("%s/v1/transactions/%s.xml", V1_BASE_URL, token);
    }
}
