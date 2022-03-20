package si.fri.prpo.lokacijskiopomniki.servlets;

import si.fri.prpo.lokacijskiopomniki.User;
import si.fri.prpo.lokacijskiopomniki.storitve.UserBean;
import si.fri.prpo.lokacijskiopomniki.storitve.UsersManagementBean;
import si.fri.prpo.lokacijskiopomniki.storitve.UserDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UserBean userBean;

    @Inject
    private UsersManagementBean umb;

    private Logger log = Logger.getLogger(JPAServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //List<User> users = userBean.getUsers();

        PrintWriter writer = resp.getWriter();

//        writer.println("<h3>Users without CriteriaApi:<br></h3>");
//        for (User u : users) {
//            writer.println(u.getId() + ", " + u.getFirstname() + ", " + u.getLastname() + ", " + u.getUsername() + ", " +
//                    u.getPersonReservations());
//        }


//        writer.println("<h3><br>Users using CriteriaApi:<br></h3>");

//        List<User> rez = userBean.getUporabnikiCriteria();
//        for (User u:rez){
//            writer.println(u.getId() + ", " + u.getFirstname() + ", " + u.getLastname() + ", " + u.getUsername() + ", " +
//                    u.getPersonReservations());
//        }
//
//        writer.println();
//        writer.println("<h3><br>Owners using CriteriaApi:<br></h3>");
//
//        List<Owner> rez2 = userBean.getOwnersCriteria();
//        for (Owner o:rez2){
//            writer.println(o.getId() + ", " + o.getFirstname() + ", " + o.getLastname() + ", " + o.getUsername() + ", " +
//                    o.getChargers());
//        }
//
//        writer.println("<h3><br>Chargers using CriteriaApi:<br></h3>");
//
//        List<Charger> rez3 = userBean.getChargersCriteria();
//        for (Charger c:rez3){
//            writer.println(c.getId_charger() + ", " + c.getLocation() + ", " + c.getPrice() + ", " + c.getSpecifications());
//        }
//
//        writer.println("<h3><br>Resevations using CriteriaApi:<br></h3>");
//
//        List<Reservation> rez4 = userBean.getReservationsCriteria();
//        for (Reservation r:rez4){
//            writer.println(r.getId_reservation() + ", " + r.getUser().getUsername() + ", " + r.getCharger().getName());
//        }

        log.info("New request:");
        writer.println("<h3><br>Added user:<br></h3>");
        User u = new User ("Miha", "Zarabec","miha_zarabec");
        writer.println(userBean.addUser(u));

        writer.println("<h3><br>Read user by username:<br></h3>");
        writer.println(userBean.getUserByUsername("petrakos").getFirstname());

        writer.println("<h3><br>Update name for the added user:<br></h3>");
        User ur = userBean.getUserByUsername("miha_zarabec");
        ur.setFirstname("Jure");
        userBean.updateUser(ur.getId(),ur);
        writer.println(userBean.getUserByUsername("miha_zarabec"));

        writer.println("<h3><br>Deleted user:<br></h3>");
        userBean.deleteUser(u.getId(),u);
        writer.println("User deleted!");


        writer.println("<h3><br>Adding user by umb:<br></h3>");

        User u1 = new User ("Janez", "Novak","jnovak");
        UserDTO u2 = new UserDTO (u1);
        umb.addUser(u2);
        writer.println(userBean.getUserByUsername("jnovak").getFirstname() + " " + userBean.getUserByUsername("jnovak").getLastname());


        User u3 = new User ("Iva", "Novak","ivanovak");
        UserDTO u4 = new UserDTO (u3);
        umb.addUser(u4);
        writer.println(userBean.getUserByUsername("ivanovak").getFirstname() + " " + userBean.getUserByUsername("ivanovak").getLastname());
    }
}