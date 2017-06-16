package kjkpvik.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class Anketa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private  Long id;
    private String opis;
    private Date vrijemeAktivacije;
    private Date vrijemeDeaktivacije;

    // veza sa korisnikom
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Korisnik.class)
    private Korisnik korisnik;

    // veza sa pitanjem
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anketa", targetEntity = Pitanje.class, cascade = CascadeType.ALL)
    private List<Pitanje> pitanja = new ArrayList<>();

    public Anketa() {
    }

    public Anketa(String opis, Date vrijemeAktivacije, Korisnik korisnik, List<Pitanje> pitanja) {

        this.opis = opis;
        this.vrijemeAktivacije = vrijemeAktivacije;
        this.korisnik = korisnik;
        this.pitanja = pitanja;
    }
    public Anketa(String opis, Date vrijemeAktivacije, Date vrijemeDeaktivacije, Korisnik korisnik) {

        this.opis = opis;
        this.vrijemeAktivacije = vrijemeAktivacije;
        this.korisnik = korisnik;
        this.vrijemeDeaktivacije = vrijemeDeaktivacije;
    }

    public Anketa(String opis, Date vrijemeAktivacije, Date vrijemeDeaktivacije) {

        this.opis = opis;
        this.vrijemeAktivacije = vrijemeAktivacije;
        this.vrijemeDeaktivacije = vrijemeDeaktivacije;
    }
    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getVrijemeAktivacije() {
        return vrijemeAktivacije;
    }

    public void setVrijemeAktivacije(Date vrijemeAktivacije) {
        this.vrijemeAktivacije = vrijemeAktivacije;
    }

    public Date getVrijemeDeaktivacije() {
        return vrijemeDeaktivacije;
    }

    public void setVrijemeDeaktivacije(Date vrijemeDeaktivacije) {
        this.vrijemeDeaktivacije = vrijemeDeaktivacije;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }
}
