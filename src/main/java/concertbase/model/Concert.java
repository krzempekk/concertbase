package concertbase.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Concert implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private String organizerWebsite;

    private String name;

    @OneToMany(mappedBy = "concert")
    private List<Performance> performances;

    public Concert() { }

    public Concert(String name, Date date, String organizerWebsite){
        this.date = date;
        this.organizerWebsite = organizerWebsite;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
