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

    public void addVenue(String name, String country, String city, String street, int streetNumber, String zipCode){
        this.venueRepository.save(new Venue(name, country, city, street, streetNumber, zipCode));
    }

}
