package kjkpvik.models;

/**
 * Created by Siii on 5/8/2017.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Obavijest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private long ID;
    private String naziv;
    private String tekst;
    private Date vrijemeObjave;

    // veza sa korisnikom
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Korisnik.class)
    private Korisnik korisnik;

    //veza sa obavijestLokacija
    @OneToMany(mappedBy = "obavijest", targetEntity = ObavijestLokacija.class, cascade = CascadeType.ALL)
    private List<ObavijestLokacija> lokacije = new ArrayList<>();

    public Obavijest() {
    }

    public Obavijest(String naziv, String tekst, Date vrijemeObjave, Korisnik korisnik) {

        this.naziv = naziv;
        this.tekst = tekst;
        this.vrijemeObjave = vrijemeObjave;
        this.korisnik = korisnik;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<ObavijestLokacija> getLokacije() {
        return lokacije;
    }

    public void setLokacije(List<ObavijestLokacija> lokacije) {
        this.lokacije = lokacije;
    }
}
