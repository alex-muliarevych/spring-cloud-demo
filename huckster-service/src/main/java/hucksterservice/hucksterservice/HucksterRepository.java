package hucksterservice.hucksterservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * Repository of Hucksters.
 */
@RepositoryRestResource
public interface HucksterRepository extends JpaRepository<Huckster, Long> {

    @RestResource(path = "by-mail")
    Collection<Huckster> findByMail(@Param("mail") String mail);
}
