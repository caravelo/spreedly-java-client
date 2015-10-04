package spreedly.client.java.xml;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.OutputSource;

/**
 * Implementation of {@link spreedly.client.java.http.OutputSource} to output
 * XMLs into HTTP body requests.
 * 
 * @author Alejandro Garcia Seco
 *
 */
public class XmlOutputSource implements OutputSource
{

    private static final Logger log = LoggerFactory.getLogger(XmlOutputSource.class);

    private final XmlParser parser;
    private final Object source;

    public XmlOutputSource(XmlParser parser, Object source)
    {
        this.parser = parser;
        this.source = source;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException
    {
        try
        {
            parser.serialize(source, out);
        }
        catch (XmlParserException e)
        {
            String message = "Unparseable XML output source";
            log.error(message);
            throw new IOException(message, e);
        }
    }

}
