package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ERROR;
import static spreedly.client.java.model.Fields.ERRORS;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = ERRORS)
public class Errors
{

    @ElementList(name = ERROR, inline = true)
    private final List<Error> errors;

    public Errors(@ElementList(name = ERROR) List<Error> errors)
    {
        this.errors = errors;
    }

    public List<Error> getErrors()
    {
        return errors;
    }

    /**
     * Convenience helper to get a single error when only one error is expected to exist.
     * 
     * @return the first error in the errors list it it's not empty, null otherwise
     */
    public Error getFirstError()
    {
        if (errors != null && !errors.isEmpty())
        {
            return errors.get(0);
        }
        else
        {
            return null;
        }
    }

}
