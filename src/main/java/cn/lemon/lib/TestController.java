package cn.lemon.lib;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test.html")
    public String testIndex() {
        return "test/index.html";
    }
}
