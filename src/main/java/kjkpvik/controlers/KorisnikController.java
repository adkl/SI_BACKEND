package kjkpvik.controlers;

import kjkpvik.models.Korisnik;
import kjkpvik.models.Rola;
import kjkpvik.services.KorisnikService;
import kjkpvik.viewmodels.KorisnikVM;
import kjkpvik.viewmodels.RolaVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.security.Principal;

/**
 * Created by Siii on 5/9/2017.
 */
@RestController
@RequestMapping(path="/korisnik")
public class KorisnikController {

    private KorisnikService korisnikService;
    @Autowired
    public void setService (KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    // STATUS: VJEROVATNO SE NECE KORISTITI
    @RequestMapping(value = "/kreirajrolu", method = RequestMethod.POST )
    public ResponseEntity dodajRolu(@RequestBody RolaVM rola)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(korisnikService.dodajRolu(rola));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public KorisnikVM getKorisnikById(@RequestParam("Id") Long korisnikId){
        return korisnikService.getUserById(korisnikId);
    }

    @RequestMapping(value = "/getByUsername", method = RequestMethod.GET)
    public KorisnikVM getKorisnikByUsername(@RequestParam("username") String username){
        return korisnikService.getUserByUsername(username);
    }

    // STATUS: RADI
    @RequestMapping(value = "/kreiraj", method = RequestMethod.POST )
    public ResponseEntity dodajKorisnika(@RequestBody KorisnikVM korisnikVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(korisnikService.dodajKorisnika(korisnikVM, "ROLE_USER"));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody KorisnikVM userVM, Principal principal){
        try{
            if(korisnikService.update(userVM, principal.getName())) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    // STATUS: RADI
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_HR', 'ROLE_ADMIN')")
    @RequestMapping(value = "/rola", method = RequestMethod.GET)
    public RolaVM getRola(Principal principal) {

        return korisnikService.getRolaForUser(principal.getName());

    }


}
