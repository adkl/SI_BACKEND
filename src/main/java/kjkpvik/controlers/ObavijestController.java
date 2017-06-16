package kjkpvik.controlers;

import kjkpvik.services.ObavijestiService;
import kjkpvik.viewmodels.ObavijestVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Delila on 11.05.2017.
 */
@RestController
@RequestMapping(path="/obavijest")
public class ObavijestController {

    private ObavijestiService obavijestiService;
    @Autowired
    public void SetService (ObavijestiService obavijestService) {this.obavijestiService = obavijestService;}

    //dodaj obavijest
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dodaj", method = RequestMethod.POST )
    public ResponseEntity dodajObavijest(@RequestBody ObavijestVM obavijestVM, Principal principal)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(obavijestiService.DodajObavijest(obavijestVM, principal.getName()));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    //dodaj obavijest
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE )
    public ResponseEntity deleteObavijest(@PathVariable("id") Long id) {

        return ResponseEntity.ok(obavijestiService.Delete(id));
    }

    //izmijeni obavijest
    @RequestMapping(value = "/update", method = RequestMethod.POST )
    public ResponseEntity update(@RequestBody ObavijestVM obavijestVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(obavijestiService.Update(obavijestVM));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    // prikazi sve obavijesti
    @RequestMapping(value = "/sve", method = RequestMethod.GET)
    public List<ObavijestVM> getObavijesti(){
        return obavijestiService.GetObavijesti();
    }

    // filtriraj obavijesti po lokaciji
    @RequestMapping(value = "/filtriraj/{id}", method = RequestMethod.GET)
    public List<ObavijestVM> filtrirajObavijesti(@PathVariable Long id){
        return obavijestiService.filtrirajObavijesti(id);
    }

    // sortiraj obavijesti po lokaciji
    @RequestMapping(value = "/sortiraj", method = RequestMethod.GET)
    public List<ObavijestVM> sortirajObavijesti(Long lokacijaId){
        return obavijestiService.sortirajObavijesti(lokacijaId);
    }
}
