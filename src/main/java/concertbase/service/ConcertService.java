package concertbase.service;

import concertbase.model.LiveConcert;
import concertbase.model.StreamedConcert;
import concertbase.model.Venue;
import concertbase.persistence.ConcertRepository;
import concertbase.persistence.LiveConcertRepository;
import concertbase.persistence.StreamedConcertRepository;
import concertbase.persistence.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class ConcertService {
    @Autowired
    ConcertRepository concertRepository;

    @Autowired
    LiveConcertRepository liveConcertRepository;

    @Autowired
    StreamedConcertRepository streamedConcertRepository;

    @Autowired
    VenueRepository venueRepository;

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




}
