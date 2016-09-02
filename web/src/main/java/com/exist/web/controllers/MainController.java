package com.exist.web.controllers;

import com.exist.service.PersonService;
import com.exist.dto.PersonDto;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
	
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/main","/"})
public class MainController{

	@Autowired
	private PersonService personService;
	
	private static final String MAIN_PAGE = "main";
	
	@RequestMapping(method=RequestMethod.GET)
	public String showHomePage(Model model){
		model.addAttribute("persons", personService.getPersons(""));
		//System.out.println(isAdmin());
		model.addAttribute("isAdmin", isAdmin());
		return MAIN_PAGE;
	}
	
	@RequestMapping(params="filterBy=dateHired", method=RequestMethod.GET)
	public String listPersonsByDateHired(@RequestParam("dateHired") String value, Model model){
		//String value = request.getParameter("dateHired");
		Date dateHired = null;
		if(!value.isEmpty()){
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			try{
				dateHired = format.parse(value);
			} catch(ParseException e){
				throw new RuntimeException(e);
			}
		}
		model.addAttribute("persons", personService.getPersonsByDateHired(dateHired));
		model.addAttribute("isAdmin", isAdmin());
		return MAIN_PAGE;
	}

	@RequestMapping(params="filterBy=gwa", method=RequestMethod.GET)
	public String listPersonsByGwa(@RequestParam("gwa") String value, Model model){
		double gwa = -1;
		if(!value.isEmpty()){
			gwa = Double.parseDouble(value);
		}
		model.addAttribute("persons", personService.getPersonsByGwa(gwa));
		model.addAttribute("isAdmin", isAdmin());
		return MAIN_PAGE;
	}

	@RequestMapping(params="filterBy=lastName", method=RequestMethod.GET)
	public String listPersonsByLastName(@RequestParam("lastName") String value, Model model){
		model.addAttribute("persons", personService.getPersonsByLastName(value));
		model.addAttribute("isAdmin", isAdmin());
		return MAIN_PAGE;
	}
	
	@RequestMapping(value="/delete")  
	public String delete(@RequestParam("id") int id, Model model) {
		PersonDto person = personService.getPersonById(id);
		if(person != null){
			personService.deletePerson(person);
			model.addAttribute("deleteSuccessful", "Person is successfully deleted.");
		}
		model.addAttribute("persons", personService.getPersons(""));
		model.addAttribute("isAdmin", isAdmin());
		return MAIN_PAGE;
	}
	
	private boolean isAdmin(){
		SecurityContext context = SecurityContextHolder.getContext();
        if (context == null){
            return false;
		}

        Authentication authentication = context.getAuthentication();
        if (authentication == null){
            return false;
		}

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
	}
	
}