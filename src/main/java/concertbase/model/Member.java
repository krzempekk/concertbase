package concertbase.model;

//@Entity
//public class Member {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    private String name;
//
//    @ManyToMany(cascade = {CascadeType.ALL})
//    private List<Artist> artists = new ArrayList<>();
//
//    public Member() {}
//
//    public Member(String name) {
//        this.name = name;
//    }
//
//    public String getName() { return name; }
//
//    public void setName(String name) { this.name = name; }
//
//    public List<Artist> getArtists() { return artists; }
//
//    public void addArtist(Artist artist) {
//        if(!this.artists.contains(artist)) {
//            this.artists.add(artist);
//            artist.addMember(this);
//        }
//    }
//}
