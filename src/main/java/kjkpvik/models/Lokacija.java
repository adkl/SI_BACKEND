package kjkpvik.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class Lokacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;

    @Column(unique = true)
    private String naziv;

    //veza sa ObavijestLokacija
    @OneToMany(mappedBy = "lokacija", targetEntity = ObavijestLokacija.class, cascade = CascadeType.ALL)
    private List<ObavijestLokacija> ol= new ArrayList<>(); //?

    public Lokacija() {
    }

    public Lokacija(String naziv) {

        this.naziv = naziv;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<ObavijestLokacija> getOl() {
        return ol;
    }

    public void setOl(List<ObavijestLokacija> ol) {
        this.ol = ol;
    }
}
