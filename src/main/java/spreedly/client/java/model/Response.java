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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Response other = (Response) obj;
        if (avsCode == null) {
            if (other.avsCode != null) return false;
        } else if (!avsCode.equals(other.avsCode)) return false;
        if (avsMessage == null) {
            if (other.avsMessage != null) return false;
        } else if (!avsMessage.equals(other.avsMessage)) return false;
        if (cancelled == null) {
            if (other.cancelled != null) return false;
        } else if (!cancelled.equals(other.cancelled)) return false;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (cvvCode == null) {
            if (other.cvvCode != null) return false;
        } else if (!cvvCode.equals(other.cvvCode)) return false;
        if (cvvMessage == null) {
            if (other.cvvMessage != null) return false;
        } else if (!cvvMessage.equals(other.cvvMessage)) return false;
        if (errorCode == null) {
            if (other.errorCode != null) return false;
        } else if (!errorCode.equals(other.errorCode)) return false;
        if (errorDetail == null) {
            if (other.errorDetail != null) return false;
        } else if (!errorDetail.equals(other.errorDetail)) return false;
        if (message == null) {
            if (other.message != null) return false;
        } else if (!message.equals(other.message)) return false;
        if (pending == null) {
            if (other.pending != null) return false;
        } else if (!pending.equals(other.pending)) return false;
        if (success == null) {
            if (other.success != null) return false;
        } else if (!success.equals(other.success)) return false;
        if (updatedAt == null) {
            if (other.updatedAt != null) return false;
        } else if (!updatedAt.equals(other.updatedAt)) return false;
        return true;
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((avsCode == null) ? 0 : avsCode.hashCode());
        result = prime * result + ((avsMessage == null) ? 0 : avsMessage.hashCode());
        result = prime * result + ((cancelled == null) ? 0 : cancelled.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((cvvCode == null) ? 0 : cvvCode.hashCode());
        result = prime * result + ((cvvMessage == null) ? 0 : cvvMessage.hashCode());
        result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
        result = prime * result + ((errorDetail == null) ? 0 : errorDetail.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((pending == null) ? 0 : pending.hashCode());
        result = prime * result + ((success == null) ? 0 : success.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Response [success=" + success + ", message=" + message + ", avsCode=" + avsCode + ", avsMessage="
                + avsMessage + ", cvvCode=" + cvvCode + ", cvvMessage=" + cvvMessage + ", pending=" + pending
                + ", errorCode=" + errorCode + ", errorDetail=" + errorDetail + ", cancelled=" + cancelled
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
