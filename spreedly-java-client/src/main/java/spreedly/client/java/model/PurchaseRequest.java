package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.AMOUNT;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = BaseGatewayRequest.ROOT_NAME)
public class PurchaseRequest extends BaseGatewayRequest
{

    @Element(name = AMOUNT)
    private final String amount;

    public PurchaseRequest(Map<String, String> options)
    {
        super(options);
        this.amount = options.get(AMOUNT);
    }

}
