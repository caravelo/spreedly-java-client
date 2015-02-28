package spreedly.client.java.exception;

import spreedly.client.java.model.Transaction;

/**
 * <p><code>UnproccessableTransactionException</code> is thrown when Spreedly returns HTTP status
 * code 422.
 * </p>
 * 
 * <p>
 * There are two types of unprocessable errors in Spreedly. The first type's response body may contain just
 * an error list. The second type of response body happens when the transaction is created successfully,
 * but then fail to process it. For example, a standard decline from a gateway would return a 422 status.
 * </p>
 * 
 * <p>
 * This exception is thrown only on when it's the second type hence transaction details are available
 * in the exception by calling {@link #getTransactionDetails()}.
 * </p>
 * 
 * @author Alejandro Garcia Seco
 *
 */
public class UnproccessableTransactionException extends SpreedlyClientException
{

    /**
     * 
     */
    private static final long serialVersionUID = -3610600381275752502L;

    private final Transaction transactionDetails;

    public UnproccessableTransactionException(Transaction unprocessableTransaction)
    {
        this.transactionDetails = unprocessableTransaction;
    }

    public String getErrorMessage()
    {
        return transactionDetails.getMessage();
    }

    public String getErrorMessageKey()
    {
        return transactionDetails.getMessageKey();
    }

    /**
     * Same as {@link #getErrorMessage()}
     */
    public String getMessage()
    {
        return getErrorMessage();
    }

    /**
     * 
     * @return
     *      the problematic transaction details
     */
    public Transaction getTransactionDetails()
    {
        return transactionDetails;
    }
    
}
