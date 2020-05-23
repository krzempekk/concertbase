package concertbase.service;

import concertbase.model.*;
import concertbase.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @Autowired
    SubgenreRepository subgenreRepository;


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


    public List<Concert> findByLiveByCriteria(Artist artist, String subgenreName, String city, String dateFrom, String dateTo){
        return concertRepository.findAll(new Specification<>() {

            @Override
            public Predicate toPredicate(Root<Concert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if(artist != null) {
                    List<Long> artistPerformancesIds = performanceRepository.findByArtist(artist)
                        .stream()
                        .map(performance -> performance.getConcert().getId())
                        .collect(Collectors.toList());

                    predicates.add(root.get("id").in(artistPerformancesIds));
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
                if(subgenreName != null){
                    Subgenre subgenre = subgenreRepository.findByName(subgenreName);
                    List<Artist> subgenreArtists = artistRepository.findBySubgenresContains(subgenre);
                    Set<Performance> artistsPerformances = new HashSet<>();
                    for(Artist subgenreAritst: subgenreArtists){
                        artistsPerformances.addAll(performanceRepository.findByArtist(subgenreAritst));
                    }
                    List<Long> artistPerformancesIds = artistsPerformances
                            .stream()
                            .map(performance -> performance.getConcert().getId())
                            .collect(Collectors.toList());
                    predicates.add(root.get("id").in(artistPerformancesIds));
                }
                if(city != null){
                    Root newRoot = criteriaBuilder.treat(root, LiveConcert.class);
                    List<Venue> cityVenues = venueRepository.findByCity(city);
                    System.out.println("Venues");
                    cityVenues.forEach(System.out::println);
                    predicates.add(newRoot.get("venue").in(cityVenues));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

            }

        });
    }




}
