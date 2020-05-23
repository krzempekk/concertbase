package concertbase.persistence;

import concertbase.model.Artist;
import concertbase.model.Performance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
    List<Performance> findByArtist(Artist artist);
}
