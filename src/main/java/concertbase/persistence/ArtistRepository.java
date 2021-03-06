package concertbase.persistence;

import concertbase.model.Artist;
import concertbase.model.Subgenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findByName(String name);

    List<Artist> findAllBySubgenres_NameIgnoreCase(String subgenres_name);

    List<Artist> findBySubgenresContains(Subgenre subgenre);
}
