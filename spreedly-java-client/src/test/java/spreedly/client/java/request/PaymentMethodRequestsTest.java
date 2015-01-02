package spreedly.client.java.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import spreedly.client.java.Credentials;
import spreedly.client.java.model.PaymentMethod;
import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

public class PaymentMethodRequestsTest
{

    private static final Credentials AUTH = 
            new Credentials("test", "test");

    @Rule public Recorder recorder = new Recorder();

    @Betamax(tape = "show-payment-method")
    @Test
    public void testShowPaymentMethodShouldWork() throws Exception
    {
        // Given
        String token = "D9Smhdu9z5ijCc0RjAQIjOJ9MOy";

        // When
        PaymentMethod paymentMethod = PaymentMethodRequests.show(token, AUTH);

        // Then
        assertNotNull(paymentMethod);
        assertEquals("D9Smhdu9z5ijCc0RjAQIjOJ9MOy", paymentMethod.getToken());
        assertEquals("visa", paymentMethod.getCardType());
        assertEquals("4242", paymentMethod.getLastFourDigits());
        assertEquals("Test", paymentMethod.getFirstName());
        assertEquals("1417080633043", paymentMethod.getLastName());
        assertEquals("credit_card", paymentMethod.getPaymentMethodType());
        assertTrue(1 == paymentMethod.getMonth());
        assertTrue(2016 == paymentMethod.getYear());
    }

}
