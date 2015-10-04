package spreedly.client.java.http;

import spreedly.client.java.exception.HttpHandlingException;

/**
 * Interface which provides an abstraction around making HTTP requests.
 *
 * @author Kevin Litwack (kevin@kevinlitwack.com)
 */
public interface HttpHandler
{

    /**
     * Executes the given request and returns the received response.
     *
     * @param request
     * @throws spreedly.client.java.exception.HttpHandlingException If there is an error executing the request or processing the
     * response.
     */
    Response execute(Request request) throws HttpHandlingException;

}