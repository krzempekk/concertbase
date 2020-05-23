package concertbase.service;

import concertbase.model.*;
import concertbase.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConcertService {
    @Autowired
    ArtistService artistService;

    @Autowired
    ConcertRepository concertRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    LiveConcertRepository liveConcertRepository;

    @Autowired
    StreamedConcertRepository streamedConcertRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    PerformanceRepository performanceRepository;


    public void findConcertByGenre(String subgenreName) {
        List<Artist> artistList = artistRepository.findAllBySubgenres_Name(subgenreName);
        System.out.println("Concerts with subgenre " + subgenreName + ":");
        for(Concert concert: concertRepository.findByPerformances_ArtistIn(artistList)) {
            System.out.println(concert.getName());
        }
    }

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public void addLiveConcert(String name, String date, String organizerWebsite, String venueName, String city) throws ParseException {
        Venue venue = this.venueRepository.findByNameAndCity(venueName, city).get(0);
        liveConcertRepository.save(new LiveConcert(name, parseDate(date), organizerWebsite, venue));
    }

    public void addStreamedConcert(String name, String date, String organizerWebsite, String website) throws ParseException {
        streamedConcertRepository.save(new StreamedConcert(name, parseDate(date), organizerWebsite, website));
    }


    private Date parseDate(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }


    public List<Concert> findByLiveByCriteria(Artist artist, Subgenre subgenre, String city, String dateFrom, String dateTo){
        return concertRepository.findAll(new Specification<>() {

            @Override
            public Predicate toPredicate(Root<Concert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(artist != null) {
                    List<Long> artistPerformancesIds = performanceRepository.findByArtist(artist)
                        .stream()
                        .map(performance -> performance.getConcert().getId())
                        .collect(Collectors.toList());

                    predicates.add(root.get("id").in(artistPerformancesIds));
                }
                if(city != null){
                    List<Long> cityVenues = venueRepository.findByCity(city)
                            .stream()
                            .map(Venue::getId)
                            .collect(Collectors.toList());
                    predicates.add(root.get("VENUE_FK").in(cityVenues));
                }

                if(dateFrom != null) {
                    try {
                        List<Long> concertIds = concertRepository.findAllByDateAfter(parseDate(dateFrom))
                                .stream()
                                .map(Concert::getId)
                                .collect(Collectors.toList());

                        predicates.add(root.get("id").in(concertIds));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if(dateTo != null) {
                    try {
                        List<Long> concertIds = concertRepository.findAllByDateBefore(parseDate(dateTo))
                                .stream()
                                .map(Concert::getId)
                                .collect(Collectors.toList());

                        predicates.add(root.get("id").in(concertIds));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }

        });
    }



//    public List<Concert> findByCriteria(String artistName, String subgerneName, String city, String fromDate, String toDate){
//        List<Predicate> predicates = new ArrayList<>();
//
//
//    }




}
