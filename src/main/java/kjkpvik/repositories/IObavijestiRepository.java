package kjkpvik.repositories;

import kjkpvik.models.Obavijest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Siii on 5/9/2017.
 */
public interface IObavijestiRepository extends PagingAndSortingRepository<Obavijest, Long> {
}

