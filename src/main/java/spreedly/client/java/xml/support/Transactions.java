package spreedly.client.java.xml.support;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import spreedly.client.java.model.Transaction;

/**
 * Supports and allows the deserialisation of a transactions list.
 * 
 * @author Alejandro Garcia Seco
 *
 */
@Root
public class Transactions
{

    @ElementList(inline = true)
    private List<Transaction> transactions;

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void getTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }
    
}
