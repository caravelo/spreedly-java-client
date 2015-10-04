package spreedly.client.java.xml.support;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

public class ISO8601DateTransformerTest
{

    @Test
    public void testReadWithShouldWork() throws Exception
    {
        // Given
        ISO8601DateTransformer t = new ISO8601DateTransformer();
        String sDate = "2014-11-28T22:25:26+00:00";

        // When
        Date dDate = t.read(sDate);

        // Then
        assertNotNull(dDate);
    }

    @Test
    public void testWriteWithShouldWork() throws Exception
    {
        // Given
        ISO8601DateTransformer t = new ISO8601DateTransformer();
        Date dDate = new Date();

        // When
        String sDate = t.write(dDate);

        // Then
        assertNotNull(sDate);
    }

}
