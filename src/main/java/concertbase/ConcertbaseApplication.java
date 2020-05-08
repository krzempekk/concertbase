package concertbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ConcertbaseApplication {
	private static final Logger log = LoggerFactory.getLogger(ConcertbaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConcertbaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(GenreRepository repository) {
		return (args) -> {
			repository.save(new Genre("Black metal"));
			repository.save(new Genre("Death metal"));
			repository.save(new Genre("Melodic death metal"));
			repository.save(new Genre("Vegetarian progressive grindcore"));

			log.info("Genres found using findAll():");
			log.info("-------------------------------");
			for (Genre genre : repository.findAll()) {
				log.info(genre.getName());
			}
		};
	}
}
