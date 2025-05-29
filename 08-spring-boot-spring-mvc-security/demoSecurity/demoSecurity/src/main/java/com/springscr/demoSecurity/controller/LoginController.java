package com.springscr.demoSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("shownMyLoginPage")
    public String shownMyLoginPage() {
        return "fancy";
    }

    //we put the code of access-denied here because this code is more security-related
    //add request mapping for /access-denied
    @GetMapping("access-denied")
    public String shownAccessDenied() {
        return "access-denied";
    }
}