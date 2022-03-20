package si.fri.prpo.lokacijskiopomniki.storitve;

import java.sql.Time;

public class ReservationDTO {

    private int chargerID;
    private String user_username;
    private Time from_time;
    private Time to_time;

    public ReservationDTO(Integer c, String u, Time from_time, Time to_time){
        this.chargerID = c;
        this.user_username = u;
        this.from_time = from_time;
        this.to_time = to_time;
    }

    public Integer getCharger() {
        return chargerID;
    }

    public String getUser() {
        return user_username;
    }

    public Time getFrom_time() {
        return from_time;
    }

    public Time getTo_time() {
        return to_time;
    }
}
