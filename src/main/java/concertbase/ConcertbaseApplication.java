package concertbase;

import concertbase.service.ConcertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = { "concertbase.persistence" })
@EntityScan(basePackages = { "concertbase.model" })
public class ConcertbaseApplication {
	private static final Logger log = LoggerFactory.getLogger(ConcertbaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConcertbaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			ConcertService concertService
	) {
		return args -> {
			concertService.findByCriteria("Bloodbath", null, null, null, null, null);

		};
	}
}
