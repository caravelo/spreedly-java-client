package spreedly.client.java.request;

import static spreedly.client.java.http.Request.POST;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import spreedly.client.java.Credentials;
import spreedly.client.java.exception.HttpHandlingException;
import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.HttpHandler;
import spreedly.client.java.http.HttpHandlerFactory;
import spreedly.client.java.http.OutputSource;
import spreedly.client.java.http.Request;
import spreedly.client.java.http.Response;
import spreedly.client.java.model.Transaction;
import spreedly.client.java.model.VerifyPaymentMethodRequest;
import spreedly.client.java.xml.XmlParser;
import spreedly.client.java.xml.XmlParserFactory;

public class GatewayRequests
{

    public static Transaction verify(String gatewayToken, Map<String, String> options, Credentials credentials) throws XmlParserException, HttpHandlingException
    {
        final VerifyPaymentMethodRequest verifyRequest = new VerifyPaymentMethodRequest(options);

        final XmlParser xmlParser = XmlParserFactory.getXmlParser();

        // XXX: extract to appropriate place
        OutputSource source = new OutputSource()
        {
            @Override
            public void writeTo(OutputStream out) throws IOException
            {
                try
                {
                    xmlParser.serialize(verifyRequest, out);
                }
                catch (XmlParserException e)
                {
                    e.printStackTrace();
                }
            }
        };

        URL url = UrlsBuilder.verifyPaymentMethod(gatewayToken);
        Request request = new Request(url, POST, credentials, source);

        HttpHandler httpHandler = HttpHandlerFactory.getHttpHandler();
        Response response = httpHandler.execute(request);

        return xmlParser.parseTransaction(response.body);
    }

}
