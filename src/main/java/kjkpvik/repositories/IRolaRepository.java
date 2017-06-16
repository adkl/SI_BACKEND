package kjkpvik.repositories;

import kjkpvik.models.Rola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.management.Query;
import javax.persistence.EntityManager;

/**
 * Created by Siii on 5/9/2017.
 */
public interface IRolaRepository extends PagingAndSortingRepository<Rola, Long> {

    Rola findRolaByNaziv(String roleName);

}
