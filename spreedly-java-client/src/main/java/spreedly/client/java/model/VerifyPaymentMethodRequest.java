package spreedly.client.java.model;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction")
public class VerifyPaymentMethodRequest
{
    // TODO: move all fields spread among all entities into a common place 

    public static final String PAYMENT_METHOD_TOKEN = "payment_method_token";
    public static final String RETAIN_ON_SUCCESS = "retain_on_success";
    public static final String CURRENCY_CODE = "currency_code";
    public static final String ORDER_ID = "order_id";
    public static final String DESCRIPTION = "description";
    public static final String IP = "ip";
    public static final String EMAIL = "email";
    public static final String MERCHANT_NAME_DESCRIPTOR = "merchant_name_descriptor";
    public static final String MERCHANT_LOCATION_DESCRIPTOR = "merchant_location_descriptor";

    @Element(name = PAYMENT_METHOD_TOKEN)
    private final String paymentMethodToken;

    @Element(name = RETAIN_ON_SUCCESS)
    private final boolean retainOnSuccess;

    @Element(name = CURRENCY_CODE)
    private final String currencyCode;

    @Element(name = ORDER_ID)
    private final String orderId;

    @Element(name = DESCRIPTION)
    private final String description;

    @Element(name = IP)
    private final String ip;

    @Element(name = EMAIL)
    private final String email;

    @Element(name = MERCHANT_NAME_DESCRIPTOR)
    private final String merchantNameDescriptor;

    @Element(name = MERCHANT_LOCATION_DESCRIPTOR)
    private final String merchantLocationDescriptor;

    public VerifyPaymentMethodRequest(Map<String, String> options)
    {
        super();
        this.paymentMethodToken = options.get(PAYMENT_METHOD_TOKEN);
        this.retainOnSuccess = Boolean.parseBoolean(options.get(RETAIN_ON_SUCCESS));
        this.currencyCode = options.get(CURRENCY_CODE);
        this.orderId = options.get(ORDER_ID);
        this.description = options.get(DESCRIPTION);
        this.ip = options.get(IP);
        this.email = options.get(EMAIL);
        this.merchantNameDescriptor = options.get(MERCHANT_NAME_DESCRIPTOR);
        this.merchantLocationDescriptor = options.get(MERCHANT_LOCATION_DESCRIPTOR);
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

    public boolean isRetainOnSuccess()
    {
        return retainOnSuccess;
    }

}
