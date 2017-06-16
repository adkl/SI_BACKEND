package kjkpvik.repositories;

import kjkpvik.models.Zalba;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Siii on 5/9/2017.
 */
public interface IZalbaRepository extends PagingAndSortingRepository<Zalba, Long> {
    public List<Zalba> findByPrivatnaTrueOrderByVrijemePostavljanjaDesc();
    public List<Zalba> findByPrivatnaFalseOrderByVrijemePostavljanjaDesc();
    public List<Zalba> findByVrijemePostavljanjaAfter(Date datum);
    public Integer deleteZalbaById(Long id);
}
