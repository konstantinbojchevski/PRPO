package si.fri.prpo.polnilnice.contactInfo.api.v1;

public class Contact {
    private Integer id;
    private String phone;

    public Contact(){}

    public Contact(Integer id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

