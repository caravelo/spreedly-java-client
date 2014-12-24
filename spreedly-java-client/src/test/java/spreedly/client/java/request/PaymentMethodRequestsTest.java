package spreedly.client.java.request;

import org.junit.Rule;
import org.junit.Test;

import spreedly.client.java.Credentials;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class PaymentMethodRequestsTest
{

    private static final Credentials AUTH = 
            new Credentials("test", "test");

    @Rule public Recorder recorder = new Recorder();

    @Betamax(tape = "show-payment-method")
    @Test
    public void testShow() throws Exception
    {
        // Given
        String token = "D9Smhdu9z5ijCc0RjAQIjOJ9MOy";

        // When
        PaymentMethodRequests.show(token, AUTH);

        // Then
        // TODO: assert fields values
    }

}
