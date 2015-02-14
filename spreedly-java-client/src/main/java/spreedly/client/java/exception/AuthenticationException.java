package spreedly.client.java.exception;

/**
 * Thrown when any authentication credentials are missing or failed authentication
 * as per wrong credentials provided. 
 * 
 * @author Alejandro Garcia Seco
 *
 */
public class AuthenticationException extends SpreedlyClientException
{

    /**
     * 
     */
    private static final long serialVersionUID = 2094595311823575090L;

    public AuthenticationException(String message)
    {
        super(message);
    }
}
