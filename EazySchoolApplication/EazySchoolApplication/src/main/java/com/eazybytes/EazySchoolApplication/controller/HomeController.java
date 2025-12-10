package com.eazybytes.EazySchoolApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //@RequestMapping("/home")
    //Multipath Mapping
    @RequestMapping(value={"","/","home"})
    public String displayHomePage(){
        return "home.html";
    }

}
