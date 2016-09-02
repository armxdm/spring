package com.exist.web.controllers;

import com.exist.dto.PersonDto;
import com.exist.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping({"/main", "/"})
public class MainController {

    private static final String MAIN_PAGE = "main";
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Model model) {
        model.addAttribute("persons", personService.getPersons(""));
        //System.out.println(isAdmin());
        model.addAttribute("isAdmin", isAdmin());
        return MAIN_PAGE;
    }

    @RequestMapping(params = "filterBy=dateHired", method = RequestMethod.GET)
    public String listPersonsByDateHired(@RequestParam("dateHired") String value, Model model) {
        //String value = request.getParameter("dateHired");
        Date dateHired = null;
        if (!value.isEmpty()) {
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            try {
                dateHired = format.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("persons", personService.getPersonsByDateHired(dateHired));
        model.addAttribute("isAdmin", isAdmin());
        return MAIN_PAGE;
    }

    @RequestMapping(params = "filterBy=gwa", method = RequestMethod.GET)
    public String listPersonsByGwa(@RequestParam("gwa") String value, Model model) {
        double gwa = -1;
        if (!value.isEmpty()) {
            gwa = Double.parseDouble(value);
        }
        model.addAttribute("persons", personService.getPersonsByGwa(gwa));
        model.addAttribute("isAdmin", isAdmin());
        return MAIN_PAGE;
    }

    @RequestMapping(params = "filterBy=lastName", method = RequestMethod.GET)
    public String listPersonsByLastName(@RequestParam("lastName") String value, Model model) {
        model.addAttribute("persons", personService.getPersonsByLastName(value));
        model.addAttribute("isAdmin", isAdmin());
        return MAIN_PAGE;
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        PersonDto person = personService.getPersonById(id);
        if (person != null) {
            personService.deletePerson(person);
            model.addAttribute("deleteSuccessful", "Person is successfully deleted.");
        }
        model.addAttribute("persons", personService.getPersons(""));
        model.addAttribute("isAdmin", isAdmin());
        return MAIN_PAGE;
    }

    private boolean isAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

}