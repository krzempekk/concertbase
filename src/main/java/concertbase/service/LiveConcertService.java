package concertbase.service;

import concertbase.persistence.ConcertRepository;
import concertbase.persistence.LiveConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class LiveConcertService {

    @Autowired
    LiveConcertRepository liveConcertRepository;

    @Autowired
    ConcertRepository concertRepository;


    public void addLiveConcert(String Name, Date date){

    }

}
