package kjkpvik.viewmodels;

/**
 * Created by Siii on 5/9/2017.
 */
public class RolaVM {

    private Long ID;
    private String naziv;

    public RolaVM(String naziv) {
        this.setNaziv(naziv);
    }

    public RolaVM() {
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
}
