package kjkpvik.services;

import kjkpvik.models.*;
import kjkpvik.repositories.IAnketeRepository;
import kjkpvik.repositories.IKorisnikRepository;
import kjkpvik.repositories.IOdgovorRepository;
import kjkpvik.repositories.IPitanjaRepository;
import kjkpvik.viewmodels.AnketaVM;
import kjkpvik.viewmodels.OdgovoriVM;
import kjkpvik.viewmodels.PitanjeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Siii on 5/9/2017.
 */
@Service
public class AnketeService {

    @Autowired
    private IAnketeRepository anketaRepository;

    @Autowired
    private IKorisnikRepository korisnikRepository;

    @Autowired
    private IPitanjaRepository pitanjaRepository;

    @Autowired
    private IOdgovorRepository odgovorRepository;

    public List<AnketaVM> getAktivneAnkete() {
        return AnketaVM.fromAnketaList(anketaRepository.findAllByVrijemeDeaktivacijeAfter(new Date()));
    }

    // prikazi pitanja za anketu tog ID-a, TESTIRANO
    public List<PitanjeVM> prikaziPitanja(Long id){
        return PitanjeVM.fromPitanjaList(pitanjaRepository.findAllByAnketa_Id(id));
    }

    public AnketaVM getAnketa(Long id) {
        Anketa anketa = anketaRepository.findOne(id);
        AnketaVM anketaVM = new AnketaVM(anketa.getID(), anketa.getOpis(), anketa.getVrijemeAktivacije(),
                                            anketa.getVrijemeDeaktivacije(), null);
        anketaVM.setPitanjaVM(prikaziPitanja(id));

        return anketaVM;
    }


    public Boolean dodajAnketu(AnketaVM anketa, String username){

        Anketa novaAnketa = new Anketa(anketa.getOpis(), anketa.getVrijeme_aktivacije(), anketa.getVrijeme_deaktivacije());
        novaAnketa.setKorisnik(korisnikRepository.findKorisnikByUsername(username)); // znam da treba null check, ali mora se malo i bugova ostaviti :(

        List<String> novaPitanja = anketa.getPitanja(); //anketa mora imati barem jedno pitanje
        if(novaPitanja == null)
            return false;

        List<Pitanje> pitanja = new ArrayList<>();
        novaPitanja.forEach(s -> pitanja.add(new Pitanje(s, novaAnketa)));
        novaAnketa.setPitanja(pitanja);

        Anketa kreirana = anketaRepository.save(novaAnketa);
        return (kreirana != null);
    }


    // daj odgovore na anketu -> za HR za analizu - testirano
    public List<Odgovor> dajOdgovore(Long idAnkete){
        List<Odgovor> sviOdgovori = (List<Odgovor>) odgovorRepository.findAll();
        List<Odgovor> trazeniOdgovori = new ArrayList<Odgovor>();

        for(Odgovor odgovor: sviOdgovori){
            if(odgovor.getPitanje().getAnketa().getID().equals(idAnkete))
                trazeniOdgovori.add(odgovor);
        }

        return trazeniOdgovori;
    }

    //delete - testirano
    public Boolean obrisiAnketu(Long id){

        Anketa anketa = anketaRepository.findAnketaById(id);
        List<Anketa> sveAnkete = (List<Anketa>) anketaRepository.findAll();
        List<Pitanje> svaPitanja = (List<Pitanje>) pitanjaRepository.findAll();
        List<Odgovor>  sviOdgovori = (List<Odgovor>) odgovorRepository.findAll();

        for(Odgovor x: sviOdgovori)
        {
            if(x.getPitanje().getID().equals(anketa.getID())){
                odgovorRepository.delete(x);
            }
        }

        for(Pitanje x: svaPitanja)
        {
            if(x.getAnketa().getID().equals(anketa.getID())){
                pitanjaRepository.delete(x);
            }
        }

        Anketa a = new Anketa();
        for(Anketa x: sveAnkete)
        {
            if(x.getID().equals(anketa.getID()))
            {
                a = x;
                break;
            }
        }

        if(a == null) return  false;

        anketaRepository.delete(a);

        return true;
    }

    //ispuniti anketu / unesi odgovore
    public Boolean ispuniAnketu(OdgovoriVM odgovor, Principal principal){

        List<String> odgovori = odgovor.getOdgovori();
        List<Long> pitanja = odgovor.getPitanja();

        for(int i=0; i<odgovori.size(); i++){
            odgovorRepository.save(new Odgovor(odgovori.get(i), korisnikRepository.findKorisnikByUsername(principal.getName()), pitanjaRepository.findOne(pitanja.get(i))));
        }

        return true;
    }
}
