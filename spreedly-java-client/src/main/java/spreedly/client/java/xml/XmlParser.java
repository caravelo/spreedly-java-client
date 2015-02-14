package spreedly.client.java.xml;

import java.io.InputStream;
import java.io.OutputStream;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.model.Errors;
import spreedly.client.java.model.PaymentMethod;
import spreedly.client.java.model.Transaction;

public interface XmlParser
{

    Errors parseErrors(InputStream source) throws XmlParserException;

    PaymentMethod parsePaymentMethod(InputStream source) throws XmlParserException;

    Transaction parseTransaction(InputStream source) throws XmlParserException;

    void serialize(Object source, OutputStream out) throws XmlParserException;

}