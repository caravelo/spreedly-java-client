package spreedly.client.java.xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.xml.support.ISO8601DateTransformer;

public class SimpleXmlParser implements XmlParser
{

    private final Serializer serializer;

    public SimpleXmlParser()
    {
        RegistryMatcher m = new RegistryMatcher();
        m.bind(Date.class, new ISO8601DateTransformer());

        serializer = new Persister(m);
    }

    @Override
    public PaymentMethod parsePaymentMethod(InputStream source) throws XmlParserException
    {
        return read(PaymentMethod.class, source);
    }

    @Override
    public Transaction parseTransaction(InputStream source) throws XmlParserException
    {
        return read(Transaction.class, source);
    }

    @Override
    public void serialize(Object source, OutputStream out) throws XmlParserException
    {
        try
        {
            serializer.write(source, out);
        }
        catch (Exception e)
        {
            // TODO: log and set appropriate message to raised exception
            throw new XmlParserException(e);
        }
    }

    private <T> T read(Class<T> type, InputStream source) throws XmlParserException
    {
        try
        {
            return serializer.read(type, source, false); // false = non-strict mode
        }
        catch (Exception e)
        {
            // TODO: log and set appropriate message to raised exception
            throw new XmlParserException(e);
        }
    }
}
