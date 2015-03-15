package spreedly.client.java.model;

import java.util.Iterator;
import java.util.Map;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import spreedly.client.java.model.GatewaySpecificFields.GatewaySpecificFieldsConverter;

@Root(name = "gateway_specific_fields")
@Convert(GatewaySpecificFieldsConverter.class)
public class GatewaySpecificFields
{

    private final String gatewayType;
    private final Map<String, String> specificFields;

    public GatewaySpecificFields(String gatewayType, Map<String, String> specificFields)
    {
        this.gatewayType = gatewayType;
        this.specificFields = specificFields;
    }

    public String getGatewayType()
    {
        return gatewayType;
    }

    public Map<String, String> getSpecificFields()
    {
        return specificFields;
    }

    /**
     * Custom converter for serializing the <code>gateway_specific_fields</code> node.
     * De-serialization is not supported so far.
     * 
     * @author Alejandro Garcia Seco
     *
     */
    static class GatewaySpecificFieldsConverter implements Converter<GatewaySpecificFields>
    {
        @Override
        public GatewaySpecificFields read(InputNode node) throws Exception
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void write(OutputNode node, GatewaySpecificFields value) throws Exception
        {
            OutputNode child = node.getChild(value.getGatewayType());

            Map<String, String> specificFields = value.getSpecificFields();
            for (Iterator<String> keysIterator = specificFields.keySet().iterator(); keysIterator.hasNext();)
            {
                String key = keysIterator.next();
                OutputNode fieldNode = child.getChild(key);
                fieldNode.setValue(specificFields.get(key));
            }
        }
    }

//    static class GatewayType
//    {
//        @Element
//        private final String gatewayType;
//
//        @ElementMap(entry = "property", key = "key", attribute = true, inline = true)
//        @Path(value = "stripe")
//        private final Map<String, String> specificFields;
//
//        public GatewayType(String gatewayType, Map<String, String> specificFields)
//        {
//            this.gatewayType = gatewayType;
//            this.specificFields = specificFields;
//        }
//
//        public String getGatewayType()
//        {
//            return gatewayType;
//        }
//
//        static class GatewayTypeConverter implements Converter<GatewayType>
//        {
//            @Override
//            public GatewayType read(InputNode node) throws Exception
//            {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void write(OutputNode node, GatewayType value) throws Exception
//            {
//                node.setName(value.getGatewayType());
//                node.getChild("blabla");
//            }
//        }
//    }
}
