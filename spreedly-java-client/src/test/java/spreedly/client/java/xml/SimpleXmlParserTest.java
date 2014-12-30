package spreedly.client.java.xml;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.CURRENCY_CODE;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.DESCRIPTION;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.EMAIL;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.IP;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.MERCHANT_LOCATION_DESCRIPTOR;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.MERCHANT_NAME_DESCRIPTOR;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.ORDER_ID;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.PAYMENT_METHOD_TOKEN;
import static spreedly.client.java.model.VerifyPaymentMethodRequest.RETAIN_ON_SUCCESS;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import spreedly.client.java.model.VerifyPaymentMethodRequest;

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
    public void testSerializeVerifyPaymentMethodRequest() throws Exception
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

        VerifyPaymentMethodRequest request = new VerifyPaymentMethodRequest(options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // When
        parser.serialize(request, baos);

        // Then
        String xml = new String(baos.toByteArray());
        String expectedXml = getXmlFileAsString("src/test/resources/xml/verify-payment-method-request.xml");

        assertXMLEqual(expectedXml, xml);
    }

    // TODO: extract into TestsUtils or similar
    private static String getXmlFileAsString(String fileName) throws Exception
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        InputStream inputStream = new FileInputStream(new File(fileName));
        Document doc = documentBuilderFactory.newDocumentBuilder().parse(inputStream);

        StringWriter stw = new StringWriter();

        Transformer serializer = TransformerFactory.newInstance().newTransformer();
        serializer.transform(new DOMSource(doc), new StreamResult(stw));

        return stw.toString();
    }
}
