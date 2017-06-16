package kjkpvik.repositories;

import kjkpvik.models.Odgovor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sumejja on 11.05.2017..
 */
public interface IOdgovorRepository extends PagingAndSortingRepository<Odgovor, Long> {
}
