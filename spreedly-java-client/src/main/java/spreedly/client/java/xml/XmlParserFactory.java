package spreedly.client.java.xml;

public class XmlParserFactory
{

    private XmlParserFactory(){}

    public static XmlParser getXmlParser()
    {
        return new SimpleXmlParser();
    }

}
