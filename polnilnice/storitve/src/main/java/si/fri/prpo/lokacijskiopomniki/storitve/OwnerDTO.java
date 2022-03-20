package si.fri.prpo.lokacijskiopomniki.storitve;

import si.fri.prpo.lokacijskiopomniki.Owner;

public class OwnerDTO {

    private String username;
    private String firstname;
    private String lastname;

    private OwnerDTO(Owner o){
        this.username = o.getUsername();
        this.firstname = o.getFirstname();
        this.lastname = o.getLastname();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
