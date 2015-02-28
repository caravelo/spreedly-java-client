package spreedly.client.java.xml.support;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.simpleframework.xml.transform.Transform;

public class ISO8601DateTransformer implements Transform<Date>
{

    // NOTE: DateFormat is not used since JDK7 still doesn't have full ISO 8601 support (http://stackoverflow.com/a/2202300/1514315)

    @Override
    public Date read(String value) throws Exception
    {
        return javax.xml.bind.DatatypeConverter.parseDateTime(value).getTime();
    }

    @Override
    public String write(Date value) throws Exception
    {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(value);
        return javax.xml.bind.DatatypeConverter.printDateTime(c);
    }

}
