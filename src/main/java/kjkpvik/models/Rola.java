package kjkpvik.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class Rola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private long ID;
    private String naziv;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rola", targetEntity = Korisnik.class)
    private List<Korisnik> korisnici = new ArrayList<>();

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

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Rola (){}

    public Rola(String naziv)
    {
        this.naziv = naziv;

    }
}
