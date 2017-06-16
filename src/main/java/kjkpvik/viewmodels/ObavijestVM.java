package kjkpvik.viewmodels;

import kjkpvik.models.Obavijest;
import kjkpvik.models.ObavijestLokacija;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kemal Halilbegović on 10.05.2017..
 */
public class ObavijestVM {
    private String naziv;
    private String tekst;
    private Date vrijemeObjave;
    private Long korisnikID;
    private List<String> lokacije;
    private Long ID;

    public ObavijestVM(){
        lokacije=new ArrayList<String>();
    }
    public ObavijestVM(String naziv, String tekst, Date vrijemeObjave){
        this.setNaziv(naziv);
        this.setTekst(tekst);
        this.setVrijemeObjave(vrijemeObjave);
    }

    public ObavijestVM(Obavijest obavijest){
        setID(obavijest.getID());
        setNaziv(obavijest.getNaziv());
        setTekst(obavijest.getTekst());
        setVrijemeObjave(obavijest.getVrijemeObjave());

        setKorisnikID(obavijest.getKorisnik().getID());

        lokacije = new ArrayList<String>();
        for (ObavijestLokacija ol : obavijest.getLokacije()) {
            lokacije.add(ol.getLokacija().getNaziv());
        }
    }

    //ovaj konstruktor je potreban radi update, gdje treba ID obavijesti da bi se znalo koja se obavijest apdejta
    public ObavijestVM (Long ID, String naziv, String tekst, Date vrijemeObjave)
    {
        this.setID(ID);
        this.setNaziv(naziv);
        this.setTekst(tekst);
        this.setVrijemeObjave(vrijemeObjave);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getVrijemeObjave() {
        return vrijemeObjave;
    }

    public void setVrijemeObjave(Date vrijemeObjave) {
        this.vrijemeObjave = vrijemeObjave;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public List<String> getLokacije() {
        return lokacije;
    }

    public void setLokacije(List<String> lokacije) {
        this.lokacije = lokacije;
    }


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
