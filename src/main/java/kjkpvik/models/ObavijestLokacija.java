package kjkpvik.models;

import javax.persistence.*;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class ObavijestLokacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;


    @ManyToOne(targetEntity = Obavijest.class)
    private Obavijest obavijest;

    @ManyToOne(targetEntity = Lokacija.class)
    private Lokacija lokacija;

    public ObavijestLokacija() {
    }

    public ObavijestLokacija(Obavijest obavijest, Lokacija lokacija) {
        this.obavijest = obavijest;
        this.lokacija = lokacija;
    }

    public Obavijest getObavijest() {
        return obavijest;
    }

    public void setObavijest(Obavijest obavijest) {
        this.obavijest = obavijest;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
