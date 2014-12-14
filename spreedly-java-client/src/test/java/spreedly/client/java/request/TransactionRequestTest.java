package spreedly.client.java.request;

import org.junit.Test;

import spreedly.client.java.Credentials;

public class TransactionRequestTest
{
    // TODO: actual request must be mocked! Use Betamax or something like this?

    private static final Credentials AUTH = 
            new Credentials("IG8KIz0eOF4ECwpVtiRJDE0XBCD", "1ahpbV0VNUy55A3dBRVRT8EpXMpFJLZeoS4pjzu4wiGL5qfNLWyemoF6kjc7wPU3");

    @Test
    public void testShow() throws Exception
    {
        // Given
        String token = "Jsa3OQ6vkevAUpzwfQg4CHO8EdS";

        TransactionRequest.show(token, AUTH);
    }

}
