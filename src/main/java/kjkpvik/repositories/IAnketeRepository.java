package kjkpvik.repositories;

import kjkpvik.models.Anketa;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Siii on 5/9/2017.
 */
public interface IAnketeRepository extends PagingAndSortingRepository<Anketa, Long> {
    List<Anketa> findAllByVrijemeDeaktivacijeAfter(Date date);
    Anketa findAnketaById(Long id);
}