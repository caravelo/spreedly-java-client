package spreedly.client.java.http;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class BasicHttpAuthTest
{

    @Test
    public void testGetEncodedAuthStringShouldWork() throws UnsupportedEncodingException
    {
        // Given
        String username = "username";
        String password = "password";
        BasicHttpAuth bha = new BasicHttpAuth(username, password);

        // When
        String encodedAuthString = bha.getAuthString();

        // Then
        Assert.assertNotNull(encodedAuthString);
        Assert.assertEquals("Basic dXNlcm5hbWU6cGFzc3dvcmQ=", encodedAuthString);
    }
}
