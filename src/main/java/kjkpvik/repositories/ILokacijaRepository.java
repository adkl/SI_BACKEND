package kjkpvik.repositories;

import kjkpvik.models.Lokacija;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Siii on 5/9/2017.
 */
public interface ILokacijaRepository extends PagingAndSortingRepository<Lokacija, Long> {
    Lokacija findLokacijaByNaziv(String naziv);
}
