package spreedly.client.java.xml;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static spreedly.client.java.model.Fields.CURRENCY_CODE;
import static spreedly.client.java.model.Fields.DESCRIPTION;
import static spreedly.client.java.model.Fields.EMAIL;
import static spreedly.client.java.model.Fields.IP;
import static spreedly.client.java.model.Fields.MERCHANT_LOCATION_DESCRIPTOR;
import static spreedly.client.java.model.Fields.MERCHANT_NAME_DESCRIPTOR;
import static spreedly.client.java.model.Fields.ORDER_ID;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.Fields.RETAIN_ON_SUCCESS;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.model.Error;
import spreedly.client.java.model.Errors;
import spreedly.client.java.model.RequestParameters;
import spreedly.client.java.model.GatewaySpecificFields;
import spreedly.client.java.model.Transaction;

public class SimpleXmlParserTest
{
    private SimpleXmlParser parser;

    @Before
    public void setUp()
    {
        parser = new SimpleXmlParser();
    }

    @Test
    public void testParseErrors() throws FileNotFoundException, XmlParserException
    {
        // Given
        String fileName = "src/test/resources/xml/errors.xml";
        InputStream targetStream = new FileInputStream(fileName);

        // When
        Errors errors = parser.parseErrors(targetStream);

        // Then
        assertNotNull(errors);
        assertNotNull(errors.getErrors());
        assertTrue(errors.getErrors().size() == 1);
        Error e = errors.getErrors().iterator().next();
        assertEquals("errors.gateway_gateway_type_cannot_be_changed", e.getKey());
        assertEquals("You may not change the gateway_type of a gateway.", e.getMessage());
    }

    @Test
    public void testParseGatewaySpecificFields() throws FileNotFoundException, XmlParserException
    {
        // Given
        String fileName = "src/test/resources/xml/stripe-specific-fields.xml";
        InputStream targetStream = new FileInputStream(fileName);

        // When
        GatewaySpecificFields fields = parser.parseGatewaySpecificFields(targetStream);

        // Then
        assertNotNull(fields);
        assertEquals("stripe", fields.getGatewayType());
        Map<String, String> fieldsMap = fields.getSpecificFields();
        assertEquals("5K Race Ticket", fieldsMap.get("statement_description"));
    }

    @Test
    public void testParsePaymentMethod()
    {
        // TODO: to be completed
    }

    @Test
    public void testParseTransaction() throws FileNotFoundException, XmlParserException
    {
     // TODO: to be completed
    }

    @Test
    public void testParseTransactionWithGatewaySpecificFields() throws FileNotFoundException, XmlParserException
    {
        // Given
        String fileName = "src/test/resources/xml/transaction-with-gateway-specific-fields.xml";
        InputStream targetStream = new FileInputStream(fileName);

        // When
        Transaction t = parser.parseTransaction(targetStream);

        // Then
        assertNotNull(t);
        GatewaySpecificFields gatewaySpecificFields = t.getGatewaySpecificFields();
        assertEquals("stripe", gatewaySpecificFields.getGatewayType());
        Map<String, String> fieldsMap = gatewaySpecificFields.getSpecificFields();
        assertEquals("5K Race Ticket", fieldsMap.get("statement_description"));
    }

    @Test
    public void testSerializeGatewaySpecificFields() throws XmlParserException, SAXException, IOException
    {
        // Given
        Map<String, String> options = new HashMap<String, String>();
        options.put("statement_description", "5K Race Ticket");

        GatewaySpecificFields p = new GatewaySpecificFields("stripe", options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // When
        parser.serialize(p, baos);

        // Then
        Reader xml = new StringReader(baos.toString());
        Reader expectedXml = new FileReader("src/test/resources/xml/stripe-specific-fields.xml");

        assertXMLEqual(expectedXml, xml);
    }

    @Test
    public void testSerializeVerifyPaymentMethodRequest() throws XmlParserException, SAXException, IOException
    {
        // Given
        Map<String, String> options = new HashMap<String, String>();
        options.put(PAYMENT_METHOD_TOKEN, "PPxU2Cl4mIDD7KdHZEHLGxYJfTj");
        options.put(RETAIN_ON_SUCCESS, "true");
        options.put(CURRENCY_CODE, "EUR");
        options.put(ORDER_ID, "Order ID");
        options.put(DESCRIPTION, "Description");
        options.put(IP, "192.168.1.10");
        options.put(EMAIL, "email@example.com");
        options.put(MERCHANT_NAME_DESCRIPTOR, "Descriptor name");
        options.put(MERCHANT_LOCATION_DESCRIPTOR, "Descriptor location");

        RequestParameters request = new RequestParameters(options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // When
        parser.serialize(request, baos);

        // Then
        Reader xml = new StringReader(baos.toString());
        Reader expectedXml = new FileReader("src/test/resources/xml/verify-payment-method-request.xml");

        assertXMLEqual(expectedXml, xml);
    }

}
