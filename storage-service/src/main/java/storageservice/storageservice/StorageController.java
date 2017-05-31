package storageservice.storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by omuliarevych on 5/31/17.
 */
@RefreshScope
@RestController
public class StorageController {

    @Value("${max-offers-per-user:10}")
    private int maxOffersPerUser;

    @Autowired
    HomeOfferRepository homeOfferRepository;

    @Autowired
    OwnerInfoRepository ownerInfoRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(
            value = "/addOffer",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HomeOffer addOffer(@RequestBody AppartmentInfo info) {
        Optional<OwnerInfo> ownerInfo = Optional.of(ownerInfoRepository.findByEmail(info.ownerMail));
        if (!ownerInfo.isPresent()) {
            throw new RuntimeException("There is no owner specified by mail, register firstly Realtor.");
        }
        HomeOffer homeOffer = new HomeOffer(info, ownerInfo.get().getPhone());
        homeOfferRepository.save(homeOffer);
        return homeOffer;
    }

    @PostMapping(
            value = "/addRealtor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OwnerInfo addRealtor(@RequestBody OwnerInfo ownerInfo) {
        ownerInfoRepository.save(ownerInfo);
        return ownerInfo;
    }

    @RequestMapping(value = "/isOfferLimitValid", method = RequestMethod.GET)
    public boolean isOfferLimitValid(@RequestParam("phone") String phone) {
        List<HomeOffer> homeOffers = homeOfferRepository.findAll();
        return homeOffers.stream()
                         .filter(e -> Pattern.compile(phone).matcher(e.getPhoneNumber()).find())
                         .count() < maxOffersPerUser;
    }

    @RequestMapping(value = "/getHomeOffers", method = RequestMethod.GET)
    public Collection<HomeOffer> getHomeOffers(@RequestParam("city") String city, @RequestParam("flatCount") int flatCount) {
        List<HomeOffer> homeOffers = homeOfferRepository.findAll();
        return homeOffers.stream()
                         .filter(e -> e.getCity().equalsIgnoreCase(city) && e.getFlatCount() == flatCount)
                         .collect(Collectors.toSet());
    }
}
