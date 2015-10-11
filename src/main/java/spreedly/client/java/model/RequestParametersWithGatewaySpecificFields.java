package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.GATEWAY_SPECIFIC_FIELDS;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction")
public class RequestParametersWithGatewaySpecificFields extends
        RequestParameters
{

    @Element(name = GATEWAY_SPECIFIC_FIELDS, required = false)
    private final GatewaySpecificFields gatewaySpecificFields;

    public RequestParametersWithGatewaySpecificFields(
            Map<String, String> options,
            String gatewayType,
            Map<String, String> specificFieldsMap)
    {
        super(options);

        GatewaySpecificFields specificFields = new GatewaySpecificFields(gatewayType, specificFieldsMap);
        this.gatewaySpecificFields = specificFields;
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public GatewaySpecificFields getGatewaySpecificFields()
    {
        return gatewaySpecificFields;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "RequestParametersWithGatewaySpecificFields [" + super.toString()
                + "gatewaySpecificFields=" + gatewaySpecificFields + "]";
    }

}
