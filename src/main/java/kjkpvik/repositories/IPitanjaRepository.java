package kjkpvik.repositories;

import kjkpvik.models.Pitanje;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Sumejja on 10.05.2017..
 */
public interface IPitanjaRepository extends PagingAndSortingRepository<Pitanje, Long> {
    List<Pitanje> findAllByAnketa_Id(Long id);
}
