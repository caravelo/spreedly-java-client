package spreedly.client.java;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils
{

    public static String streamToString(InputStream is)
    {
        try (java.util.Scanner s = new java.util.Scanner(is))
        {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }

    public static InputStream stringToStream(String s)
    {
        InputStream is = new ByteArrayInputStream(
                s.getBytes(StandardCharsets.UTF_8));
        return is;
    }

}
