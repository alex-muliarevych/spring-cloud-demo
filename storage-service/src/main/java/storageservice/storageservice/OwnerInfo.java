package storageservice.storageservice;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by omuliarevych on 5/30/17.
 */
@Entity
@EqualsAndHashCode
public class OwnerInfo {
    @Id
    protected String email;
    protected String name;
    protected String surname;
    protected String phone;
    protected String info;

    public OwnerInfo(String email, String name, String surname, String phone, String info) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.info = info;
    }

    public OwnerInfo() {
    }

    public String getEmail() {

        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getInfo() {
        return info;
    }
}
