package storageservice.storageservice;

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

    @RestResource(path = "by-mail")
    Collection<HomeOffer> findByEmail(@Param("email") String email);

    @RestResource(path = "by-city")
    Collection<HomeOffer> findByCity(@Param("city") String city);

    @RestResource(path = "by-flat-count")
    Collection<HomeOffer> findByFlatCount(@Param("flatCount") int flatCount);

    @RestResource(path = "by-price")
    Collection<HomeOffer> findByPrice(@Param("price") double price);
}

