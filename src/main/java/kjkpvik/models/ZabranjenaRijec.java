package kjkpvik.models;

import javax.persistence.*;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class ZabranjenaRijec {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String rijec;

    // veza sa korisnikom
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Korisnik.class, cascade = CascadeType.DETACH)
    private Korisnik korisnik;

    public ZabranjenaRijec() {
    }

    public ZabranjenaRijec(String rijec) {

        this.rijec = rijec;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public String getRijec() {
        return rijec;
    }

    public void setRijec(String rijec) {
        this.rijec = rijec;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
