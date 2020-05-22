package concertbase;

import concertbase.model.Genre;
import concertbase.model.Subgenre;
import concertbase.persistence.GenreRepository;
import concertbase.persistence.SubgenreRespository;
import concertbase.service.GenreService;
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
	public CommandLineRunner demo(GenreService genreService) {
		return args -> {

			genreService.addSubgenre("Speed metal", "Heavy metal");

//			genreRepository.save(new Genre("Black metal"));
//			repository.save(new Genre("Death metal"));
//			repository.save(new Genre("Melodic death metal"));
//			repository.save(new Genre("Vegetarian progressive grindcore"));
//
//			log.info("Genres found using findAll():");
//			log.info("-------------------------------");
//			Genre genreFound = genreRepository.findByName("Black metal").get(0);
//			System.out.println(genreFound.getName());
//
//			Subgenre subgenre = new Subgenre("Post");
//			repository.save(subgenre);
//
//			subgenre.setGenre(genreFound);
//			for (Genre genre : genreRepository.findAll()) {
//				System.out.println(genre.getName());
//			}

//			System.out.println(genreRepository.findByName("Black metal").get(0).getName());
		};
	}
}
