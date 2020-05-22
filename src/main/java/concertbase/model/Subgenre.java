package concertbase.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
public class Subgenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="GENRE_FK")
    private Genre genre;

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

}
