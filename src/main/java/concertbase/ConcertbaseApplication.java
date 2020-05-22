package concertbase;

import concertbase.model.Genre;
import concertbase.persistence.GenreRepository;
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

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Gówno");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for(String beanName: beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}

	@Bean
	public CommandLineRunner demo(GenreRepository repository) {
		return args -> {
//			repository.save(new Genre("Black metal"));
//			repository.save(new Genre("Death metal"));
//			repository.save(new Genre("Melodic death metal"));
//			repository.save(new Genre("Vegetarian progressive grindcore"));

			log.info("Genres found using findAll():");
			log.info("-------------------------------");
			for (Genre genre : repository.findAll()) {
				log.info(genre.getName());
			}
			System.out.println(repository.findByName("Black metal").get(0).getName());
		};
	}
}
