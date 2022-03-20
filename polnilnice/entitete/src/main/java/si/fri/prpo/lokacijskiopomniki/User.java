package si.fri.prpo.lokacijskiopomniki;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name="user")
@Table(name="user", schema = "public")

@NamedQueries(value =
        {
                @NamedQuery(name = "User.getAll", query = "SELECT u FROM user u"),
                @NamedQuery(name="User.findByUserName", query = "SELECT u FROM user u WHERE u.username = :userName"),
                @NamedQuery(name="User.findByFirstName", query = "SELECT u FROM user u WHERE u.firstname = :firstName"),
                @NamedQuery(name="User.getAllReservations", query = "SELECT u.personReservations FROM user u")

        })

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    private String firstname;
    private String lastname;
    private String username;

    public User(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public User() {}

    @OneToMany
    private ArrayList<Reservation> personReservations;

    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Reservation> getPersonReservations() {
        return personReservations;
    }

    public void setPersonReservations(ArrayList<Reservation> personReservations) {
        this.personReservations = personReservations;
    }
}
