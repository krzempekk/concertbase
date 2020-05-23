package concertbase.model;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @OrderBy("date DESC")
    private SortedSet<LiveConcert> liveConcerts = new TreeSet<LiveConcert>();

    private String name;
    private String city;
    private String street;
    private int streetNumber;
    private String zipCode;

    public Venue() { }

    public Venue(String name, String city, String street, int streetNumber, String zipCode){
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId(){ return this.id; }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Venue)) return false;
        Venue other = (Venue) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return (int) this.id * 2137 + this.streetNumber;
    }

    public void addLiveConcert(LiveConcert liveConcert){
        this.liveConcerts.add(liveConcert);
    }

//    public void removeLiveConcert(LiveConcert liveConcert){
//        this.liveConcerts.remove(liveConcert);
//    }

}
