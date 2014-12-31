package spreedly.client.java.xml;

import java.io.IOException;
import java.io.OutputStream;

import spreedly.client.java.exception.XmlParserException;
import spreedly.client.java.http.OutputSource;

public class XmlOutputSource implements OutputSource
{

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
            // XXX: improve exception handling
            e.printStackTrace();
        }
    }

}
