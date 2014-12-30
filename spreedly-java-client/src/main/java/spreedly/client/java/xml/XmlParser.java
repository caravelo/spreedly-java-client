package spreedly.client.java.xml;

import java.io.OutputStream;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;

public interface XmlParser
{

    Transaction parseTransaction(String source) throws XmlParserException;

    PaymentMethod parsePaymentMethod(String source) throws XmlParserException;

    void serialize(Object source, OutputStream out) throws XmlParserException;

}