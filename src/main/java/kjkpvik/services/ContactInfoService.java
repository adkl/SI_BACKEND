package kjkpvik.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import kjkpvik.models.ContactInfo;
import kjkpvik.repositories.IContactInfoRepository;
import kjkpvik.viewmodels.ContactInfoVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by amer on 5/28/17.
 */

@Service
public class ContactInfoService {

    @Autowired
    private IContactInfoRepository contactInfoRepository;

    public Boolean addContact(ContactInfoVM contactVM){
        if(contactInfoRepository.findFirstByIdIsNotNull()==null) {
            ContactInfo newContact = new ContactInfo(contactVM.getEmail(), contactVM.getAddress(), contactVM.getPhone());

            return contactInfoRepository.save(newContact) != null;
        }
        else{
            ContactInfo editContact = contactInfoRepository.findFirstByIdIsNotNull();
            editContact.setAddress(contactVM.getAddress());
            editContact.setEmail(contactVM.getEmail());
            editContact.setPhone(contactVM.getPhone());

            return contactInfoRepository.save(editContact) !=null;
        }
    }

    public ContactInfoVM getContact(){
        return new ContactInfoVM(contactInfoRepository.findFirstByIdIsNotNull());
    }

}
