package concertbase.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class StreamedConcert extends Concert {
    private String website;

    public StreamedConcert() {}

    public StreamedConcert(String name, Date date, String organizerWebsite, String website){
        super(name, date, organizerWebsite);
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
