package concertbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Performance> performances;

    @ManyToMany(mappedBy = "artists", cascade = {CascadeType.PERSIST})
    private List<Member> members = new ArrayList<>();;

    @ManyToMany(mappedBy = "artists", cascade = {CascadeType.PERSIST})
    private List<Subgenre> subgenres = new ArrayList<>();

    public Artist() {};

    public Artist(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Member> getMembers() { return members; }

    public void addMember(Member member) {
        if(!this.members.contains(member)) {
            this.members.add(member);
            member.addArtist(this);
        }
    }

    public List<Subgenre> getSubgenres() { return subgenres; }

    public void addSubgenre(Subgenre subgenre) {
        if(!this.subgenres.contains(subgenre)) {
            this.subgenres.add(subgenre);
            subgenre.addArtist(this);
        }
    }
}
