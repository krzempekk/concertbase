package concertbase.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ARTIST_FK")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "CONCERT_FK")
    private Concert concert;

    private String role;

    private LocalTime startTime;

    private LocalTime endTime;


    public Performance() {}

    public Performance(Artist artist, Concert concert, String role, LocalTime startTime, LocalTime endTime) {
        this.artist = artist;
        artist.addPerformance(this);

        this.concert = concert;
        concert.addPerformance(this);

        this.role = role;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Artist getArtist() { return artist; }

    public void setArtist(Artist artist) {
        if(this.artist != artist) {
            this.artist = artist;
            artist.addPerformance(this);
        }
    }

    public Concert getConcert() { return concert; }

    public void setConcert(Concert concert) {
        if(this.concert != concert) {
            this.concert = concert;
            concert.addPerformance(this);
        }
    }
}
