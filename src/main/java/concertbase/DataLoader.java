package concertbase;

import concertbase.model.Concert;
import concertbase.service.ArtistService;
import concertbase.service.ConcertService;
import concertbase.service.GenreService;
import concertbase.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    VenueService venueService;

    @Autowired
    GenreService genreService;

    @Autowired
    ArtistService artistService;

    @Autowired
    ConcertService concertService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        venueService.addVenue("Studio","Kraków","Witolda Budryka", 4, "30-072");
        venueService.addVenue("Zaścianek","Kraków","Stanisława Skarżyńskiego", 1, "31-866");
        venueService.addVenue("Progresja","Warszawa","Fort Wola", 22, "01-258");
        venueService.addVenue("Spodek","Katowice","aleja Korfantego", 35, "40-005");


        genreService.addGenre("metal");
        genreService.addGenre("rock");
        genreService.addGenre("jazz");
        genreService.addGenre("pop");


        genreService.addSubgenre("death metal", "metal");
        genreService.addSubgenre("thrash metal", "metal");
        genreService.addSubgenre("black metal", "metal");
        genreService.addSubgenre("groove metal", "metal");
        genreService.addSubgenre("progressive rock", "rock");
        genreService.addSubgenre("acoustic rock", "rock");
        genreService.addSubgenre("acoustic pop", "pop");


        artistService.addArtist("Lamb of God", "groove metal");
        artistService.addArtist("Kreator", "thrash metal");
        artistService.addArtist("Batushka", "black metal");
        artistService.addArtist("Bloodbath", "death metal");
        artistService.addArtist("Ayreon", "progressive rock");
        artistService.addArtist("Lucy Rose", "acoustic pop");


        Concert concert1 = concertService.addLiveConcert("State of unrest tour", "2021-04-02", "https://www.knockoutprod.net/", "Studio", "Kraków");
        Concert concert2 =  concertService.addLiveConcert("Ayreon Universe", "2021-01-20", "https://www.knockoutprod.net/", "Progresja", "Warszawa");
        Concert concert3 = concertService.addLiveConcert("Bloodbath world tour", "2021-03-03", "https://www.knockoutprod.net/", "Spodek", "Katowice");


        Concert concert4 = concertService.addStreamedConcert("Lucy Rose stream", "2020-07-03", "https://www.knockoutprod.net/", "https://www.facebook.com/");
        Concert concert5 = concertService.addStreamedConcert("Panihida live", "2020-06-06", "https://www.knockoutprod.net/", "https://www.facebook.com/");


        artistService.addPerformance("Kreator", concert1.getId());
        artistService.addPerformance("Lamb of God", concert1.getId());
        artistService.addPerformance("Ayreon", concert2.getId());
        artistService.addPerformance("Bloodbath", concert3.getId());

        artistService.addPerformance("Lucy Rose", concert4.getId());
        artistService.addPerformance("Batushka", concert5.getId());

    }
}
