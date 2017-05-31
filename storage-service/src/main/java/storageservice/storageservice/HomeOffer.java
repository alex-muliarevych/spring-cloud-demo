package storageservice.storageservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class which contains information about home offer.
 */

@Entity
public class HomeOffer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private Integer flatCount;

    @NotNull
    private double price;

    @NotNull
    private double sqft;

    public HomeOffer(AppartmentInfo info, String phoneNumber) {
        this.price = info.price;
        this.sqft = info.sqft;
        this.city = info.city;
        this.address = info.address;
        this.flatCount = info.flatCount;
        this.email = info.ownerMail;
        this.phoneNumber = phoneNumber;
    }

    public HomeOffer(String email, String phoneNumber, String city, String address,
                     double price, double sqft, int flatCount) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.price = price;
        this.sqft = sqft;
        this.address = address;
        this.flatCount = flatCount;
    }

    public HomeOffer() {
    }

    @Override
    public String toString() {
        return "HomeOffer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", flatCount=" + flatCount +
                ", price=" + price +
                ", sqft=" + sqft +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getPrice() {
        return price;
    }

    public double getSqft() {
        return sqft;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Integer getFlatCount() {
        return flatCount;
    }
}
