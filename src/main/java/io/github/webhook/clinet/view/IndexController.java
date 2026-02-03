package io.github.webhook.clinet.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }


}
