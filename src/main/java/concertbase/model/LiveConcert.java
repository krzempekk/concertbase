package concertbase.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class LiveConcert extends Concert {

    @ManyToOne
    @JoinColumn(name = "VENUE_FK")
    private Venue venue;

    public LiveConcert() {}

    public LiveConcert(String name, Date date, String organizerWebsite, Venue venue){
        super(name, date, organizerWebsite);
        this.venue = venue;
        if(!venue.getConcerts().contains(this))
            venue.addLiveConcert(this);
    }

    public Venue getVenue(){
        return this.venue;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
        if(!venue.getConcerts().contains(this))
            venue.addLiveConcert(this);
    }


}
