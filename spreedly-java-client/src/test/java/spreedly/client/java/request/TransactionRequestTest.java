package spreedly.client.java.request;

import org.junit.Rule;
import org.junit.Test;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import spreedly.client.java.Credentials;

public class TransactionRequestTest
{

    private static final Credentials AUTH = 
            new Credentials("test", "test");

    @Rule public Recorder recorder = new Recorder();

    @Betamax(tape = "show-transaction")
    @Test
    public void testShow() throws Exception
    {
        // Given
        String token = "Jsa3OQ6vkevAUpzwfQg4CHO8EdS";

        // When
        TransactionRequest.show(token, AUTH);

        // Then
        // TODO: assert fields values
    }

}
