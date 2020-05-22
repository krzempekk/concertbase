package concertbase.model;

import javax.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}
