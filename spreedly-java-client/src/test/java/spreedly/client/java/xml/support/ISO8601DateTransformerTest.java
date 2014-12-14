package spreedly.client.java.xml.support;

import java.util.Date;

import org.junit.Assert;
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
        Assert.assertNotNull(dDate);
    }

    // TODO: test write
//    @Test
//    public void testWriteWithShouldWork() throws Exception
//    {
//        
//    }

}
