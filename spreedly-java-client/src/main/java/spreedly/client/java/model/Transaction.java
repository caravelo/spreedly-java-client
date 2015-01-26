package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.*;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction")
public class Transaction extends Base
{

    @Element(name = AMOUNT, required = false)
    private final Integer amount;

    @Element(name = ON_TEST_GATEWAY)
    private final Boolean onTestGateway;

    @Element(name = CURRENCY_CODE, required = false)
    private final String currencyCode;

    @Element(name = SUCCEEDED)
    private final Boolean succeeded;

    // TODO: should be Enum (succeeded, failed, etc)
    @Element(name = STATE)
    private final String state;

    // TODO: should be Enum (Authorization, Capture, Credit)
    @Element(name = TRANSACTION_TYPE)
    private final String transactionType;

    @Element(name = ORDER_ID, required = false)
    private final String orderId;

    @Element(name = IP, required = false)
    private final String ip;

    @Element(name = DESCRIPTION, required = false)
    private final String description;

    @Element(name = EMAIL, required = false)
    private final String email;

    @Element(name = MERCHANT_NAME_DESCRIPTOR, required = false)
    private final String merchantNameDescriptor;

    @Element(name = MERCHANT_LOCATION_DESCRIPTOR, required = false)
    private final String merchantLocationDescriptor;

    // TODO: gateway_specific_fields, gateway_specific_response_fields

    @Element(name = GATEWAY_TRANSACTION_ID, required = false)
    private final String gatewayTransactionId;

    @Element(name = RETAIN_ON_SUCCESS, required = false)
    private final Boolean retainOnSuccess;

    @Element(name = PAYMENT_METHOD_ADDED, required = false)
    private final Boolean paymentMethodAdded;

    @Element(name = MESSAGE)
    private final String message;

    @Element(name = GATEWAY_TOKEN, required = false)
    private final String gatewayToken;

    @Element(name = RESPONSE, required = false)
    private final Response response;

    @Element(name = PAYMENT_METHOD, required = false)
    private final PaymentMethod paymentMethod;
    
    // TODO: api_urls

    @Element(name = REFERENCE_TOKEN, required = false)
    private final String referenceToken;

    public Transaction(
            @Element(name = AMOUNT) Integer amount,
            @Element(name = ON_TEST_GATEWAY) Boolean onTestGateway,
            @Element(name = CREATED_AT) Date createdAt,
            @Element(name = UPDATED_AT) Date updatedAt,
            @Element(name = CURRENCY_CODE) String currencyCode,
            @Element(name = SUCCEEDED) Boolean succeeded,
            @Element(name = STATE) String state,
            @Element(name = TOKEN) String token,
            @Element(name = TRANSACTION_TYPE) String transactionType,
            @Element(name = ORDER_ID) String orderId,
            @Element(name = IP) String ip,
            @Element(name = DESCRIPTION) String description,
            @Element(name = EMAIL) String email,
            @Element(name = MERCHANT_NAME_DESCRIPTOR) String merchantNameDescriptor,
            @Element(name = MERCHANT_LOCATION_DESCRIPTOR) String merchantLocationDescriptor,
            @Element(name = GATEWAY_TRANSACTION_ID) String gatewayTransactionId,
            @Element(name = RETAIN_ON_SUCCESS) Boolean retainOnSuccess,
            @Element(name = PAYMENT_METHOD_ADDED) Boolean paymentMethodAdded,
            @Element(name = MESSAGE) String message,
            @Element(name = GATEWAY_TOKEN) String gatewayToken,
            @Element(name = RESPONSE) Response response,
            @Element(name = PAYMENT_METHOD) PaymentMethod paymentMethod,
            @Element(name = REFERENCE_TOKEN) String referenceToken)
    {
        super(token, createdAt, updatedAt);
        this.amount = amount;
        this.onTestGateway = onTestGateway;
        this.currencyCode = currencyCode;
        this.succeeded = succeeded;
        this.state = state;
        this.transactionType = transactionType;
        this.orderId = orderId;
        this.ip = ip;
        this.description = description;
        this.email = email;
        this.merchantNameDescriptor = merchantNameDescriptor;
        this.merchantLocationDescriptor = merchantLocationDescriptor;
        this.gatewayTransactionId = gatewayTransactionId;
        this.retainOnSuccess = retainOnSuccess;
        this.paymentMethodAdded = paymentMethodAdded;
        this.message = message;
        this.gatewayToken = gatewayToken;
        this.response = response;
        this.paymentMethod = paymentMethod;
        this.referenceToken = referenceToken;
    }

    public Integer getAmount()
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

    public String getGatewayToken()
    {
        return gatewayToken;
    }

    public String getGatewayTransactionId()
    {
        return gatewayTransactionId;
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

    public String getMessage()
    {
        return message;
    }

    public Boolean getOnTestGateway()
    {
        return onTestGateway;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    public Boolean getPaymentMethodAdded()
    {
        return paymentMethodAdded;
    }

    public String getReferenceToken()
    {
        return referenceToken;
    }

    public Response getResponse()
    {
        return response;
    }

    public Boolean getRetainOnSuccess()
    {
        return retainOnSuccess;
    }

    public String getState()
    {
        return state;
    }

    public Boolean getSucceeded()
    {
        return succeeded;
    }

    public String getTransactionType()
    {
        return transactionType;
    }

}
