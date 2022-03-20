package si.fri.prpo.lokacijskiopomniki.storitve;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.lokacijskiopomniki.Charger;
import si.fri.prpo.lokacijskiopomniki.Owner;
import si.fri.prpo.lokacijskiopomniki.Reservation;
import si.fri.prpo.lokacijskiopomniki.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@ApplicationScoped
public class UsersManagementBean {

    private Logger log = Logger.getLogger(UserBean.class.getName());

    @Inject
    private UserBean userBean;

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private OwnerBean ownerBean;

    @Inject
    private ChargerBean chargerBean;

    private Client httpClient;
    private String baseUrl;
    private String baseUrl2;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance().get("integrations.ocena.base-url").orElse("http://localhost:8081/v1");
        baseUrl2 = ConfigurationUtil.getInstance().get("integrations.contactInfo.base-url").orElse("http://localhost:8082/v1");
    }


    @Transactional
    public void addReservation(ReservationDTO r){
        User u = userBean.getUserByUsername(r.getUser());
        Charger c = chargerBean.getChargerByID(r.getCharger());
        if(r.getCharger()!= null && r.getUser() != null &&
        r.getFrom_time() != null && r.getTo_time() != null){
            Reservation nov = new Reservation(c,u, r.getFrom_time(), r.getTo_time());
            reservationBean.addReservation(nov);
            log.info("Reservation successfully added");
        } else{
            log.info("Some of the mandatory data is missing!");
        }
    }
    @BeleziKlice
    @Transactional
    public void addUser(UserDTO dto){
        if(dto.getUsername() != "" && dto.getFirstname() != "" && dto.getLastname() != ""){
            User u = new User(dto.getFirstname(), dto.getLastname(), dto.getUsername());

            userBean.addUser(u);
            dto.setPhone("064-256-255");
            updateContactInfo(dto);
            log.info("User successfully added");
        } else{
            log.info("Some of the mandatory data is missing!");
        }
    }

    @BeleziKlice
    @Transactional
    public void addOwner(OwnerDTO o){
        if(o.getFirstname() != "" && o.getLastname() != "" &&
        o.getUsername() != ""){
            Owner nov = new Owner(o.getFirstname(), o.getLastname(), o.getUsername());
            ownerBean.addOwner(nov);
            log.info("Owner successfully added");
        } else{
            log.info("Some of the mandatory data is missing!");
        }
    }

    @Transactional
    public void addCharger(ChargerDTO c){
        Owner o = ownerBean.getOwnerByUsername(c.getOwnerusername());
        if(c.getLocation() != "" && c.getSpecifications() != "" &&
        c.getPrice() > -1 && c.getName() != "" && c.getClosing() != null && c.getOpening() != null){
            Charger nov = new Charger(c.getName(), c.getSpecifications(), c.getLocation(), c.getPrice(), c.getOpening(),
                    c.getClosing(),o);
            chargerBean.addCharger(nov);
            c.setOcena(5);
            updateOcena(c);
            log.info("Charger successfully added");
        } else{
            log.info("Some of the mandatory data is missing!");
        }
    }

    private void updateOcena(ChargerDTO c){
        try {
            httpClient.target(baseUrl + "/ocena/").request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(new OcenaDTO(c.getId_charger(), c.getOcena())));
        }
        catch(WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    private void updateContactInfo(UserDTO u){
        try {
            httpClient.target(baseUrl2 + "/contactInfo/").request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(new ContactDTO(u.getId(), u.getPhone())));
        }
        catch(WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}
