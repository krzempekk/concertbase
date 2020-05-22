package concertbase.service;

import concertbase.persistence.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StreamedConcertService {

    @Autowired
    StreamedConcertService streamedConcertRepository;

    @Autowired
    ConcertRepository concertRepository;


}
