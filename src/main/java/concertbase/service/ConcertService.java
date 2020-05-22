package concertbase.service;

import concertbase.model.Artist;
import concertbase.persistence.ArtistRepository;
import concertbase.persistence.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConcertService {
    @Autowired
    ArtistService artistService;

    @Autowired
    ConcertRepository concertRepository;

    @Autowired
    ArtistRepository artistRepository;

    public void findConcertByGenre(String subgenreName) {
        List<Artist> artistList = artistRepository.findAllBySubgenres_Name(subgenreName);
    }

//    @Autowired
//    ConcertRepository concertRepository;
//
//    @Autowired
//    LiveConcertRepository liveConcertRepository;
//
//    @Autowired
//    StreamedConcertRepository streamedConcertRepository;
//
//    @Autowired
//    VenueRepository venueRepository;
//
//    public void addLiveConcert(String name, Date date, String organizerWebsite, String venueName){
//    }
}
