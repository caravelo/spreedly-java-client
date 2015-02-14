package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ERROR;
import static spreedly.client.java.model.Fields.ERRORS;

import java.util.Collection;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = ERRORS)
public class Errors
{

    @ElementList(name = ERROR, inline = true)
    private final Collection<Error> errors;

    public Errors(@ElementList(name = ERROR) Collection<Error> errors)
    {
        this.errors = errors;
    }

    public Collection<Error> getErrors()
    {
        return errors;
    }

    /**
     * Convenience helper to get a single error when only one error is expected to exist.
     * 
     * @return
     */
    public Error getError()
    {
        if (errors != null && errors.size() > 0)
        {
            return errors.iterator().next();
        }
        else
        {
            return null;
        }
    }

}
