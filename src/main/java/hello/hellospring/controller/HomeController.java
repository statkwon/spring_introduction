package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // "/"에 매핑된 메서드가 존재하면 해당 콘트롤러를 호출하고, 그렇지 않으면 static의 index.html이 보여진다.
    public String home() {
        return "home";
    }
}
