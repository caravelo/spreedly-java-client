package spreedly.client.java.exception;

/**
 * Any response with status code 4xx or 5xx.
 * 
 * @author Alejandro Garcia Seco
 *
 */
public class UnsuccessfulHttpResponseException extends SpreedlyClientException
{

    /**
     * 
     */
    private static final long serialVersionUID = 2094595311823575090L;

    private final int statusCode;

    public UnsuccessfulHttpResponseException(int statusCode)
    {
        super();
        this.statusCode = statusCode;
    }

    public UnsuccessfulHttpResponseException(String message, int statusCode)
    {
        super();
        this.statusCode = statusCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

}
