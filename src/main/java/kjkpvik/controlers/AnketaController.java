package kjkpvik.controlers;

import kjkpvik.services.AnketeService;
import kjkpvik.viewmodels.AnketaVM;
import kjkpvik.viewmodels.OdgovoriVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Sumejja on 10.05.2017..
 */
@RestController
@RequestMapping(path="/anketa")
public class AnketaController {

    private AnketeService anketeService;
    @Autowired
    public void setService (AnketeService anketeService) {
        this.anketeService = anketeService;
    }

    @RequestMapping(value = "/kreirajanketu", method = RequestMethod.POST )
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    public ResponseEntity dodajAnketu(@RequestBody AnketaVM anketa, Principal principal)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.dodajAnketu(anketa, principal.getName()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "aktivne", method = RequestMethod.GET)
    public ResponseEntity getAktivne() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.getAktivneAnkete());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/prikazipitanja/{id}", method = RequestMethod.GET )
    public ResponseEntity prikaziPitanja(@PathVariable Long id){

        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.prikaziPitanja(id));
        }
        catch (ServiceException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    @RequestMapping(value = "/obrisianketu", method = RequestMethod.DELETE )
    public ResponseEntity obrisiAnketu(@RequestParam("Id")Long id) {

        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.obrisiAnketu(id));
        }
        catch (ServiceException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/{id}/odgovori", method = RequestMethod.GET )
    public ResponseEntity dajOdgovore(@PathVariable Long id){ // id ankete

        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.dajOdgovore(id));
        }
        catch (ServiceException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/ispuni", method = RequestMethod.POST )
    public ResponseEntity ispuniAnketu(@RequestBody OdgovoriVM odgovori, Principal principal){

        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(anketeService.ispuniAnketu(odgovori, principal));
        }
        catch (ServiceException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ResponseEntity getAnketa(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                            .body(anketeService.getAnketa(id));
        }
        catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }
}
