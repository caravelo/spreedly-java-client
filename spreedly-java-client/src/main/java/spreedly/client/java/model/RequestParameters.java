package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.AMOUNT;
import static spreedly.client.java.model.Fields.CURRENCY_CODE;
import static spreedly.client.java.model.Fields.DESCRIPTION;
import static spreedly.client.java.model.Fields.EMAIL;
import static spreedly.client.java.model.Fields.IP;
import static spreedly.client.java.model.Fields.MERCHANT_LOCATION_DESCRIPTOR;
import static spreedly.client.java.model.Fields.MERCHANT_NAME_DESCRIPTOR;
import static spreedly.client.java.model.Fields.ORDER_ID;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.Fields.RETAIN_ON_SUCCESS;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction")
public class RequestParameters
{

    @Element(name = PAYMENT_METHOD_TOKEN, required = false)
    private final String paymentMethodToken;

    @Element(name = AMOUNT, required = false)
    private final String amount;

    @Element(name = CURRENCY_CODE, required = false)
    private final String currencyCode;

    @Element(name = RETAIN_ON_SUCCESS, required = false)
    private final Boolean retainOnSuccess;

    @Element(name = ORDER_ID, required = false)
    private final String orderId;

    @Element(name = DESCRIPTION, required = false)
    private final String description;

    @Element(name = IP, required = false)
    private final String ip;

    @Element(name = EMAIL, required = false)
    private final String email;

    @Element(name = MERCHANT_NAME_DESCRIPTOR, required = false)
    private final String merchantNameDescriptor;

    @Element(name = MERCHANT_LOCATION_DESCRIPTOR, required = false)
    private final String merchantLocationDescriptor;

    public RequestParameters(Map<String, String> options)
    {
        super();
        this.paymentMethodToken = options.get(PAYMENT_METHOD_TOKEN);
        this.amount = options.get(AMOUNT);
        this.currencyCode = options.get(CURRENCY_CODE);
        this.retainOnSuccess = Boolean.parseBoolean(options.get(RETAIN_ON_SUCCESS));
        this.orderId = options.get(ORDER_ID);
        this.description = options.get(DESCRIPTION);
        this.ip = options.get(IP);
        this.email = options.get(EMAIL);
        this.merchantNameDescriptor = options.get(MERCHANT_NAME_DESCRIPTOR);
        this.merchantLocationDescriptor = options.get(MERCHANT_LOCATION_DESCRIPTOR);
    }

    public String getAmount()
    {
        return amount;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public String getDescription()
    {
        return description;
    }

    public String getEmail()
    {
        return email;
    }

    public String getIp()
    {
        return ip;
    }

    public String getMerchantLocationDescriptor()
    {
        return merchantLocationDescriptor;
    }

    public String getMerchantNameDescriptor()
    {
        return merchantNameDescriptor;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public String getPaymentMethodToken()
    {
        return paymentMethodToken;
    }

    public Boolean getRetainOnSuccess()
    {
        return retainOnSuccess;
    }

    public Boolean isRetainOnSuccess()
    {
        return retainOnSuccess;
    }

}