package kjkpvik.viewmodels;

import kjkpvik.models.ContactInfo;

/**
 * Created by amer on 5/28/17.
 */
public class ContactInfoVM {
    private String email;
    private String phone;
    private String address;

    public ContactInfoVM(){}

    public ContactInfoVM(ContactInfo contactInfo){
        setAddress(contactInfo.getAddress());
        setPhone(contactInfo.getPhone());
        setEmail(contactInfo.getEmail());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
