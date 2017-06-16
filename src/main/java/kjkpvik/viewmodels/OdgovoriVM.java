package kjkpvik.viewmodels;


import java.util.List;

/**
 * Created by Sumejja on 11.05.2017..
 */
public class OdgovoriVM {

    private Long korisnik_id;
    private List<Long> pitanja;
    private List<String> odgovori;

    public OdgovoriVM() {}

    public OdgovoriVM(Long korisnik_id, List<Long> pitanja, List<String> odgovori){
        this.korisnik_id = korisnik_id;
        this.pitanja = pitanja;
        this.odgovori = odgovori;
    }


    public List<String> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<String> odgovori) {
        this.odgovori = odgovori;
    }

    public Long getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(Long korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public List<Long> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Long> pitanja) {
        this.pitanja = pitanja;
    }
}
