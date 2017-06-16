package kjkpvik.controlers;

import kjkpvik.models.ZabranjenaRijec;
import kjkpvik.services.ZabranjeneRijeciService;
import kjkpvik.viewmodels.KorisnikVM;
import kjkpvik.viewmodels.ZabranjeneRijeciVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Siii on 5/9/2017.
 */
//API prema korisnicima
@RestController
@RequestMapping(path="/obavijesti")
public class test {

    @RequestMapping(value = "/all", method = RequestMethod.GET) // prikaz svih oglasa iz kategorije, treba dodati request parameter za odabranu kat.
    public String getAll() {
        return "anisa";
    }

}
//test za servis zabranjenih rijeci
/*@RestController
@RequestMapping(path = "/zrijeci")
public class test {
    private ZabranjeneRijeciService zabranjeneRijeciService;
    @Autowired
    public void setService(ZabranjeneRijeciService zabranjeneRijeciService){
        this.zabranjeneRijeciService = zabranjeneRijeciService;
    }
    @RequestMapping(value = "/kreiraj", method = RequestMethod.POST)
    public ResponseEntity dodajRijec(@RequestBody ZabranjeneRijeciVM rijec)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zabranjeneRijeciService.dodajZabranjenuRijec(rijec));
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
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteRijec(@RequestBody ZabranjeneRijeciVM rijec)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zabranjeneRijeciService.deleteZabranjenuRijec(rijec));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }
}*/