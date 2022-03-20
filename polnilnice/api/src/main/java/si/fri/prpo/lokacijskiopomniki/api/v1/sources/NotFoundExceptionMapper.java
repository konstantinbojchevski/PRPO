package si.fri.prpo.lokacijskiopomniki.api.v1.sources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {

        Error error = new Error();
        error.setStatus(404);
        error.setCode("Resource not found.");
        error.setMessage(e.getMessage());

        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}