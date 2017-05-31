package storageservice.storageservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by omuliarevych on 5/31/17.
 */
@RepositoryRestResource
public interface OwnerInfoRepository extends JpaRepository<OwnerInfo, String> {

    @RestResource(path = "by-email")
    OwnerInfo findByEmail(@Param("email") String email);
}
