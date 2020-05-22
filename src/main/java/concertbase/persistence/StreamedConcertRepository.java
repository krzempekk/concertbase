package concertbase.persistence;

import concertbase.model.StreamedConcert;
import org.springframework.data.repository.CrudRepository;

public interface StreamedConcertRepository extends CrudRepository<StreamedConcert, Long> {
}
