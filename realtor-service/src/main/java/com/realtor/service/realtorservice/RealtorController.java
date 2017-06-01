package com.realtor.service.realtorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by omuliarevych on 5/21/17.
 */
@RefreshScope
@RestController
public class RealtorController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

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
    public ResponseEntity<?> registerRealtor(@RequestBody OwnerInfo ownerInfo, HttpServletResponse response) {
        String hucksterUrl = "http://HUCKSTER-SERVICE/isHuckster";
        // Add query parameters.
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(hucksterUrl)
                .queryParam("phone", ownerInfo.phone)
                .queryParam("mail", ownerInfo.email);

        ResponseEntity<Boolean> isHucksterCheck = this.restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, Boolean.class);
        if (isHucksterCheck.getBody()) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Sorry, current user is recognized as Huckster. Registration failed");
            return new ResponseEntity<String>(headers, HttpStatus.METHOD_NOT_ALLOWED);
        }
        ResponseEntity<OwnerInfo> responseEntity;
        try {
            responseEntity = this.restTemplate.exchange("http://STORAGE-SERVICE/addRealtor",
                    HttpMethod.POST, new HttpEntity<>(ownerInfo), OwnerInfo.class, ownerInfo);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            // Process response with error message from storage-service.
            HttpStatus statusCode = ex.getStatusCode();
            if (statusCode == HttpStatus.BAD_REQUEST) {
                if (ex.getResponseHeaders().containsKey("Error")) {
                    HttpHeaders headers = new HttpHeaders();
                    List<String> errorMessages = ex.getResponseHeaders().getValuesAsList("Error");
                    errorMessages.forEach(message -> headers.add("Error", message));
                    return new ResponseEntity<String>(headers, statusCode);
                }
            }
            throw ex;
        }
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