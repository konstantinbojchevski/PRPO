package si.fri.prpo.lokacijskiopomniki.storitve;

public class OcenaDTO {

    private Integer id;
    private Integer ocena;

    public OcenaDTO(Integer id, Integer ocena) {
        this.id = id;
        this.ocena = ocena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
}
