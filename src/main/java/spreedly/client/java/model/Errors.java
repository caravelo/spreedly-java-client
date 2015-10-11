package spreedly.client.java.model;

import static java.lang.String.format;
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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Errors other = (Errors) obj;
        if (errors == null) {
            if (other.errors != null) return false;
        } else if (!errors.equals(other.errors)) return false;
        return true;
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
    public Error getSingleError()
    {
        if (errors != null && !errors.isEmpty())
        {
            int size = errors.size();
            if (size > 1)
            {
                String message = format("Expected single error but found [%s]", size);
                throw new RuntimeException(message);
            }

            return errors.get(0);
        }
        else
        {
            return Error.EMPTY;
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((errors == null) ? 0 : errors.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Errors [errors=" + errors + "]";
    }

}
