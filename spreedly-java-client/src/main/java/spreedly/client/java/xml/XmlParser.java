package spreedly.client.java.xml;

import spreedly.client.java.model.Transaction;

public interface XmlParser
{

    Transaction parseTransaction(String source) throws Exception;

}