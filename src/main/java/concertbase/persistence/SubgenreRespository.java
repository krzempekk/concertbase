package concertbase.persistence;

import concertbase.model.Subgenre;
import org.springframework.data.repository.CrudRepository;

public interface SubgenreRespository extends CrudRepository<Subgenre, Long> {
}
