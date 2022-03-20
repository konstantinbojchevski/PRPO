package si.fri.prpo.lokacijskiopomniki.storitve;

import java.sql.Time;

public class ChargerDTO {
    private Integer id_charger;

    public Integer getId_charger() {
        return id_charger;
    }

    public void setId_charger(Integer id_charger) {
        this.id_charger = id_charger;
    }

    private String name;
    private String specifications;
    private String location;
    private Integer ocena;
    private int price;
    private Time opening;
    private Time closing;
    private String ownerusername;

    public String getName() {
        return name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    public Time getOpening() {
        return opening;
    }

    public Time getClosing() {
        return closing;
    }

    public String getOwnerusername(){
        return ownerusername;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public ChargerDTO(Integer id,String name, String specifications, String location, int price, Time opening, Time closing, String ownerusername){
        this.id_charger = id;
        this.name = name;
        this.specifications = specifications;
        this.location = location;
        this.price = price;
        this.opening = opening;
        this.closing = closing;
        this.ownerusername = ownerusername;
    }

}
