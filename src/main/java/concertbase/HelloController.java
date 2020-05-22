package concertbase;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

//    @RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }

    @GetMapping("/concerts")
    public String concerts(
            @RequestParam(name = "band", required = false) String band,
            Model model
            ) {
        model.addAttribute("band", band);
        return "concerts";
    }

}