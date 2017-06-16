package kjkpvik.controlers;

import kjkpvik.services.ContactInfoService;
import kjkpvik.viewmodels.ContactInfoVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by amer on 5/28/17.
 */

@RestController
@RequestMapping(path = "/contact")
public class ContactInfoController {
    private ContactInfoService infoService;

    @Autowired
    public void setInfoService(ContactInfoService infoService){ this.infoService = infoService; }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity addContact(@RequestBody ContactInfoVM contactVM){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(infoService.addContact(contactVM));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    /**
     * By the current SRS there should be only one contact information set in the database
     * so the GET method will return the only object from the DB via the function findFirstByIdIsNotNull()
     * in the corresponding repository.
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ContactInfoVM getContact(){
        return infoService.getContact();
    }

}
