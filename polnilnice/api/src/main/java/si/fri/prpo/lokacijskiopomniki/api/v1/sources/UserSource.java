package si.fri.prpo.lokacijskiopomniki.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import si.fri.prpo.lokacijskiopomniki.User;
import si.fri.prpo.lokacijskiopomniki.storitve.UserBean;
import si.fri.prpo.lokacijskiopomniki.storitve.UserDTO;
import si.fri.prpo.lokacijskiopomniki.storitve.UsersManagementBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserSource {

    @Inject
    private UserBean userbean;

    @Inject
    private UsersManagementBean umb;

    @Context
    protected UriInfo uriInfo;

    //Uporabljamo vsaj 5 OpenApi anotacij: @Schema, @Operation, @ApiResponse, @Content, @SecurityRequirement
    @Schema(description = "return all of the users")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get customers list", description = "Returns a list of users.")
    @APIResponses({
            @APIResponse(description = "List of users", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class, type=SchemaType.ARRAY)))
    })
    @GET
    public Response returnUsers(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<User> users = userbean.getUsers(query);
        Long usersCount = userbean.getUsersCount(query);

        return Response.ok(users).header("X-Total=Count", usersCount).build();
    }

    @Schema(description = "add user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Adds a new user", description = "add user")
    @APIResponses({
            @APIResponse(description = "List of users", responseCode = "204")
    })

    @POST
    public Response addUser(UserDTO user){
        umb.addUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "edit a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "edit user", description = "edit user")
    @APIResponses({
            @APIResponse(description = "Edits a user", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    User.class)))
    })

    @Path("{id}")
    @PUT
    public Response editUser(@PathParam("id") Integer id, User user){
        userbean.updateUser(id,user);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "delete a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "deletes user", description = "delete user")
    @APIResponses({
            @APIResponse(description = "Deletes a user", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    User.class)))
    })
    @Path("{id}")
    @DELETE
    public Response deleteUser(@PathParam("id") Integer id, User user) {
        boolean odstranjen=userbean.deleteUser(id, user);
        if(odstranjen){
            return Response.status(Response.Status.GONE).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
