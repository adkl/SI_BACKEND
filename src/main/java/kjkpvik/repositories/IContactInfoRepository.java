package kjkpvik.repositories;

import kjkpvik.models.ContactInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amer on 5/28/17.
 */
public interface IContactInfoRepository extends PagingAndSortingRepository<ContactInfo, Long> {
    ContactInfo findContactInfoById(Long id);
    ContactInfo findFirstByIdIsNotNull();
}
