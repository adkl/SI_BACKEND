package kjkpvik.viewmodels;

/**
 * Created by Siii on 5/9/2017.
 */
public class KorisnikVM {

    private Long ID;
    private String username;
    private String password;
    private String oldPassword;
    private String email;
    private String rola; // ovaj dio jos nisam razjasnila

    public KorisnikVM() {}

    public KorisnikVM(Long ID, String username, String password, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public KorisnikVM(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
