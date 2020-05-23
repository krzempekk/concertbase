package concertbase.presentation;

import concertbase.model.Concert;
import concertbase.model.LiveConcert;
import concertbase.service.ConcertService;
import concertbase.service.VenueService;
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

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping("/concerts")
    public String concerts(
//        Model model
    ) {
//        String[] genres = {"Heavy metal", "Thrash metal", "Black metal"};
//        model.addAttribute("genres", genres);
//        model.addAttribute("genre", new Genre());

        return "concerts";
    }

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
    ) {
        System.out.println(concertForm.getArtistName());
        if(bindingResult.hasErrors()) {
            return "concert-add";
        }

        return "concerts";
    }

    @PostMapping("/concerts")
    public String findConcerts(
        @RequestBody MultiValueMap<String, String> formData,
        Model model
    ) {
        String artistName, subgenreName, city, dateFrom, dateTo;

        artistName = formData.getFirst("artist");
        subgenreName = formData.getFirst("subgenre");
        city = formData.getFirst("city");
        dateFrom = formData.getFirst("dateFrom");
        dateTo = formData.getFirst("dateTo");

//        List<Concert> concerts = concertService.findByLiveByCriteria(artistName, subgenreName, city, dateFrom, dateTo);
//        model.addAttribute("concerts", concerts);

        return "concerts";
    }


    @GetMapping("/")
    public String indexPage(
        VerySimpleSearchForm searchForm,
        Model model
    ){
        List<Concert> results = new ArrayList<>();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);
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

        Concert foundConcert = this.concertService.findByName(searchForm.getSearchString());
        if (foundConcert == null){
            return "index";
        }
        results.add(foundConcert);

        Concert temp_mockup = new LiveConcert();
        temp_mockup.setName(searchForm.getSearchString());
        results.add(temp_mockup);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);

        return "index";
    }

}