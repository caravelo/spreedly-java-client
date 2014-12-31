package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.AVS_CODE;
import static spreedly.client.java.model.Fields.AVS_MESSAGE;
import static spreedly.client.java.model.Fields.CANCELLED;
import static spreedly.client.java.model.Fields.CREATED_AT;
import static spreedly.client.java.model.Fields.CVV_CODE;
import static spreedly.client.java.model.Fields.CVV_MESSAGE;
import static spreedly.client.java.model.Fields.ERROR_CODE;
import static spreedly.client.java.model.Fields.ERROR_DETAIL;
import static spreedly.client.java.model.Fields.MESSAGE;
import static spreedly.client.java.model.Fields.PENDING;
import static spreedly.client.java.model.Fields.SUCCESS;
import static spreedly.client.java.model.Fields.UPDATED_AT;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class Response
{

    @Element(name = SUCCESS)
    private final Boolean success;

    @Element(name = MESSAGE)
    private final String message;

    @Element(name = AVS_CODE, required = false)
    private final String avsCode;

    @Element(name = AVS_MESSAGE, required = false)
    private final String avsMessage;

    @Element(name = CVV_CODE, required = false)
    private final String cvvCode;

    @Element(name = CVV_MESSAGE, required = false)
    private final String cvvMessage;

    @Element(name = PENDING)
    private final Boolean pending;

    @Element(name = ERROR_CODE, required = false)
    private final String errorCode;

    @Element(name = ERROR_DETAIL, required = false)
    private final String errorDetail;

    @Element(name = CANCELLED)
    private final Boolean cancelled;

    @Element(name = CREATED_AT)
    protected final Date createdAt;

    @Element(name = UPDATED_AT)
    protected final String updatedAt;

    public Response(
            @Element(name = SUCCESS) Boolean success,
            @Element(name = MESSAGE) String message,
            @Element(name = AVS_CODE) String avsCode,
            @Element(name = AVS_MESSAGE) String avsMessage,
            @Element(name = CVV_CODE) String cvvCode,
            @Element(name = CVV_MESSAGE) String cvvMessage,
            @Element(name = PENDING) Boolean pending,
            @Element(name = ERROR_CODE) String errorCode,
            @Element(name = ERROR_DETAIL) String errorDetail,
            @Element(name = CANCELLED) Boolean cancelled,
            @Element(name = CREATED_AT) Date createdAt,
            @Element(name = UPDATED_AT) String updatedAt)
    {
        super();
        this.success = success;
        this.message = message;
        this.avsCode = avsCode;
        this.avsMessage = avsMessage;
        this.cvvCode = cvvCode;
        this.cvvMessage = cvvMessage;
        this.pending = pending;
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
        this.cancelled = cancelled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getAvsCode()
    {
        return avsCode;
    }

    public String getAvsMessage()
    {
        return avsMessage;
    }

    public Boolean getCancelled()
    {
        return cancelled;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getCvvCode()
    {
        return cvvCode;
    }

    public String getCvvMessage()
    {
        return cvvMessage;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public String getErrorDetail()
    {
        return errorDetail;
    }

    public String getMessage()
    {
        return message;
    }

    public Boolean getPending()
    {
        return pending;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

}
