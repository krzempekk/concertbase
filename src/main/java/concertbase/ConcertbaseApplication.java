package concertbase;

import concertbase.model.Artist;
import concertbase.model.Concert;
import concertbase.model.LiveConcert;
import concertbase.model.Performance;
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

import java.text.SimpleDateFormat;
import java.util.List;


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
			PerformanceRepository performanceRepository
	) {
		return args -> {
			genreService.addGenre("Metal");
			genreService.addSubgenre("Doom metal", "Metal");
			genreService.addSubgenre("Gothic metal", "Metal");
			genreService.addSubgenre("Stoner metal", "Metal");

			artistService.addArtist("Candlemass", "Doom metal");
			artistService.addArtist("Paradise Lost", new String[] {"Gothic metal", "Doom metal"});
			artistService.addArtist("Elephant Tree", "Stoner metal");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			LiveConcert concert = new LiveConcert("Blood Fire Death", formatter.parse("02-04-2005"), "knockout.jp2", null);
			concertRepository.save(concert);

			artistService.addPerformance("Candlemass", "Blood Fire Death");
			artistService.addPerformance("Elephant Tree", "Blood Fire Death");



//			log.info("Searching for doom metal artists...");
//			for(Artist artist: artistRepository.findAllBySubgenres_Name("Doom metal")) {
//				log.info(artist.getName());
//			}

//			concertService.findConcertByGenre("Gothic metal");
			Artist searchedArtist = artistRepository.findByName("Paradise Lost");
			List<Performance> performances = performanceRepository.findByArtist(searchedArtist);
			if(performances!= null){
				System.out.println("Performances:");
				performances.forEach(System.out::println);
			}
			System.out.println("Concerts with artist " + searchedArtist.getName());
			List<Concert> foundconcerts = concertService.findByLiveByCriteria(searchedArtist, null, null, null, null);
			if(foundconcerts!=null)
				foundconcerts.forEach(System.out::println);

//			Subgenre subgenre = new Subgenre("Doom metal");
//			artistRepository.findAllBySubgenresContains_Name(subgenre);

//			genreService.addSubgenre("Speed metal", "Heavy metal");

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
