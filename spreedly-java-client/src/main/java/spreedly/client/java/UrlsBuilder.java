package spreedly.client.java;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlsBuilder
{

    private static final Logger log = LoggerFactory.getLogger(UrlsBuilder.class); 

    private static final String V1_BASE_URL = "https://core.spreedly.com/v1";

    public static URL credit(String transactionToken)
    {
        return buildUrl("%s/transactions/%s/credit.xml", V1_BASE_URL, transactionToken);
    }

    public static URL purchase(String gatewayToken)
    {
        return buildUrl("%s/gateways/%s/purchase.xml", V1_BASE_URL, gatewayToken);
    }

    public static URL redactPaymentMethod(String paymentMethodToken)
    {
        return buildUrl("%s/payment_methods/%s/redact.xml", V1_BASE_URL, paymentMethodToken);
    }

    public static URL retainPaymentMethod(String paymentMethodToken)
    {
        return buildUrl("%s/payment_methods/%s/retain.xml", V1_BASE_URL, paymentMethodToken);
    }

    public static URL showTransaction(String transactionToken)
    {
        return buildUrl("%s/transactions/%s.xml", V1_BASE_URL, transactionToken);
    }

    public static URL showPaymentMethod(String paymentMethodToken)
    {
        return buildUrl("%s/payment_methods/%s.xml", V1_BASE_URL, paymentMethodToken);
    }

    public static URL verifyPaymentMethod(String paymentMethodToken)
    {
        return buildUrl("%s/gateways/%s/verify.xml", V1_BASE_URL, paymentMethodToken);
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
