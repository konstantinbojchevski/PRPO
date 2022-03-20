package si.fri.prpo.lokacijskiopomniki.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import si.fri.prpo.lokacijskiopomniki.Charger;
import si.fri.prpo.lokacijskiopomniki.storitve.ChargerBean;
import si.fri.prpo.lokacijskiopomniki.storitve.ChargerDTO;
import si.fri.prpo.lokacijskiopomniki.storitve.UsersManagementBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("chargers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChargerSource {

    @Inject
    private ChargerBean chargerBean;

    @Inject
    private UsersManagementBean umb;

    @Context
    protected UriInfo uriInfo;

    @Schema(description = "return all of the chargers")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get chargers list", description = "Returns a list of chargers.")
    @APIResponses({
            @APIResponse(description = "List of chargers", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Charger.class, type=SchemaType.ARRAY)))
    })
    @GET
    public Response returnChargers(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Charger> chargers = chargerBean.getChargers(query);
        Long chargersCount = chargerBean.getChargersCount(query);

        return Response.ok(chargers).header("X-Total=Count", chargersCount).build();
    }

    @Schema(description = "add charger")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Adds a new charger", description = "add charger")
    @APIResponses({
            @APIResponse(description = "List of chargers", responseCode = "204")
    })

    @POST
    public Response addCharger(ChargerDTO charger){
        umb.addCharger(charger);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "edit an charger")
    @SecurityRequirement(name = "none")
    @Operation(summary = "edit charger", description = "edit charger")
    @APIResponses({
            @APIResponse(description = "Edits a charger", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Charger.class)))
    })

    @Path("{id}")
    @PUT
    public Response editcharger(@PathParam("id") Integer id, Charger charger){
        chargerBean.updateCharger(id,charger);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "delete an charger")
    @SecurityRequirement(name = "none")
    @Operation(summary = "deletes charger", description = "delete charger")
    @APIResponses({
            @APIResponse(description = "Deletes a charger", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Charger.class)))
    })
    @Path("{id}")
    @DELETE
    public Response deletecharger(@PathParam("id") Integer id, Charger charger) {
        boolean odstranjen=chargerBean.deleteCharger(id, charger);
        if(odstranjen){
            return Response.status(Response.Status.GONE).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
