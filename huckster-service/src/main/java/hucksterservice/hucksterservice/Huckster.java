package hucksterservice.hucksterservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

/**
 * Huckster Entity
 */
@Entity
public class Huckster {
    @Id
    @GeneratedValue
    private Long id;
    private String mail;
    private String name;
    private String phoneNumber;

    public Huckster() {
    }

    public Huckster(String mail, String name, String phoneNumber) {
        this.mail = mail;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "Huckster{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
