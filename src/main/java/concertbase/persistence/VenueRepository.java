package concertbase.persistence;

import concertbase.model.Venue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Long> {
    Venue findByNameAndCity(String name, String city);
    List<Venue> findByCity(String City);
    List<Venue> findAll();
}
