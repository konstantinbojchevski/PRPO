package si.fri.prpo.lokacijskiopomniki.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.lokacijskiopomniki.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UserBean {

    //if the bean is ApplicationScoped, it will create the same uuid for every request,
    //If the bean is RequestScoped, it will create different uuids for every request, visible upon refreshing

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;
    private UUID uuid;

    private Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        uuid = UUID.randomUUID();
        log.info("Initializing");
    }

    @PreDestroy
    private void dest() {
        log.info("Destroying");
    }


    public List<User> getUsers(QueryParameters query) {
        //Query q = em.createNamedQuery("User.getAll");
//       List<User> users=(List<User>)(q.getResultList());
        List<User> users = JPAUtils.queryEntities(em, User.class, query);
        log.info("Returned users.");
        return users;
    }

    public Long getUsersCount(QueryParameters query) {
        Long usersCount = JPAUtils.queryEntitiesCount(em, User.class, query);
        return usersCount;

    }

//    public List<User> getUporabnikiCriteria() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> cr = cb.createQuery(User.class);
//        Root<User> root = cr.from(User.class);
//        cr.select(root);
//
//        TypedQuery<User> q = em.createQuery(cr);
//        List<User> rez = q.getResultList();
//
//        return rez;
//    }

    @Transactional
    public User addUser(User u) {
        if(u != null) {
            //em.getTransaction().begin();
            em.persist(u);
            log.info("User is added.");
            //em.getTransaction().commit();
        }
        else {
            log.warning("Cannot add user.");
        }
        return u;
    }

    public User getUserByUsername(String username) {
        log.info(String.valueOf(uuid));
        Query q = em.createNamedQuery("User.findByUserName").setParameter("userName", username);
        User user = (User) q.getResultList().get(0);
        log.info("Successfully returned user by username.");
        return user;
    }

    @Transactional
    public boolean deleteUser(Integer id, User u) {
        if(u != null) {
            User rem = em.merge(u);
            //em.getTransaction().begin();
            //em.merge(u);
            em.remove(rem);
            log.info("User deleted");
            return true;
            //em.getTransaction().commit();
        }
        //em.remove(u);
        return  false;
    }

    @Transactional
    public User updateUser(Integer id, User u) {
        if(u != null) {
            //em.getTransaction().begin();
            em.merge(u);
            log.info("User updated");
            //em.getTransaction().commit();
        }
        return u;
    }



//    public List<Owner> getOwnersCriteria() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Owner> cr = cb.createQuery(Owner.class);
//        Root<Owner> root = cr.from(Owner.class);
//        cr.select(root);
//
//        TypedQuery<Owner> q = em.createQuery(cr);
//        List<Owner> rez = q.getResultList();
//
//        return rez;
//    }
//
//    public List<Charger> getChargersCriteria() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Charger> cr = cb.createQuery(Charger.class);
//        Root<Charger> root = cr.from(Charger.class);
//        cr.select(root);
//
//        TypedQuery<Charger> q = em.createQuery(cr);
//        List<Charger> rez = q.getResultList();
//
//        return rez;
//    }

//    public List<Reservation> getReservationsCriteria() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Reservation> cr = cb.createQuery(Reservation.class);
//        Root<Reservation> root = cr.from(Reservation.class);
//        cr.select(root);
//
//        TypedQuery<Reservation> q = em.createQuery(cr);
//        List<Reservation> rez = q.getResultList();
//
//        return rez;
//    }

}
