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
    protected final String paymentMethodToken;

    @Element(name = AMOUNT, required = false)
    protected final String amount;

    @Element(name = CURRENCY_CODE, required = false)
    protected final String currencyCode;

    @Element(name = RETAIN_ON_SUCCESS, required = false)
    protected final Boolean retainOnSuccess;

    @Element(name = ORDER_ID, required = false)
    protected final String orderId;

    @Element(name = DESCRIPTION, required = false)
    protected final String description;

    @Element(name = IP, required = false)
    protected final String ip;

    @Element(name = EMAIL, required = false)
    protected final String email;

    @Element(name = MERCHANT_NAME_DESCRIPTOR, required = false)
    protected final String merchantNameDescriptor;

    @Element(name = MERCHANT_LOCATION_DESCRIPTOR, required = false)
    protected final String merchantLocationDescriptor;

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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RequestParameters other = (RequestParameters) obj;
        if (amount == null) {
            if (other.amount != null) return false;
        } else if (!amount.equals(other.amount)) return false;
        if (currencyCode == null) {
            if (other.currencyCode != null) return false;
        } else if (!currencyCode.equals(other.currencyCode)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (ip == null) {
            if (other.ip != null) return false;
        } else if (!ip.equals(other.ip)) return false;
        if (merchantLocationDescriptor == null) {
            if (other.merchantLocationDescriptor != null) return false;
        } else if (!merchantLocationDescriptor.equals(other.merchantLocationDescriptor)) return false;
        if (merchantNameDescriptor == null) {
            if (other.merchantNameDescriptor != null) return false;
        } else if (!merchantNameDescriptor.equals(other.merchantNameDescriptor)) return false;
        if (orderId == null) {
            if (other.orderId != null) return false;
        } else if (!orderId.equals(other.orderId)) return false;
        if (paymentMethodToken == null) {
            if (other.paymentMethodToken != null) return false;
        } else if (!paymentMethodToken.equals(other.paymentMethodToken)) return false;
        if (retainOnSuccess == null) {
            if (other.retainOnSuccess != null) return false;
        } else if (!retainOnSuccess.equals(other.retainOnSuccess)) return false;
        return true;
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((merchantLocationDescriptor == null) ? 0 : merchantLocationDescriptor.hashCode());
        result = prime * result + ((merchantNameDescriptor == null) ? 0 : merchantNameDescriptor.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((paymentMethodToken == null) ? 0 : paymentMethodToken.hashCode());
        result = prime * result + ((retainOnSuccess == null) ? 0 : retainOnSuccess.hashCode());
        return result;
    }

    public Boolean isRetainOnSuccess()
    {
        return retainOnSuccess;
    }

    @Override
    public String toString()
    {
        return "RequestParameters [paymentMethodToken=" + paymentMethodToken + ", amount=" + amount + ", currencyCode="
                + currencyCode + ", retainOnSuccess=" + retainOnSuccess + ", orderId=" + orderId + ", description="
                + description + ", ip=" + ip + ", email=" + email + ", merchantNameDescriptor=" + merchantNameDescriptor
                + ", merchantLocationDescriptor=" + merchantLocationDescriptor + "]";
    }

}
