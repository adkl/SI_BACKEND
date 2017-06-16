package kjkpvik.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siii on 5/8/2017.
 */
@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    public Korisnik () {}

    public Korisnik (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @ManyToOne(targetEntity = Rola.class)
    private Rola rola;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik", targetEntity = Anketa.class, cascade = CascadeType.ALL)
    private List<Anketa> ankete = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Odgovor.class, mappedBy = "korisnik", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Odgovor> odgovori = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik", targetEntity = Zalba.class, cascade = CascadeType.ALL)
    private List<Zalba> zalbe = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik", targetEntity = ZabranjenaRijec.class, cascade = CascadeType.ALL)
    private List<ZabranjenaRijec> zabranjeneRijeci = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik", targetEntity = Obavijest.class, cascade = CascadeType.ALL)
    private List<Obavijest> obavijesti = new ArrayList<>();

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public List<Anketa> getAnkete() {
        return ankete;
    }

    public void setAnkete(List<Anketa> ankete) {
        this.ankete = ankete;
    }

    public List<Zalba> getZalbe() {
        return zalbe;
    }

    public void setZalbe(List<Zalba> zalbe) {
        this.zalbe = zalbe;
    }

    public List<ZabranjenaRijec> getZabranjeneRijeci() {
        return zabranjeneRijeci;
    }

    public void setZabranjeneRijeci(List<ZabranjenaRijec> zabranjeneRijeci) {
        this.zabranjeneRijeci = zabranjeneRijeci;
    }

    public List<Obavijest> getObavijesti() {
        return obavijesti;
    }

    public void setObavijesti(List<Obavijest> obavijesti) {
        this.obavijesti = obavijesti;
    }

    public List<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }
}
