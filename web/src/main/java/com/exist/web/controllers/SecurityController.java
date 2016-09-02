package com.exist.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exist.service.UserService;
import com.exist.model.User;
import com.exist.dto.UserRoleDto;
import com.exist.dto.UserDto;

@Controller
public class SecurityController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
    		return "forward:/main";
		}
		return "login";
	}
	
	@RequestMapping("/denied")
	public String denied() {
	    return "denied";
	}
	
	@RequestMapping("/create_account")
	public String createAccount(){
		return "create_account";
	}
	
	@RequestMapping(value="/create_account/submit", method=RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request) throws ServletException{
		ModelAndView model = new ModelAndView("create_account");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		System.out.println(role);
		UserRoleDto userRole = UserRoleDto.USER;
		
		if("ADMIN".equals(role)){
			userRole = UserRoleDto.ADMIN;	
		}
		
		UserDto user = new UserDto(username, password, userRole);
		userService.add(user);
		model.addObject("success", "User is added successfully.");
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth != null){    
        	new SecurityContextLogoutHandler().logout(request, response, auth);
   	 	}
    	return "redirect:/login?logout";
	}

}