package concertbase.persistence;

import concertbase.model.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
}
