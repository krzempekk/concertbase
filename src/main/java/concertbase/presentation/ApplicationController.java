package concertbase.presentation;

import concertbase.model.*;
import concertbase.service.ArtistService;
import concertbase.service.ConcertService;
import concertbase.service.VenueService;
import concertbase.util.ConcertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    ConcertService concertService;

    @Autowired
    VenueService venueService;

    @Autowired
    ArtistService artistService;


    @GetMapping("/")
    public String indexPage(
        VerySimpleSearchForm searchForm,
        Model model
    ){
        List<Concert> results = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        List<Concert> streamedConcerts = concertService.findByCriteria(null, null, null, null, null, ConcertType.STREAMED);
        model.addAttribute("streamedConcerts", streamedConcerts);
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

        List<Concert> streamedConcerts = concertService.findByCriteria(null, null, null, null, null, ConcertType.STREAMED);
        model.addAttribute("streamedConcerts", streamedConcerts);

        Concert foundConcert = this.concertService.findByName(searchForm.getSearchString());
        
        if (foundConcert == null) {
            errors.add(String.format("No results for: %s", searchForm.getSearchString() ));
            model.addAttribute("results", results);
            model.addAttribute("errors", errors);
            return "index";
        }

        results.add(foundConcert);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);
        model.addAttribute("errors", errors);

        return "index";
    }

    @GetMapping("/advancedSearch")
    public String advancedSearchPage(
            SearchForm searchForm,
            Model model
    ){
        List<Concert> results = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("results", results);
        model.addAttribute("errors", errors);
        return "advancedSearch";
    }

    @PostMapping("/advancedSearch")
    public String advancedSearchPagePost(
            SearchForm searchForm,
            BindingResult bindingResult,
            Model model
    )
    {
        if(bindingResult.hasErrors()) {
            System.out.println("Error: przy zaawansowanym wyszukiwaniu w advancedSearch.html");
        }

        List<String> errors = new ArrayList<>();


        List<Concert> results;
        try {
            results = concertService.findByCriteria(
                searchForm.getArtistName(),
                searchForm.getSubgenreName(),
                searchForm.getCity(),
                searchForm.getDateFrom(),
                searchForm.getDateTo(),
                ConcertType.ANY
            );
        }
        catch (Exception e){
            results = new ArrayList<>();
        }

        if(results.size() == 0) errors.add("No results");


        model.addAttribute("searchForm", searchForm);
        model.addAttribute("errors", errors);
        model.addAttribute("results", results);
        return "advancedSearch";
    }

    @GetMapping("/addConcert")
    public String addConcertPage(
            ConcertForm concertForm,
            Model model
    ){
        List<Concert> results = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        model.addAttribute("concertForm", concertForm);
        model.addAttribute("results", results);
        model.addAttribute("errors", errors);
        model.addAttribute("venues",  venueService.getAllVenues());
        return "addConcert";
    }


    @PostMapping("/addConcert")
    public String addConcertPagePost(
            @Valid ConcertForm concertForm,
            BindingResult bindingResult,
            Model model
    ) {
        List<String> errors = new ArrayList<>();
        List<Concert> results = new ArrayList<>();

        model.addAttribute("errors", errors);
        model.addAttribute("results", results);
        model.addAttribute("venues",  venueService.getAllVenues());
        model.addAttribute("concertForm", concertForm);

        if(bindingResult.hasErrors()) {
            System.out.println("Error: przy dodawaniu koncertu addConcert.html");

            return "addConcert";
        }


        Concert concert = null;



        try {
            if (concertForm.getWebsite().equals("")) {
                concert = concertService.addLiveConcert(
                        concertForm.getName(),
                        concertForm.getDate(),
                        concertForm.getOrganizerWebsite(),
                        concertForm.getVenueId()
                );

            } else {
                concert = concertService.addStreamedConcert(
                        concertForm.getName(),
                        concertForm.getDate(),
                        concertForm.getOrganizerWebsite(),
                        concertForm.getWebsite()
                );

            }
            artistService.addPerformance(
                    concertForm.getArtistName(),
                    concert,
                    concertForm.getStartTime(),
                    concertForm.getEndTime()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Concert addedConcert = this.concertService.findByName(concertForm.getName());
//        if (addedConcert == null) {
//            errors.add(String.format("Couldn't add: %s", concertForm.getName() ));
//            model.addAttribute("results", results);
//            model.addAttribute("errors", errors);
//            return "addConcert";
//        }

        results.add(concert);

//        model.addAttribute("errors", errors);
//        model.addAttribute("results", results);

        return "addConcert";
    }




}