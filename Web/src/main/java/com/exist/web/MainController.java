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
import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"main","/"})
public class MainController{

	private PersonService personService;
	private static final String MAIN_PAGE = "main";
		
	@Autowired
	public MainController(PersonService personService){
		this.personService = personService;
	}
	/*@RequestMapping({"/","/home"})
	public String showHomePage(Model model){
		model.addAttribute("persons", personService.getPersons(""));
		return MAIN_PAGE;
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showHomePage(){
		return new ModelAndView(MAIN_PAGE, "persons", personService.getPersons(""));
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
		return MAIN_PAGE;
	}

	@RequestMapping(params="filterBy=gwa", method=RequestMethod.GET)
	public String listPersonsByGwa(@RequestParam("gwa") String value, Model model){
		double gwa = -1;
		if(!value.isEmpty()){
			gwa = Double.parseDouble(value);
		}
		model.addAttribute("persons", personService.getPersonsByGwa(gwa));
		return MAIN_PAGE;
	}

	@RequestMapping(params="filterBy=lastName", method=RequestMethod.GET)
	public String listPersonsByLastName(@RequestParam("lastName") String value, Model model){
		model.addAttribute("persons", personService.getPersonsByLastName(value));
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
		return MAIN_PAGE;
	}
}