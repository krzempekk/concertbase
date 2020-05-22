package concertbase.persistence;

import concertbase.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Genre, Long>{
}
