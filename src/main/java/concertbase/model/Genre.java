package concertbase.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy="genre")
    private List<Subgenre> subgenres = new ArrayList<>();

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public List getSubgenres(){
        return this.subgenres;
    }

    public void addSubgenre(Subgenre subgenre){
        this.subgenres.add(subgenre);

    }
}
