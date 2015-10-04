package spreedly.client.java.xml.support;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import spreedly.client.java.model.PaymentMethod;

/**
 * Supports and allows the deserialisation of list of payment methods.
 * 
 * @author Alejandro Garcia Seco
 *
 */
@Root
public class PaymentMethods
{

    @ElementList(inline = true)
    private List<PaymentMethod> paymentMethods;

    public List<PaymentMethod> getPaymentMethods()
    {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods)
    {
        this.paymentMethods = paymentMethods;
    }
    
}
