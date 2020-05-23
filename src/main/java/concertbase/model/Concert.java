package concertbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Concert implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private String organizerWebsite;

    private String name;

    @OneToMany(mappedBy = "concert")
    private List<Performance> performances = new ArrayList<>();

    public Concert() { }

    public Concert(String name, Date date, String organizerWebsite){
        this.date = date;
        this.organizerWebsite = organizerWebsite;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void addPerformance(Performance performance) {
        if(!this.performances.contains(performance)) {
            this.performances.add(performance);
            performance.setConcert(this);
        }
    }

    public int compareTo(Object other){
        if(!(other instanceof Concert)) return 0;
        return -this.date.compareTo(((Concert) other).date);
    }

    public String getOrganizerWebsite() {
        return organizerWebsite;
    }

    public void setOrganizerWebsite(String organizerWebsite) {
        this.organizerWebsite = organizerWebsite;
    }

    @Override
    public String toString() {
        return this.name + " " + this.date;
    }
}
