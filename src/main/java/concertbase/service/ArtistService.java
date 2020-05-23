package concertbase.service;

import concertbase.model.Artist;
import concertbase.model.Concert;
import concertbase.model.Performance;
import concertbase.model.Subgenre;
import concertbase.persistence.ArtistRepository;
import concertbase.persistence.ConcertRepository;
import concertbase.persistence.PerformanceRepository;
import concertbase.persistence.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SubgenreRepository subgenreRepository;

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    ConcertRepository concertRepository;

    public Artist addArtist(String name) {
        Artist artist = new Artist(name);
        artistRepository.save(artist);
        return artist;
    }

    public Artist addArtist(String name, String subgenreName) {
        Artist artist = new Artist(name);
        Subgenre subgenre = subgenreRepository.findByName(subgenreName);
        artist.addSubgenre(subgenre);
        artistRepository.save(artist);
        return artist;
    }

    public Artist addArtist(String name, String[] subgenreNames) {
        Artist artist = new Artist(name);
        for(String subgenreName: subgenreNames) {
            Subgenre subgenre = subgenreRepository.findByName(subgenreName);
            artist.addSubgenre(subgenre);
        }
        artistRepository.save(artist);
        return artist;
    }

    public Performance addPerformance(String artistName, Concert concert, String startTime, String endTime) {
        Artist artist = artistRepository.findByName(artistName);
        if(artist == null) artist = addArtist(artistName);
        Performance performance = new Performance(artist, concert, "headliner", LocalTime.parse(startTime), LocalTime.parse(endTime));
        performanceRepository.save(performance);
        return performance;
    }

    public Performance addPerformance(String artistName, long concertId, String role, String startTime, String endTime){
        Optional<Concert> concertOpt = concertRepository.findById(concertId);
        if(concertOpt.isEmpty()) throw new IllegalArgumentException("No such concert");
        Concert concert = concertOpt.get();
        Performance performance = new Performance(artistRepository.findByName(artistName), concert, role, LocalTime.parse(startTime), LocalTime.parse(endTime));
        performanceRepository.save(performance);
        return performance;
    }


    public Performance addPerformance(String artistName, long concertId){
        Optional<Concert> concertOpt = concertRepository.findById(concertId);
        if(concertOpt.isEmpty()) throw new IllegalArgumentException("No such concert");
        Concert concert = concertOpt.get();
        Performance performance = new Performance(artistRepository.findByName(artistName), concert, "headliner", null, null);
        performanceRepository.save(performance);
        return performance;
    }


}
