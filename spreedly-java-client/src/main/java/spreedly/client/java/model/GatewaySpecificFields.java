package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.GATEWAY_SPECIFIC_FIELDS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import spreedly.client.java.model.GatewaySpecificFields.GatewaySpecificFieldsConverter;

@Root(name = GATEWAY_SPECIFIC_FIELDS)
@Convert(GatewaySpecificFieldsConverter.class)
public class GatewaySpecificFields
{

    private final String gatewayType;
    private final Map<String, String> specificFields;

    public GatewaySpecificFields(String gatewayType, Map<String, String> specificFieldsMap)
    {
        this.gatewayType = gatewayType;
        this.specificFields = specificFieldsMap;
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
            if (node == null)
            {
                return null;
            }

            // Gateway type node (e.g. "stripe")
            InputNode gatewayTypeNode = node.getNext();

            if (gatewayTypeNode == null)
            {
                return null;
            }

            String gatewayType = gatewayTypeNode.getName();

            Map<String, String> fieldsMap = new HashMap<String, String>();

            // Loop through gateway's specific fields
            InputNode fieldNode;
            while ((fieldNode = gatewayTypeNode.getNext()) != null)
            {
                String fieldName = fieldNode.getName();
                String fieldValue = fieldNode.getValue();
                fieldsMap.put(fieldName, fieldValue);
            }

            return new GatewaySpecificFields(gatewayType, fieldsMap);
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
}
