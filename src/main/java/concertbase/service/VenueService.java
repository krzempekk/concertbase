package concertbase.service;

import concertbase.persistence.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VenueService {

    @Autowired
    VenueRepository venueRepository;


}
