package com.eazybytes.EazySchoolApplication.controller;
//commit
import com.eazybytes.EazySchoolApplication.model.Person;
import com.eazybytes.EazySchoolApplication.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    //spring expression language
    //fetching property value directly from application properties file
    @Value("${eazyschool.pageSize}")
    private int defaultPageSize;

    @Value("${eazyschool.contact.successMsg}")
    private String message;

    //using environment class to use variable value witout using SPEL
    @Autowired
    Environment environment;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
       // throw new RuntimeException("It's been a bad day!!");
        if(null != person.getEazyClass() && null != person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessages();
       return "dashboard.html";
    }

    private void logMessages(){
        log.error(message +":"+ defaultPageSize);
        log.error("defaultPageSize value with Environment is : "+environment.getProperty("eazyschool.pageSize"));
        log.error("successMsg value with Environment is : "+environment.getProperty("eazyschool.contact.successMsg"));
        log.error("Java Home environment variable using Environment is : "+environment.getProperty("USERNAME"));
    }
}