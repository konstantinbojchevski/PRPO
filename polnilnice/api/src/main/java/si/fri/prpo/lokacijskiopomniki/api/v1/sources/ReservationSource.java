package si.fri.prpo.lokacijskiopomniki.api.v1.sources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import si.fri.prpo.lokacijskiopomniki.Reservation;
import si.fri.prpo.lokacijskiopomniki.storitve.ReservationBean;
import si.fri.prpo.lokacijskiopomniki.storitve.ReservationDTO;
import si.fri.prpo.lokacijskiopomniki.storitve.UsersManagementBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import com.kumuluz.ee.cors.annotations.CrossOrigin;

@Path("reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, PUT, POST, HEAD, DELETE, OPTIONS")
@ApplicationScoped
public class ReservationSource {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private UsersManagementBean umb;

    @Context
    protected UriInfo uriInfo;

    @Schema(description = "return all of the reservations")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get reservations list", description = "Returns a list of reservations.")
    @APIResponses({
            @APIResponse(description = "List of reservations", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class, type=SchemaType.ARRAY)))
    })

    @CrossOrigin
    @GET
    public Response returnreservations(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Reservation> reservations = reservationBean.getReservations(query);
        Long reservationsCount = reservationBean.getReservationsCount(query);

        return Response.ok(reservations).header("X-Total=Count", reservationsCount).build();
    }

    @Schema(description = "add reservation")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Adds a new reservation", description = "add reservation")
    @APIResponses({
            @APIResponse(description = "List of reservations", responseCode = "204")
    })

    @CrossOrigin
    @POST
    public Response addReservation(ReservationDTO reservation){
        umb.addReservation(reservation);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "edit an reservation")
    @SecurityRequirement(name = "none")
    @Operation(summary = "edit reservation", description = "edit reservation")
    @APIResponses({
            @APIResponse(description = "Edits a reservation", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Reservation.class)))
    })

    @CrossOrigin
    @Path("{id}")
    @PUT
    public Response editreservation(@PathParam("id") Integer id, Reservation reservation){
        reservationBean.updateReservation(id,reservation);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "delete an reservation")
    @SecurityRequirement(name = "none")
    @Operation(summary = "deletes reservation", description = "delete reservation")
    @APIResponses({
            @APIResponse(description = "Deletes a reservation", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Reservation.class)))
    })

    @Path("{id}")
    @DELETE
    public Response deletereservation(@PathParam("id") Integer id) {
        boolean odstranjen=reservationBean.deleteReservations(id);
        if(odstranjen){
            return Response.status(Response.Status.GONE).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
