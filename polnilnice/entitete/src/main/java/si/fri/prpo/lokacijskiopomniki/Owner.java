package si.fri.prpo.lokacijskiopomniki;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name="owner")

@NamedQueries(value =
        {
                @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM owner o"),
                @NamedQuery(name="Owner.findByUserName", query = "SELECT o FROM owner o WHERE o.username_owner = :userName"),
                @NamedQuery(name="Owner.findByFirstName", query = "SELECT o FROM owner o WHERE o.firstname_owner = :firstName"),
                @NamedQuery(name="Owner.getAllChargers", query = "SELECT o.chargers FROM owner o")

        })

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_owner;

    private String firstname_owner;
    private String lastname_owner;
    private String username_owner;

    public Owner(String firstname, String lastname, String username) {
        this.firstname_owner = firstname;
        this.lastname_owner = lastname;
        this.username_owner = username;
    }
    public Owner(){}

    @OneToMany
    private ArrayList<Reservation> personReservations;

    public ArrayList<Charger> getChargers() {
        return chargers;
    }

    public void setChargers(ArrayList<Charger> chargers) {
        this.chargers = chargers;
    }

    @OneToMany
    private ArrayList<Charger>  chargers;

    public int getId() {
        return id_owner;
    }

    public void setId(int id) {
        this.id_owner = id;
    }

    public String getFirstname() {
        return firstname_owner;
    }

    public void setFirstname(String firstname) {
        this.firstname_owner = firstname;
    }

    public String getLastname() {
        return lastname_owner;
    }

    public void setLastname(String lastname) {
        this.lastname_owner = lastname;
    }

    public String getUsername() {
        return username_owner;
    }

    public void setUsername(String username) {
        this.username_owner = username;
    }

    public ArrayList<Reservation> getPersonReservations() {
        return personReservations;
    }

    public void setPersonReservations(ArrayList<Reservation> personReservations) {
        this.personReservations = personReservations;
    }
}

