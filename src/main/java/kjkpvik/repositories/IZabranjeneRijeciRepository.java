package kjkpvik.repositories;

import kjkpvik.models.ZabranjenaRijec;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Siii on 5/9/2017.
 */
public interface IZabranjeneRijeciRepository extends PagingAndSortingRepository<ZabranjenaRijec, Long> {
    public Integer deleteZabranjenaRijecById(Long id);
}
