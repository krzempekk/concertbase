package concertbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subgenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
//    @JoinColumn(name="GENRE_FK")
    private Genre genre;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Artist> artists = new ArrayList<>();

    public Subgenre() {}


    public Subgenre(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        if(!this.genre.getSubgenres().contains(this))
            this.genre.addSubgenre(this);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        if(!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addSubgenre(this);
        }
    }
}
