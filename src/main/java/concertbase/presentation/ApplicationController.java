package concertbase.presentation;

import concertbase.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/concerts")
    public String concerts(
        Model model
    ) {
        String[] genres = {"Heavy metal", "Thrash metal", "Black metal"};
        model.addAttribute("genres", genres);
        model.addAttribute("genre", new Genre());

        return "concerts";
    }

    @PostMapping("/concerts")
    public String concertsSubmit(
        @ModelAttribute Genre genre
    ) {
        return "concerts";
    }


    @GetMapping("/test")
    public String test_base(

    ){
        return "base";
    }

}