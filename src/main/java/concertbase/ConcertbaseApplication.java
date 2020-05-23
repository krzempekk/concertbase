package concertbase;

import concertbase.model.Venue;
import concertbase.persistence.*;
import concertbase.service.ArtistService;
import concertbase.service.ConcertService;
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
	public CommandLineRunner demo(
			GenreService genreService,
			ArtistService artistService,
			ConcertService concertService,
			ArtistRepository artistRepository,
			GenreRepository genreRepository,
			SubgenreRepository subgenreRepository,
			ConcertRepository concertRepository,
			PerformanceRepository performanceRepository,
            VenueRepository venueRepository
	) {
		return args -> {


			Venue venue = venueRepository.findByNameAndCity("Studio", "Kraków");
//			System.out.println(venue.getConcerts());
		};
	}
}
