package com.realtor.service.realtorservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * Repository for {@link HomeOffer}s.
 */
@RepositoryRestResource
public interface HomeOfferRepository extends JpaRepository<HomeOffer, Long> {

    @RestResource(path = "by-name")
    Collection<HomeOffer> findByOwnerName(@Param("ownerName") String ownerName);
}
