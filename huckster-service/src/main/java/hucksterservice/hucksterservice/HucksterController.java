package hucksterservice.hucksterservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by omuliarevych on 5/31/17.
 */
@RestController
public class HucksterController {
    @Value("${enabled-ban-list:true}")
    private boolean enabledFilter;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    HucksterRepository hucksterRepository;

    @RequestMapping({"/service-instances/{applicationName}"})
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(
            value = "/addHuckster",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Huckster updateCustomer(@RequestBody Huckster huckster) {
        hucksterRepository.save(huckster);
        return huckster;
    }

    @RequestMapping(
            value = {"/isHuckster"},
            method = {RequestMethod.GET}
    )
    public boolean isHuckster(@RequestParam("phone") String phone, @RequestParam("mail") String mail) {
        if (!enabledFilter) {
            return false;
        }
        return hucksterRepository.findAll().stream()
                                           .filter(e -> e.getMail().equalsIgnoreCase(mail) ||
                                                   e.getPhoneNumber().equalsIgnoreCase(phone))
                                           .count() > 0;
    }
}
