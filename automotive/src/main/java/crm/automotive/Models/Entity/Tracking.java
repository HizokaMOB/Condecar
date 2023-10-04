package crm.automotive.Models.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trackings")
public class Tracking implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customername;

    private String comlog;

    private String statussale;

    private String relevantnotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getComlog() {
        return comlog;
    }

    public void setComlog(String comlog) {
        this.comlog = comlog;
    }

    public String getStatussale() {
        return statussale;
    }

    public void setStatussale(String statussale) {
        this.statussale = statussale;
    }

    public String getRelevantnotes() {
        return relevantnotes;
    }

    public void setRelevantnotes(String relevantnotes) {
        this.relevantnotes = relevantnotes;
    }

    
}
