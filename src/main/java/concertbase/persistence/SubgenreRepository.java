package concertbase.persistence;

import concertbase.model.Subgenre;
import org.springframework.data.repository.CrudRepository;

public interface SubgenreRepository extends CrudRepository<Subgenre, Long> {
}
