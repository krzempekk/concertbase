package concertbase.presentation;

import concertbase.model.Concert;
import concertbase.model.LiveConcert;
import concertbase.model.StreamedConcert;
import concertbase.model.Performance;
import concertbase.service.ArtistService;
import concertbase.service.ConcertService;
import concertbase.service.VenueService;
import concertbase.util.ConcertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {


//    @RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }

//    @GetMapping("/concerts")
//    public String concerts(
//        @RequestParam(name = "band", required = false) String band,
//        Model model
//    ) {
//        model.addAttribute("band", band);
//        model.addAttribute("another", "some random string");
//        model.addAttribute("genre", new Genre());
//        return "concerts";
//    }

    @Autowired
    ConcertService concertService;

    @Autowired
    VenueService venueService;

    @Autowired
    ArtistService artistService;


    @GetMapping("/concerts/add")
    public String addConcertGet(
        ConcertForm concertForm,
        Model model
    ) {
        model.addAttribute("concertForm", concertForm);
        model.addAttribute("venues",  venueService.getAllVenues());
        return "concert-add";
    }

    @PostMapping("/concerts/add")
    public String addConcertPost(
        @Valid ConcertForm concertForm,
        BindingResult bindingResult
    ) throws ParseException, InvalidAttributeValueException {

        if(bindingResult.hasErrors()) {
            return "concert-add";
        }

        Concert concert = concertService.addLiveConcert(
            concertForm.getName(),
            concertForm.getDate(),
            concertForm.getOrganizerWebsite(),
            concertForm.getVenueId()
        );

        Performance performance = artistService.addPerformance(
            concertForm.getArtistName(),
            concert,
            concertForm.getStartTime(),
            concertForm.getEndTime()
        );

        return "concerts";
    }

    @GetMapping("/concerts/find")
    public String findConcertsGet(
        SearchForm searchForm,
        Model model
    ) {

        model.addAttribute("searchForm", searchForm);

        return "concerts";
    }

    @PostMapping("/concerts/find")
    public String findConcertsPost(
        SearchForm searchForm,
        BindingResult bindingResult,
        Model model
    ) {

        if(bindingResult.hasErrors()) {
            return "concerts";
        }

        List<Concert> concerts = concertService.findByLiveByCriteria(
            searchForm.getArtistName(),
            searchForm.getSubgenreName(),
            searchForm.getCity(),
            searchForm.getDateFrom(),
            searchForm.getDateTo(),
            ConcertType.ANY
        );

        model.addAttribute("concerts", concerts);

        return "concerts";
    }


    @GetMapping("/")
    public String indexPage(
        VerySimpleSearchForm searchForm,
        Model model
    ){
        List<Concert> results = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);
        model.addAttribute("errors", errors);
        return "index";
    }

    @PostMapping("/")
    public String searchSubmit(
        @Valid @ModelAttribute("searchForm") VerySimpleSearchForm searchForm,
        BindingResult bindingResult,
        Model model
    ){
        if(bindingResult.hasErrors()) {
            System.out.println("Error: przy wyszukiwaniu prostej formy w index.html");
        }

        List<Concert> results = new ArrayList<>();
        List<String> errors = new ArrayList<>();
/*
        Concert foundConcert = this.concertService.findByName(searchForm.getSearchString());
        if (foundConcert == null){
            errors.add(String.format("Brak wyników dla zapytania: %s", searchForm.getSearchString() ));
            model.addAttribute("results", results);
            model.addAttribute("errors", errors);
            return "index";
        }

        results.add(foundConcert);

*/
        Concert temp_mockup = new StreamedConcert("Dobra bimba u Andrzeja", new Date(432429834), "google.com", ConcertType.STREAMED, "https");
        results.add(temp_mockup);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);

        return "index";
    }

}