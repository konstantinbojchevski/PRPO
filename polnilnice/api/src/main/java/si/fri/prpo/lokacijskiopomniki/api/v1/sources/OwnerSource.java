package si.fri.prpo.lokacijskiopomniki.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import si.fri.prpo.lokacijskiopomniki.Owner;
import si.fri.prpo.lokacijskiopomniki.storitve.OwnerBean;
import si.fri.prpo.lokacijskiopomniki.storitve.OwnerDTO;
import si.fri.prpo.lokacijskiopomniki.storitve.UsersManagementBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnerSource {

    @Inject
    private OwnerBean ownerBean;

    @Inject
    private UsersManagementBean umb;

    @Context
    protected UriInfo uriInfo;

    @Schema(description = "return all of the owners")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get owners list", description = "Returns a list of owners.")
    @APIResponses({
            @APIResponse(description = "List of owners", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class, type=SchemaType.ARRAY)))
    })
    @GET
    public Response returnOwners(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Owner> owners = ownerBean.getOwners(query);
        Long ownersCount = ownerBean.getOwnersCount(query);

        return Response.ok(owners).header("X-Total=Count", ownersCount).build();
    }

    @Schema(description = "add owner")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Adds a new owner", description = "add owner")
    @APIResponses({
            @APIResponse(description = "List of owners", responseCode = "204")
    })

    @POST
    public Response addOwner(OwnerDTO owner){
        umb.addOwner(owner);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "edit an owner")
    @SecurityRequirement(name = "none")
    @Operation(summary = "edit owner", description = "edit owner")
    @APIResponses({
            @APIResponse(description = "Edits a owner", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Owner.class)))
    })

    @Path("{id}")
    @PUT
    public Response editOwner(@PathParam("id") Integer id, Owner owner){
        ownerBean.updateOwner(id,owner);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "delete an owner")
    @SecurityRequirement(name = "none")
    @Operation(summary = "deletes owner", description = "delete owner")
    @APIResponses({
            @APIResponse(description = "Deletes an owner", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Owner.class)))
    })
    @Path("{id}")
    @DELETE
    public Response deleteOwner(@PathParam("id") Integer id, Owner owner) {
        boolean odstranjen=ownerBean.deleteOwner(id, owner);
        if(odstranjen){
            return Response.status(Response.Status.GONE).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
