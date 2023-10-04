package crm.automotive.Models.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Suppliers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String compaName;

    private String mainContact;

    private String contactInfo;

    private String producType;

    private String purHistory;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompaName() {
        return compaName;
    }

    public void setCompaName(String compaName) {
        this.compaName = compaName;
    }

    public String getMainContact() {
        return mainContact;
    }

    public void setMainContact(String mainContact) {
        this.mainContact = mainContact;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getProducType() {
        return producType;
    }

    public void setProducType(String producType) {
        this.producType = producType;
    }

    public String getPurHistory() {
        return purHistory;
    }

    public void setPurHistory(String purHistory) {
        this.purHistory = purHistory;
    }

}
