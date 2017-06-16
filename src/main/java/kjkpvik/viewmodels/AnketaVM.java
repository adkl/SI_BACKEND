package kjkpvik.viewmodels;

import kjkpvik.models.Anketa;
import kjkpvik.models.Pitanje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Sumejja on 10.05.2017..
 */
public class AnketaVM {
    private Long ID;
    private String opis;
    private Date vrijeme_aktivacije;
    private Date vrijeme_deaktivacije;
    private List<String> pitanja;

    private List<PitanjeVM> pitanjaVM;

    public AnketaVM() {}

    public AnketaVM(Long ID, String opis, Date vrijeme_aktivacije, Date vrijeme_deaktivacije){
        this.ID = ID;
        this.opis = opis;
        this.vrijeme_aktivacije = vrijeme_aktivacije;
        this.vrijeme_deaktivacije = vrijeme_deaktivacije;

    }

    public AnketaVM(Long ID, String opis, Date vrijeme_aktivacije, Date vrijeme_deaktivacije, List<String> pitanja){
        this.ID = ID;
        this.opis = opis;
        this.vrijeme_aktivacije = vrijeme_aktivacije;
        this.vrijeme_deaktivacije = vrijeme_deaktivacije;

        this.pitanja = pitanja;

    }

    public Long getID() {
        return ID;
    }

    public String getOpis() {
        return opis;
    }

    public Date getVrijeme_aktivacije() {
        return vrijeme_aktivacije;
    }

    public Date getVrijeme_deaktivacije() {
        return vrijeme_deaktivacije;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setVrijeme_aktivacije(Date vrijeme_aktivacije) {
        this.vrijeme_aktivacije = vrijeme_aktivacije;
    }

    public void setVrijeme_deaktivacije(Date vrijeme_deaktivacije) {
        this.vrijeme_deaktivacije = vrijeme_deaktivacije;
    }

    public List<String> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<String> pitanja) {
        this.pitanja = pitanja;
    }

    public static List<AnketaVM> fromAnketaList(List<Anketa> anketas) {
        List<AnketaVM> vms = new ArrayList<>();
        anketas.forEach(anketa -> {
            vms.add(new AnketaVM(anketa.getID(), anketa.getOpis(), anketa.getVrijemeAktivacije(), anketa.getVrijemeDeaktivacije(), null));
        });
        return vms;
    }

    public List<PitanjeVM> getPitanjaVM() {
        return pitanjaVM;
    }

    public void setPitanjaVM(List<PitanjeVM> pitanjaVM) {
        this.pitanjaVM = pitanjaVM;
    }
}
