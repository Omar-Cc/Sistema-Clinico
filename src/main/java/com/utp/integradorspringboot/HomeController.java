package com.utp.integradorspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping({"/", "/logout"})
    public String index() {
        return "home";
    }
}
