package concertbase.persistence;

import concertbase.model.Artist;
import concertbase.model.Subgenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    public List<Artist> findAllBySubgenres_Name(String subgenres_name);
}
