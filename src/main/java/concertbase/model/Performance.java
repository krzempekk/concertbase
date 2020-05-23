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
        this.concert = concert;
        this.role = role;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Concert getConcert() {
        return concert;
    }
}
