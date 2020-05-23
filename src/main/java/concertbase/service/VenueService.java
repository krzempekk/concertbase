package concertbase.service;

import concertbase.model.Venue;
import concertbase.persistence.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VenueService {

    @Autowired
    VenueRepository venueRepository;

    public Venue addVenue(String name, String city, String street, int streetNumber, String zipCode){
        Venue venue = new Venue(name, city, street, streetNumber, zipCode);
        this.venueRepository.save(venue);
        return venue;
    }



    public Iterable<Venue> getAllVenues() {
        return this.venueRepository.findAll();
    }


    public void printVenueConcerts(String name, String city){
        Venue venue = this.venueRepository.findByNameAndCity(name, city);
        venue.getConcerts().forEach(System.out::println);
    }
}
