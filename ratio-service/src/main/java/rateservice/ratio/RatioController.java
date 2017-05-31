package rateservice.ratio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by omuliarevych on 5/31/17.
 */
@RefreshScope
@RestController
public class RatioController {

    @Value("${rate:100}")
    private int rate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(value = "/minRate")
    Integer getMaxRate() {
        return rate;
    }

    @RequestMapping(value = "/isPriceValid", method = RequestMethod.GET)
    public boolean isPriceValid(@RequestParam("sqft") Double sqft, @RequestParam("flatCount") Integer flatCount,
                             @RequestParam("price") Double price) {
        // check min logical correct values:
        if (flatCount <= 0 || sqft <= 10 || price <= 100) {
            return false;
        }
        double averageSqftPerRoom = sqft / flatCount;
        double pricePerSqft = price / averageSqftPerRoom;
        return pricePerSqft % 10 < rate;
    }
}
