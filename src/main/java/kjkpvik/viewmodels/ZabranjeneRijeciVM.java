package kjkpvik.viewmodels;

import kjkpvik.models.ZabranjenaRijec;

/**
 * Created by Kemal Halilbegović on 10.05.2017..
 */
public class ZabranjeneRijeciVM {
    private Long id;
    private String rijec;
    private Long korisnikID;
    public ZabranjeneRijeciVM(){}
    public ZabranjeneRijeciVM(String rijec){
        this.setRijec(rijec);
    }
    public ZabranjeneRijeciVM(ZabranjenaRijec rijec){
        this.setId(rijec.getID());
        this.setKorisnikID(rijec.getKorisnik().getID());
        this.setRijec(rijec.getRijec());
    }
    public ZabranjeneRijeciVM(String rijec,KorisnikVM korisnik){
        this.setRijec(rijec);
        this.setKorisnikID(korisnik.getID());
    }

    public Long getId(){return id;}

    public void setId(Long id) {this.id = id;}

    public String getRijec() {
        return rijec;
    }

    public void setRijec(String rijec) {
        this.rijec = rijec;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }
}
