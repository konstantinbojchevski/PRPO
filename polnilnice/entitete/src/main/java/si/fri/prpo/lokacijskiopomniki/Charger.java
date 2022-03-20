package si.fri.prpo.lokacijskiopomniki;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;

@Entity(name="charger")

@NamedQueries(value =
        {
                @NamedQuery(name = "Charger.getAll", query = "SELECT c FROM charger c"),
                @NamedQuery(name="Charger.findByID", query = "SELECT c FROM charger c WHERE c.id_charger = :ID"),
                @NamedQuery(name="Charger.getAllCheap", query = "SELECT c FROM charger c WHERE c.price < 100"),
                @NamedQuery(name="Charger.findInLjubljana", query = "SELECT c FROM charger c WHERE c.location LIKE '%:location%' ")
        })

public class Charger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_charger;

    private String name;
    private String specifications;
    private String location;
    private int price;
    private Time opening;
    private Time closing;

    @ManyToOne
    @JoinColumn(name="id_owner")
    private Owner owner;

    public Charger(String name, String specifications, String location, int price, Time opening, Time closing, Owner owner){
        this.name = name;
        this.specifications = specifications;
        this.location = location;
        this.price = price;
        this.opening = opening;
        this.closing = closing;
        this.owner = owner;
    }

    public Charger(){}

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @OneToMany
    private ArrayList<Reservation> chargerReservations;

    public int getId_charger() {
        return id_charger;
    }

    public void setId_charger(int id_charger) {
        this.id_charger = id_charger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int cena) {
        this.price = cena;
    }

    public Time getOpening() {
        return opening;
    }

    public void setOpening(Time opening) {
        this.opening = opening;
    }

    public Time getClosing() {
        return closing;
    }

    public void setClosing(Time closing) {
        this.closing = closing;
    }

    public ArrayList<Reservation> getReservations() {
        return chargerReservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.chargerReservations = reservations;
    }
}

