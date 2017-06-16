package kjkpvik.controlers;

import kjkpvik.services.ZalbaService;
import kjkpvik.viewmodels.ZalbaVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Dell on 10.5.2017..
 */

@RestController
@RequestMapping(path = "/zalbe")
public class ZalbaController {
    private ZalbaService zalbaService;

    @Autowired
    public void setZalbaService(ZalbaService zalbaService) {
        this.zalbaService = zalbaService;
    }

    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteZalba (@RequestParam("Id")Long id ){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(zalbaService.deleteById(id));
        }
        catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }


    // get
    /**
     * RADI!
     */
    @RequestMapping(value = "/prikazi_zalbe", method = RequestMethod.GET)
    public List<ZalbaVM> getZalbe() {
        return zalbaService.getZalbe();
    }

    // filtriraj

    /**
     * RADI!
     */
    @RequestMapping(value = "/prikazi_privatne_zalbe", method = RequestMethod.GET)
    public List<ZalbaVM> getPrivatneZalbe() {
        return zalbaService.getPrivatneZalbe();
    }

    /**
     * RADI!
     */
    @RequestMapping(value = "/prikazi_javne_zalbe", method = RequestMethod.GET)
    public List<ZalbaVM> getJavneZalbe() {
        return zalbaService.getJavneZalbe();
    }

    // add

    /**
     * RADI!
     */
    @RequestMapping(value = "/dodaj_zalbu", method = RequestMethod.POST )
    public ResponseEntity dodajZalbu(@RequestBody ZalbaVM zalba, Principal principal) //, Principal principal
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(zalbaService.dodajZalbu(zalba, principal.getName())); //principal.getName()
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }
}
