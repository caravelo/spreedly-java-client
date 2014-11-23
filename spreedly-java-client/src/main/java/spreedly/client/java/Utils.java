package spreedly.client.java;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utils
 *
 */
public class Utils {

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // Ignore.
            }
        }
    }

    public static String convertStreamToString(java.io.InputStream is) {
        Scanner s = null;

        try {
            s = new Scanner(is);
            s.useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
		} finally {
			s.close();
		}
    }

}
