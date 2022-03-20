package si.fri.prpo.lokacijskiopomniki.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.lokacijskiopomniki.Owner;
import si.fri.prpo.lokacijskiopomniki.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class OwnerBean {

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

    public List<Owner> getOwners(QueryParameters query) {

        Query q = em.createNamedQuery("Owner.getAll");
        List<Owner> owners = JPAUtils.queryEntities(em, Owner.class, query);
        log.info("Returned owners.");
        return owners;
    }

    public Long getOwnersCount(QueryParameters query) {
        Long ownersCount = JPAUtils.queryEntitiesCount(em, Owner.class, query);
        return ownersCount;

    }

    @Transactional
    public Owner addOwner(Owner o) {
        if(o != null) {
            //em.getTransaction().begin();
            em.persist(o);
            log.info("Owner added");

            //em.getTransaction().commit();
        }
        else {
            log.warning("Could not add owner");
        }
        return o;
    }

    public Owner getOwnerByUsername(String username) {
        Query q = em.createNamedQuery("Owner.findByUserName").setParameter("userName", username);
        Owner owner = (Owner) q.getResultList().get(0);
        log.info("Owner returned by username");
        return owner;
    }

    @Transactional
    public boolean deleteOwner(Integer id, Owner o) {
        if(o != null) {
            Owner rem = em.merge(o);
            //em.getTransaction().begin();
            em.remove(rem);
            log.info("Owner deleted");
            //em.getTransaction().commit();
            return true;
        }
        else {
            log.warning("Couldn't delete owner");
        }
        //em.remove(o);
        return false;
    }

    @Transactional
    public Owner updateOwner(Integer id, Owner o) {
        if(o != null) {
            //em.getTransaction().begin();
            em.merge(o);
            log.info("Owner updated");
            //em.getTransaction().commit();
        }
        return o;
    }

}
