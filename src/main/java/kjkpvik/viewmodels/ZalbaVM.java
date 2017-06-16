package kjkpvik.viewmodels;

import kjkpvik.models.Zalba;

import java.util.Date;

/**
 * Created by Dell on 10.5.2017..
 */
public class ZalbaVM {
    private Long ID;
    private Date vrijemePostavljanja;
    private String tekst;
    private Boolean privatna;
    private Long korisnikID;

    public ZalbaVM() {}

    public ZalbaVM(Zalba zalba){
        setID(zalba.getID());
        setTekst(zalba.getTekst());
        setPrivatna(zalba.getPrivatna());
        setVrijemePostavljanja(zalba.getVrijemePostavljanja());
        setKorisnikID(zalba.getKorisnikID().getID());
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getVrijemePostavljanja() {
        return vrijemePostavljanja;
    }

    public void setVrijemePostavljanja(Date vrijemePostavljanja) {
        this.vrijemePostavljanja = vrijemePostavljanja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Boolean getPrivatna() {
        return privatna;
    }

    public void setPrivatna(Boolean privatna) {
        this.privatna = privatna;
    }

    public Long  getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long  korisnikID) {
        this.korisnikID = korisnikID;
    }
}
