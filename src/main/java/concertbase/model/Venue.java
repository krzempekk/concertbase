package concertbase.model;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<LiveConcert> liveConcerts = new TreeSet<>();

    private String name;
    private String country;
    private String street;
    private int streetNumber;
    private String zipCode;

    public Venue() { }



}
