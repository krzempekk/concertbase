package concertbase.service;

import concertbase.model.Artist;
import concertbase.model.Subgenre;
import concertbase.persistence.ArtistRepository;
import concertbase.persistence.GenreRepository;
import concertbase.persistence.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SubgenreRepository subgenreRepository;

    public void addArtist(String name, String subgenreName) {
        Artist artist = new Artist(name);
        Subgenre subgenre = subgenreRepository.findByName(subgenreName);
        artist.addSubgenre(subgenre);
        artistRepository.save(artist);
    }

    public void addArtist(String name, String[] subgenreNames) {
        Artist artist = new Artist(name);
        for(String subgenreName: subgenreNames) {
            Subgenre subgenre = subgenreRepository.findByName(subgenreName);
            artist.addSubgenre(subgenre);
        }
        artistRepository.save(artist);
    }
}
