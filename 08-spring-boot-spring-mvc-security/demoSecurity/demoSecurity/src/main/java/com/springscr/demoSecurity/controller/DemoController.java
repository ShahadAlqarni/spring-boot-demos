package com.springscr.demoSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // Add a request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeaders() {
        return "/leaders";
    }

    // Add a request mapping for /systems
    @GetMapping("/systems")
    public String showSystems() {
        return "/systems";
    }
}
