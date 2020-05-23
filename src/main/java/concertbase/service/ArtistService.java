package concertbase.service;

import concertbase.model.Artist;
import concertbase.model.Performance;
import concertbase.model.Subgenre;
import concertbase.persistence.*;
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

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    ConcertRepository concertRepository;


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

    public void addPerformance(String artistName, String concertName) {
        Performance performance = new Performance(artistRepository.findByName(artistName), concertRepository.findByName(concertName), "headliner", null, null);

        performanceRepository.save(performance);
    }


}
