package kjkpvik.services;

import kjkpvik.models.Korisnik;
import kjkpvik.models.ZabranjenaRijec;
import kjkpvik.models.Zalba;
import kjkpvik.repositories.IKorisnikRepository;
import kjkpvik.repositories.IZabranjeneRijeciRepository;
import kjkpvik.repositories.IZalbaRepository;
import kjkpvik.viewmodels.ZalbaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Siii on 5/9/2017.
 */

@Service
public class ZalbaService {

    @Autowired
    private IZalbaRepository iZalbaRepository;

    @Autowired
    private IKorisnikRepository iKorisnikRepository;

    @Autowired
    private IZabranjeneRijeciRepository iZabranjeneRijeciRepository;

    //get

    public List<ZalbaVM> getZalbe(){
        Sort sortiraj=new Sort(Sort.Direction.DESC, "vrijemePostavljanja");
        List<Zalba> zalbe=(List<Zalba>)iZalbaRepository.findAll(sortiraj);
        return zalbeVMlista(zalbe);
    }

    public Integer deleteById(Long id){
        return iZalbaRepository.deleteZalbaById(id);
    }

    // get filtriraj (opcija javne/privatne)

    public List<ZalbaVM> getPrivatneZalbe(){
        List<Zalba> zalbe=iZalbaRepository.findByPrivatnaTrueOrderByVrijemePostavljanjaDesc();
        return zalbeVMlista(zalbe);
    }

    /**
     * RADI!
     */
    public List<ZalbaVM> getJavneZalbe(){
        List<Zalba> zalbe=iZalbaRepository.findByPrivatnaFalseOrderByVrijemePostavljanjaDesc();
        return zalbeVMlista(zalbe);
    }

    //add

    public Boolean dodajZalbu(ZalbaVM zalbaVM, String username){
        if(prazanTekst(zalbaVM.getTekst()) || sadrziZabranjenuRijec(zalbaVM.getTekst())){
            return false;
        }

        // Registruje se vrijeme kada je zalba upisana u bazu
        Date refVrijeme;
        if(zalbaVM.getVrijemePostavljanja()!=null){
            refVrijeme=new Date(zalbaVM.getVrijemePostavljanja().getTime());
        }
        else{
            refVrijeme=new Date();
        }
        refVrijeme.setDate(refVrijeme.getDate()-1);

        List<Zalba> pomZalbe=iZalbaRepository.findByVrijemePostavljanjaAfter(refVrijeme);

        /**
         * Ovo je kao provjera da li je korisnik postavio zalbu.... Treba prepravit
         */
        pomZalbe=pomZalbe.stream().filter(korisnikPostavioZalbu(zalbaVM.getKorisnikID())).collect(Collectors.toList());
       /* if(pomZalbe.size()!=0){
            return false;
        }*/

        Zalba zalba=new Zalba();

        zalba.setTekst(zalbaVM.getTekst());
        zalba.setPrivatna(zalbaVM.getPrivatna());

        Korisnik korisnik=iKorisnikRepository.findKorisnikByUsername(username);
        zalba.setKorisnikID(korisnik);

        refVrijeme=new Date();
        if(zalbaVM.getVrijemePostavljanja()!=null){
            refVrijeme=zalbaVM.getVrijemePostavljanja();
        }
        zalba.setVrijemePostavljanja(refVrijeme);

        return iZalbaRepository.save(zalba)!=null;
    }

    private Boolean sadrziZabranjenuRijec(String tekst){
        List<ZabranjenaRijec> rijeci=(List<ZabranjenaRijec>)iZabranjeneRijeciRepository.findAll();

        for(ZabranjenaRijec r:rijeci){
            Pattern p1=Pattern.compile(".*[^a-zčćđšž]"+r.getRijec()+"[^a-zčćđšž].*");
            Matcher m1 = p1.matcher(tekst.toLowerCase());

            Pattern p2=Pattern.compile("^"+r.getRijec()+"[^a-zčćđšž].*");
            Matcher m2 = p2.matcher(tekst.toLowerCase());

            Pattern p3=Pattern.compile(".*[^a-zčćđšž]"+r.getRijec()+"$");
            Matcher m3 = p3.matcher(tekst.toLowerCase());

            if(m1.matches() || m2.matches() || m3.matches()){
                return true;
            }

            // ili da se stavi:
            /*
            Pattern p=Pattern.compile(".*"+r.getRijec()+".*"); zabranjene rijeci su mala slova ili da se to ovdje uradi
            Matcher m=p.matcher(tekst.toLowerCase());

            if(m.matches()){
                return true;
            }
            */
        }

        return false;
    }

    private Boolean prazanTekst(String tekst) {
        if(tekst == null) {
            return true;
        }

        Pattern p = Pattern.compile("\\s*");
        Matcher m = p.matcher(tekst);

        return m.matches();
    }

    private static Predicate<Zalba> korisnikPostavioZalbu(Long korisnikId){
        return z->z.getKorisnikID().getID().equals(korisnikId);
    }

    private List<ZalbaVM> zalbeVMlista(List<Zalba> zalbe){
        List<ZalbaVM> zalbeVM=new ArrayList<ZalbaVM>();
        for(Zalba z:zalbe){
            zalbeVM.add(new ZalbaVM(z));
        }
        return zalbeVM;
    }

}
