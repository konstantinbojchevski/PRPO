package si.fri.prpo.lokacijskiopomniki.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.lokacijskiopomniki.Reservation;
import si.fri.prpo.lokacijskiopomniki.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ReservationBean {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    private Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Initializing");
    }

    @PreDestroy
    private void dest() {
        log.info("Destroying");
    }

    public List<Reservation> getReservations(QueryParameters query) {

        List<Reservation> reservations = JPAUtils.queryEntities(em, Reservation.class, query);
        log.info("Returned reservations.");
        return reservations;
    }

    public Long getReservationsCount(QueryParameters query) {
        Long reservationsCount = JPAUtils.queryEntitiesCount(em, Reservation.class, query);
        return reservationsCount;
    }



    @Transactional
    public Reservation addReservation(Reservation r) {
        if(r != null) {
            //em.getTransaction().begin();
            em.persist(r);
            log.info("Reservation added");
            //em.getTransaction().commit();
        }
        else {
            log.warning("Could not add reservation");
        }
        return r;
    }

    public Reservation getReservationByID(int id) {
        Query q = em.createNamedQuery("Charger.findByID").setParameter("ID", id);
        Reservation reservation = (Reservation) q.getResultList().get(0);
        log.info("Reservation returned by ID");
        return reservation;
    }

    @Transactional
    public boolean deleteReservations(Integer id) {
        Reservation r = em.find(Reservation.class, id);
        if(r != null) {
            Reservation rem = em.merge(r);
            em.remove(rem);
            log.info("Reservation deleted");
            return true;
        }
        return  false;
    }

    @Transactional
    public Reservation updateReservation(Integer id, Reservation r) {
        if(r != null) {
            //em.getTransaction().begin();
            em.merge(r);
            log.info("Reservation updated");
            //em.getTransaction().commit();
        }
        return r;
    }
}
