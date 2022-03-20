package si.fri.prpo.polnilnice.contactInfo.api.v1.sources;

import si.fri.prpo.polnilnice.contactInfo.api.v1.Contact;
import si.fri.prpo.polnilnice.contactInfo.storitve.Phonechecker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Path("contactInfo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped

public class ContactInfoSource {

    @Inject
    Phonechecker phonechecker;

    private Logger log = Logger.getLogger(ContactInfoSource.class.getName());

    private Map<Integer, String> phones;

    @PostConstruct
    public void init(){
        phones = new HashMap<>();
        phones.put(1, "041-566-356");
        phones.put(2, "070-543-532");
    }

    @Path("{id}")
    @GET
    public Response getPhone(@PathParam("id") Integer id){
        String phone = phones.get(id);
        return Response.ok(phone).build();
    }

    @POST
    public Response addPhone(Contact c) throws IOException, InterruptedException{
        if (phonechecker.f(c.getPhone())){
            phones.put(c.getId(), c.getPhone());
            log.info("Telefon dodan!");
            return Response.ok().build();
        }
        else {
            log.info("Could not add phone");
            return Response.noContent().build();
        }
    }
}
