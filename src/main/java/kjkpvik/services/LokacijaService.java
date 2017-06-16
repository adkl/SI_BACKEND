package kjkpvik.services;

import kjkpvik.models.Lokacija;
import kjkpvik.repositories.ILokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  kjkpvik.viewmodels.LokacijaVM;

import java.util.List;
import java.util.function.Consumer;


/**
 * Created by Siii on 5/9/2017.
 */
@Service
public class LokacijaService {

   @Autowired
   private ILokacijaRepository lokacijaRepository;

   public Iterable<Lokacija> dajSveLokacije() {
       Iterable<Lokacija> lokacije = lokacijaRepository.findAll();
       lokacije.forEach(new Consumer<Lokacija>() {
           @Override
           public void accept(Lokacija lokacija) {
               lokacija.setOl(null);
           }
       });
       return lokacije;
   }

    //dodavanje
    public Boolean dodajLokaciju (LokacijaVM lokacija ){//u lokaciji se nalazi lista ObavijestLokacija
        if(lokacijaRepository.findLokacijaByNaziv(lokacija.getNaziv())!=null){
            return false;
        }
        Lokacija mojaLokacija = new Lokacija(lokacija.getNaziv());
        //mojaLokacija.setOl(lokacija.getOl());//?
        Lokacija kreirana = lokacijaRepository.save(mojaLokacija);

        return (kreirana!= null);

    }

    //brisanje
    public Boolean brisiLokaciju (LokacijaVM lokacija){
        List<Lokacija> sveLokacije = (List<Lokacija>) lokacijaRepository.findAll();
        Lokacija x = new Lokacija();

        for (Lokacija y : sveLokacije){
            if (y.getNaziv().equals(lokacija.getNaziv())){
                x=y;
            }
        }

        if (x.getNaziv()==null) {
            return false;
        }
        lokacijaRepository.delete(x);
        return true;

    }

    //get
    public LokacijaVM getLokacija (Long ID){

        LokacijaVM mojaLokacija = new LokacijaVM(lokacijaRepository.findOne(ID).getNaziv());

        if(mojaLokacija != null)
            return  mojaLokacija;
        return  null;
    }
}
