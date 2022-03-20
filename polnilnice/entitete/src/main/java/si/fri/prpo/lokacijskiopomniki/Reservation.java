package si.fri.prpo.lokacijskiopomniki;

import javax.persistence.*;
import java.sql.Time;

@Entity(name="reservation")

@NamedQueries(value =
        {
                @NamedQuery(name = "Reservation.getAll", query = "SELECT r FROM reservation r"),
                @NamedQuery(name="Reservation.findByID", query = "SELECT r FROM reservation r WHERE r.id_reservation = :ID"),
                @NamedQuery(name="Reservation.allChargersWithReservation", query = "SELECT DISTINCT r.charger FROM reservation r"),
                @NamedQuery(name="Reservation.getROfUser", query = "SELECT r FROM reservation r WHERE r.user = :user")


        })

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reservation;

    @ManyToOne
    @JoinColumn(name = "id_charger")
    private Charger charger;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    private Time from_time;
    private Time to_time;

    public Reservation(Charger c, User u, Time from_time, Time to_time){
        this.charger = c;
        this.user = u;
        this.from_time = from_time;
        this.to_time = to_time;
    }

    public Reservation(){}

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getFrom() {
        return from_time;
    }

    public void setFrom(Time from) {
        this.from_time = from;
    }

    public Time getTo() {
        return to_time;
    }

    public void setTo(Time to) {
        this.to_time = to;
    }


}