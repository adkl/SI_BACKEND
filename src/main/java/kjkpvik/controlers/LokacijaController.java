package kjkpvik.controlers;

import kjkpvik.models.Lokacija;
import kjkpvik.viewmodels.LokacijaVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import kjkpvik.services.LokacijaService;


/**
 * Created by Mersida on 11.5.2017.
 */

@RestController
@RequestMapping (path= "/lokacija")
public class LokacijaController {

    private LokacijaService lokacijaService;

    @Autowired
    public void setService (LokacijaService korisnikService) {
        this.lokacijaService = korisnikService;
    }

    // STATUS: RADI
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping (value = "/dodaj", method = RequestMethod.POST)
    public ResponseEntity dodajLokaciju (@RequestBody LokacijaVM lokacija){

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(lokacijaService.dodajLokaciju(lokacija));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity dajSveLokacije() {
        return ResponseEntity.status(HttpStatus.OK)
                            .body(lokacijaService.dajSveLokacije());
    }

    @RequestMapping (value = "/getlokaciju" , method = RequestMethod.GET)
    public ResponseEntity getLokacija(@RequestParam("ID")Long id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(lokacijaService.getLokacija(id));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteLokacija (@RequestBody LokacijaVM lokacija)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(lokacijaService.brisiLokaciju(lokacija));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

}
