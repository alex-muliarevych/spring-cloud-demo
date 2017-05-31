package com.realtor.service.realtorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by omuliarevych on 5/21/17.
 */
@RefreshScope
@RestController
public class RealtorController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(
            value = "/registerRealtor",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OwnerInfo registerRealtor(@RequestBody OwnerInfo ownerInfo) {
        // TODO: add call for storage service with check for huckster or not.
        return ownerInfo;
    }

    @PostMapping(
            value = "/addOffer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AppartmentInfo addOffer(@RequestBody AppartmentInfo offerInfo) {
        // TODO: add call to check offer, check owner for huckster and limit of offers, then store to storage.
        return offerInfo;
    }
}