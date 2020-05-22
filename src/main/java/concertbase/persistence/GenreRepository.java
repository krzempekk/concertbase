package concertbase.persistence;

import concertbase.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findById(long id);
    List<Genre> findByName(String name);
}
