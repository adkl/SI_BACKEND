package kjkpvik.viewmodels;

import kjkpvik.models.ObavijestLokacija;

import java.util.List;

/**
 * Created by Mersida on 10.5.2017.
 */
public class LokacijaVM {

    private Long ID;
    private String naziv;
    //private List<ObavijestLokacija> ol;


    public LokacijaVM () {}
    public LokacijaVM (String naziv){this.setNaziv(naziv);}



    public void setNaziv (String naziv){this.naziv=naziv;}
    public String getNaziv (){return naziv;}
    public void setID (Long ID) {this.ID=ID;}
    public Long getID (){return ID;}
    //public List<ObavijestLokacija> getOl() {return ol;}
    //public void setOl(List<ObavijestLokacija> ol) {
        //this.ol = ol;
    //}


}

