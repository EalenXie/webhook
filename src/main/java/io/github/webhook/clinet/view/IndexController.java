package io.github.webhook.clinet.view;

import io.github.webhook.clinet.view.vo.Pipeline;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String welcome(Model model, Authentication authentication) {
        List<Pipeline> list = new ArrayList<>();
        list.add(new Pipeline("https://gitlab.xxx.com/project-a", "SUCCESS"));
        list.add(new Pipeline("https://gitlab.xxx.com/project-b", "RUNNING"));
        list.add(new Pipeline("https://gitlab.xxx.com/project-c", "FAILED"));
        model.addAttribute("pipelines", list);
        // 返回欢迎页面
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }




}
