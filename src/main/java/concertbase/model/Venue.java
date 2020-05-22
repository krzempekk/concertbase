package concertbase.model;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private SortedSet<LiveConcert> liveConcerts = new TreeSet<>();

    private String name;
    private String country;
    private String street;
    private int streetNumber;
    private String zipCode;

    public Venue() { }



}
