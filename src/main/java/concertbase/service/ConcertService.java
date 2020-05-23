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


    public List<Concert> findByLiveByCriteria(Artist artist, Subgenre subgenre, String city, String fromDate, String toDate){
        return concertRepository.findAll(new Specification<>() {

            @Override
            public Predicate toPredicate(Root<Concert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                CriteriaBuilder.In<Long> inClause = criteriaBuilder.in(root.get("id"));

                List<Performance> artistPerformances = performanceRepository.findByArtist(artist);
                artistPerformances.forEach(System.out::println);

                for(Performance performance: artistPerformances) {
                    inClause.value(performance.getConcert().getId());
                }

                return inClause;


//                List<Predicate> predicates = new ArrayList<>();
//                List<String> artistPerformancesIds = null;
//                if (artist != null) {
//                    List<Performance> artistPerformances = performanceRepository.findByArtist(artist);
//                    artistPerformances.forEach(System.out::println);
//
//                    artistPerformancesIds = artistPerformances
//                            .stream()
//                            .map(performance -> String.valueOf(performance.getConcert().getId()))
//                            .collect(Collectors.toList());

//                    if (artistPerformances != null) {
//                        for (Performance performance : artistPerformances) {
//                            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), performance.getConcert().getId())));
//                        }
//                    }
//                }
//                return criteriaBuilder.isMember(root.get("id"), artistPerformancesIds);
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        });
    }



//    public List<Concert> findByCriteria(String artistName, String subgerneName, String city, String fromDate, String toDate){
//        List<Predicate> predicates = new ArrayList<>();
//
//
//    }




}
