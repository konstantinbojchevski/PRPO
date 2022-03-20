package si.fri.prpo.polnilnice.ocene.api.v1.sources;

import si.fri.prpo.polnilnice.ocene.api.v1.Ocena;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Path("ocene")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped

public class OceneSource {

    private Logger log = Logger.getLogger(OceneSource.class.getName());

    private Map<Integer, ArrayList<Integer>> ocene;

    @PostConstruct
    public void init(){
        ocene = new HashMap<>();
        ArrayList<Integer> ocena = new ArrayList<>();
        ocena.add(5);
        ocene.put(1, ocena);
        ocene.put(2, ocena);
    }

    @GET
    public Response getAverage(Ocena ocena){
     ArrayList<Integer> oceneZaUser = ocene.get(ocena.getId());
     int sum = 0;
     for(Integer i: oceneZaUser){
         sum+= i;
     }
     int avg = sum/oceneZaUser.size();
     return Response.ok(avg).build();
    }

    @POST
    public Response dodajOcena(Ocena ocena){

        if(!ocene.containsKey(ocena.getId())){
            ArrayList<Integer> oceneZaUser = new ArrayList<>();
            oceneZaUser.add(ocena.getOcena());
        }
        else{
            ocene.get(ocena.getId()).add(ocena.getOcena());
        }

        log.info("Ocena dodana!");

        return Response.ok().build();
    }
}
