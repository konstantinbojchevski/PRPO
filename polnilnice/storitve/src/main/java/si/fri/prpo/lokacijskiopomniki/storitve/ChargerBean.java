package si.fri.prpo.lokacijskiopomniki.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.lokacijskiopomniki.Charger;
import si.fri.prpo.lokacijskiopomniki.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ChargerBean {

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

    public List<Charger> getChargers(QueryParameters query) {

        List<Charger> chargers = JPAUtils.queryEntities(em, Charger.class, query);
        log.info("Returned chargers.");
        return chargers;

    }

    public Long getChargersCount(QueryParameters query) {
        Long chargersCount = JPAUtils.queryEntitiesCount(em, Charger.class, query);
        return chargersCount;
    }

    @Transactional
    public Charger addCharger(Charger c) {
        if(c != null) {
            //em.getTransaction().begin();
            em.persist(c);
            log.info("Charger added");
            //em.getTransaction().commit();
        }
        else {
            log.info("Cannot add charger");
        }
        return c;
    }

    public Charger getChargerByID(int id) {
        Query q = em.createNamedQuery("Charger.findByID").setParameter("ID", id);
        Charger charger = (Charger) q.getResultList().get(0);
        log.info("Charger returned by id");
        return charger;
    }

    @Transactional
    public boolean deleteCharger(Integer id, Charger c) {
        if(c != null) {
            Charger rem = em.merge(c);
            em.remove(rem);
            log.info("Charger deleted");
            return true;
        }
        return  false;
    }

    @Transactional
    public Charger updateCharger(Integer id, Charger c) {
        if(c != null) {
            //em.getTransaction().begin();
            em.merge(c);
            log.info("Charger updated");
            //em.getTransaction().commit();
        }
        return c;
    }

}
