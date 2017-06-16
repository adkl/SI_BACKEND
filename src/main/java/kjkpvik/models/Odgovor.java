package kjkpvik.models;

import javax.persistence.*;

/**
 * Created by Siii on 5/8/2017.
 */

@Entity
public class Odgovor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
    private String tekst;

    //veza sa korisnikom
    @ManyToOne(fetch = FetchType.LAZY)
    private Korisnik korisnik;

    // veza sa pitanjem
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pitanje.class)
    private Pitanje pitanje;

    public Odgovor() {
    }

    public Odgovor(String tekst, Korisnik korisnik, Pitanje pitanje) {

        this.tekst = tekst;
        this.korisnik = korisnik;
        this.pitanje = pitanje;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Pitanje getPitanje() {
        return pitanje;
    }

    public void setPitanje(Pitanje pitanje) {
        this.pitanje = pitanje;
    }
}
