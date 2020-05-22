package concertbase.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class LiveConcert extends Concert {


    @OneToMany
    private Venue venue;

    public LiveConcert() {}

    public LiveConcert(String name, Date date, String organizerWebsite, Venue venue){
        super(name, date, organizerWebsite);
        this.venue = venue;
    }

    public Venue getVenue(){
        return this.venue;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }


}
