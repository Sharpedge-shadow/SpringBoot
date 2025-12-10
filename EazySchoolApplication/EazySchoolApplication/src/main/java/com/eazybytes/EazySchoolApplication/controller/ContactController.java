package com.eazybytes.EazySchoolApplication.controller;

import com.eazybytes.EazySchoolApplication.model.Contact;
import com.eazybytes.EazySchoolApplication.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    /*@PostMapping(value = "/saveMsg") this also work in place of request mapping
    @RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(@RequestParam(name="name") String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/contact");
    }*/

    @RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(Contact contact){
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }



}