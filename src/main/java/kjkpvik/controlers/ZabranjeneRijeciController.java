package kjkpvik.controlers;

import kjkpvik.services.ZabranjeneRijeciService;
import kjkpvik.viewmodels.ZabranjeneRijeciVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Kemal Halilbegović on 11.05.2017..
 */
@RestController
@RequestMapping(path = "/zrijeci")
public class ZabranjeneRijeciController {
    private ZabranjeneRijeciService zabranjeneRijeciService;
    @Autowired
    public void setService(ZabranjeneRijeciService zabranjeneRijeciService){
        this.zabranjeneRijeciService = zabranjeneRijeciService;
    }

    @RequestMapping(value = "/prikazi_rijeci", method = RequestMethod.GET)
    public List<ZabranjeneRijeciVM> getWords() {
        return zabranjeneRijeciService.getWords();
    }

    @RequestMapping(value = "/kreiraj", method = RequestMethod.POST)
    public ResponseEntity dodajRijec(@RequestBody ZabranjeneRijeciVM rijec, Principal prinicpal)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zabranjeneRijeciService.dodajZabranjenuRijec(rijec, prinicpal.getName()));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getRijec(@RequestParam("ID")Long id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zabranjeneRijeciService.getZabranjenaRijec(id));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_HR')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteRijec(@RequestParam("Id")Long id )
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zabranjeneRijeciService.deleteById(id));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }
}
