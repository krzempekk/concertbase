package concertbase.persistence;


import concertbase.model.LiveConcert;
import org.springframework.data.repository.CrudRepository;

public interface LiveConcertRepository extends CrudRepository<LiveConcert, Long> {
}
