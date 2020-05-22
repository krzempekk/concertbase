package concertbase.persistence;

import concertbase.model.Artist;
import concertbase.model.Concert;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
    Concert findByName(String name);

    List<Concert> findByPerformances_ArtistIn(List<Artist> artists);
}
