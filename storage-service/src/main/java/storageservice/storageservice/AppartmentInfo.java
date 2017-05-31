package storageservice.storageservice;

import lombok.Data;

/**
 * Created by omuliarevych on 5/31/17.
 */
@Data
public class AppartmentInfo {
    protected String ownerMail;
    protected String city;
    protected Double price;
    protected Double sqft;
    protected Integer flatCount;
    protected String address;
}
