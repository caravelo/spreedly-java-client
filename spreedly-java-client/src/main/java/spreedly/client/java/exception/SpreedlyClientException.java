package spreedly.client.java.exception;

public class SpreedlyClientException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -3610600381275752502L;

    public SpreedlyClientException()
    {
        super();
    }

    public SpreedlyClientException(Throwable cause)
    {
        super(cause);
    }

    public SpreedlyClientException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
