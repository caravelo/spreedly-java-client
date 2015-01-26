package spreedly.client.java.xml;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.model.RequestParameters;

public class SimpleXmlParserTest
{
    private SimpleXmlParser parser;

    @Before
    public void setUp()
    {
        parser = new SimpleXmlParser();
    }

    @Test
    public void testParseTransaction()
    {
        // TODO: to be completed
    }

    @Test
    public void testParsePaymentMethod()
    {
        // TODO: to be completed
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
