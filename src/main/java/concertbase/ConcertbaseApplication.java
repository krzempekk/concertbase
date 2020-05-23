package concertbase;

import concertbase.model.*;
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
			PerformanceRepository performanceRepository,
            VenueRepository venueRepository
	) {
		return args -> {
			genreService.addGenre("Metal");
			genreService.addSubgenre("Doom metal", "Metal");
			genreService.addSubgenre("Orthodox black metal", "Metal");
			genreService.addSubgenre("Prog metal", "Metal");
			genreService.addSubgenre("Thrash metal", "Metal");
			genreService.addSubgenre("Speed metal", "Metal");

			artistService.addArtist("Katatonia", new String[] {"Doom metal", "Prog metal"});
			artistService.addArtist("Kreator", "Thrash metal");
			artistService.addArtist("Lamb of God", "Speed metal");
			artistService.addArtist("Batushka", "Orthodox black metal");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Venue venue = new Venue("Studio", "Krakow", "Polska", "Budryka", 2, "666-666" );

            venueRepository.save(venue);
			LiveConcert concert = new LiveConcert("Mystic Festival", formatter.parse("2020-06-10"), "knockout.jp2", venue);
			concertRepository.save(concert);
			concert = new LiveConcert("State of Unrest", formatter.parse("2020-04-02"), "knockout.jp2", null);
			concertRepository.save(concert);
			concert = new LiveConcert("Panihida Tour", formatter.parse("2020-03-13"), "knockout.jp2", null);
			concertRepository.save(concert);

			artistService.addPerformance("Katatonia", "Mystic Festival");
			artistService.addPerformance("Kreator", "State of Unrest");
			artistService.addPerformance("Lamb of God", "State of Unrest");
			artistService.addPerformance("Batushka", "Panihida Tour");


//			log.info("Searching for doom metal artists...");
//			for(Artist artist: artistRepository.findAllBySubgenres_Name("Doom metal")) {
//				log.info(artist.getName());
//			}

//			concertService.findConcertByGenre("Gothic metal");
			Artist searchedArtist = artistRepository.findByName("Lamb of God");
			List<Performance> performances = performanceRepository.findByArtist(searchedArtist);
			if(performances!= null) {
				System.out.println("Performances: ");
				performances.forEach(System.out::println);
			}

			List<Concert> foundconcerts = concertService.findByLiveByCriteria(searchedArtist, null, "Krakow", "2019-04-01", null);
			System.out.println("Concerts with artist " + searchedArtist.getName());
			if(foundconcerts != null) {
				foundconcerts.forEach(System.out::println);
			} else {
				System.out.println("No concerts found :(");
			}

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
