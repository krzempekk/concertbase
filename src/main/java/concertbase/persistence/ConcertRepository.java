package concertbase.persistence;

import concertbase.model.Artist;
import concertbase.model.Concert;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConcertRepository extends CrudRepository<Concert, Long>, JpaSpecificationExecutor<Concert> {
    Concert findByName(String name);

    List<Concert> findByPerformances_ArtistIn(List<Artist> artists);
}
