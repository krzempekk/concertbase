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

    public Venue(String name, String country, String street, int streetNumber, String zipCode){
        this.name = name;
        this.country = country;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void addLiveConcert(LiveConcert liveConcert){
        this.liveConcerts.add(liveConcert);
    }

    public void removeLiveConcert(LiveConcert liveConcert){
        this.liveConcerts.remove(liveConcert);
    }
}
