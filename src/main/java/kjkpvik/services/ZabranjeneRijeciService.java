package kjkpvik.services;

import kjkpvik.models.Korisnik;
import kjkpvik.models.ZabranjenaRijec;
import kjkpvik.repositories.IKorisnikRepository;
import kjkpvik.repositories.IZabranjeneRijeciRepository;
import kjkpvik.viewmodels.KorisnikVM;
import kjkpvik.viewmodels.ZabranjeneRijeciVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siii on 5/9/2017.
 */
@Service
public class ZabranjeneRijeciService {

    @Autowired
    private IKorisnikRepository korisnikRepository;
    @Autowired
    private IZabranjeneRijeciRepository zabranjeneRijeciRepository;


    public List<ZabranjeneRijeciVM> getWords(){
        Iterable<ZabranjenaRijec> words = zabranjeneRijeciRepository.findAll();
        ArrayList<ZabranjeneRijeciVM> wordsVM = new ArrayList<ZabranjeneRijeciVM>();

        for(ZabranjenaRijec word : words){
            wordsVM.add(new ZabranjeneRijeciVM(word));
        }

        return wordsVM;

    }

    //add
    public Boolean dodajZabranjenuRijec(ZabranjeneRijeciVM rijec, String username){//unutar ovoga se nalazi i korisnikID

        ZabranjenaRijec zabranjenaRijec = new ZabranjenaRijec(rijec.getRijec());
        zabranjenaRijec.setKorisnik(korisnikRepository.findKorisnikByUsername(username));
        ZabranjenaRijec kreirana = zabranjeneRijeciRepository.save(zabranjenaRijec);

        return (kreirana!=null);
    }
    //delete
    public boolean deleteZabranjenuRijec(ZabranjeneRijeciVM rijec){
        List<ZabranjenaRijec>zabranjenaRijecList = (List<ZabranjenaRijec>) zabranjeneRijeciRepository.findAll();
        ZabranjenaRijec x =new ZabranjenaRijec();
        for (ZabranjenaRijec z : zabranjenaRijecList)
        {
            if (z.getRijec().equals(rijec.getRijec()))
                x=z;
        }
        if (x.getRijec()==null)return false;
        zabranjeneRijeciRepository.delete(x);
        return  true;

    }

    public Integer deleteById(Long id){
        return zabranjeneRijeciRepository.deleteZabranjenaRijecById(id);
    }

    //get
    public String getZabranjenaRijec(Long ID){
        String zabranjenaRijec = zabranjeneRijeciRepository.findOne(ID).getRijec();
        if (zabranjenaRijec !=null)return zabranjenaRijec;
        return null;
    }

}
