package com.exist.web.controllers;

import com.exist.dto.AddressDto;
import com.exist.dto.ContactDto;
import com.exist.dto.PersonDto;
import com.exist.dto.RoleDto;
import com.exist.model.ContactType;
import com.exist.service.PersonService;
import com.exist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    private static final String PERSON_PAGE = "person";
    @Autowired
    public PersonService personService;
    @Autowired
    public RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("contactTypes", ContactType.values());
        return PERSON_PAGE;
    }

    @RequestMapping(params = "action=edit")
    public String edit(@RequestParam("id") int id, Model model) {
        PersonDto person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return showPage(model);
    }

    @RequestMapping(params = "action=submit", method = RequestMethod.POST)
    public ModelAndView submit(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView model = new ModelAndView(PERSON_PAGE);
        List<String> errors = new ArrayList<>();

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String suffix = request.getParameter("suffix");
        String title = request.getParameter("title");

        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date dateHired = null;
        String dateHiredStr = request.getParameter("dateHired");
        try {
            dateHired = format.parse(dateHiredStr);
        } catch (ParseException e) {
            errors.add("Invalid date format for Date Hired field.");
        }

        String birthdayStr = request.getParameter("birthday");
        Date birthday = null;
        try {
            birthday = format.parse(birthdayStr);
        } catch (ParseException e) {
            errors.add("Invalid date format for Birthday field.");
        }

        String gwaStr = request.getParameter("gwa");
        double gwa = 0;
        try {
            gwa = Double.parseDouble(gwaStr);
        } catch (Exception e) {
            errors.add("Invalid format for GWA field");
        }

        String currentlyEmployedStr = request.getParameter("currentlyEmployed");
        boolean currentlyEmployed = currentlyEmployedStr.equals("Yes");

        String homeAddressNumStr = request.getParameter("homeAddressNum");
        int homeAddressNum = 0;
        try {
            homeAddressNum = Integer.parseInt(homeAddressNumStr);
        } catch (Exception e) {
            errors.add("Invalid format for Home Address' house number field.");
        }
        String homeAddressStreet = request.getParameter("homeAddressStreet");
        String homeAddressBarangay = request.getParameter("homeAddressBarangay");
        String homeAddressCity = request.getParameter("homeAddressCity");
        String homeAddressZipCodeStr = request.getParameter("homeAddressZipCode");
        int homeAddressZipCode = 0;
        try {
            homeAddressZipCode = Integer.parseInt(homeAddressZipCodeStr);
        } catch (Exception e) {
            errors.add("Invalid format for Home Address' zip code field.");
        }
        AddressDto homeAddress = new AddressDto(homeAddressNum, homeAddressStreet, homeAddressBarangay, homeAddressCity, homeAddressZipCode);

        String workAddressNumStr = request.getParameter("workAddressNum");
        int workAddressNum = 0;
        try {
            workAddressNum = Integer.parseInt(workAddressNumStr);
        } catch (Exception e) {
            errors.add("Invalid format for Work Address' bldg. number field.");
        }
        String workAddressStreet = request.getParameter("workAddressStreet");
        String workAddressBarangay = request.getParameter("workAddressBarangay");
        String workAddressCity = request.getParameter("workAddressCity");
        String workAddressZipCodeStr = request.getParameter("workAddressZipCode");
        int workAddressZipCode = 0;
        try {
            workAddressZipCode = Integer.parseInt(workAddressZipCodeStr);
        } catch (Exception e) {
            errors.add("Invalid format for Work Address' zip code field.");
        }
        AddressDto workAddress = new AddressDto(workAddressNum, workAddressStreet, workAddressBarangay, workAddressCity, workAddressZipCode);

        String[] roleIds = request.getParameterValues("roleChecked");
        Set<RoleDto> roles = new HashSet<>();
        if (roleIds != null) {
            for (String roleId : roleIds) {
                int i = Integer.parseInt(roleId);
                RoleDto role = roleService.getRole(i);
                roles.add(role);
            }
        }

        if (!errors.isEmpty()) {
            model.addObject("errors", errors);
            model.addObject("roles", roleService.getRoles());
            model.addObject("contactTypes", ContactType.values());
            return model;
        }

        PersonDto person = new PersonDto(firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress,
                homeAddress, currentlyEmployed, roles);

        String[] contactId = request.getParameterValues("contactId");
        String[] contactInfo = request.getParameterValues("contactInfo");
        String[] contactType = request.getParameterValues("contactType");

        if (contactId != null && contactInfo != null && contactType != null) {
            for (int i = 0; i < contactId.length; i++) {
                int id = Integer.parseInt(contactId[i]);
                if (id == -1) {
                    ContactDto contact = new ContactDto(contactType[i], contactInfo[i], person);
                    person.getContacts().add(contact);
                } else {
                    ContactDto contact = personService.getContactById(id);
                    person.getContacts().add(contact);
                }
            }
        }

        String edit = request.getParameter("edit");
        if ("true".equalsIgnoreCase(edit)) {
            int id = Integer.parseInt(request.getParameter("id"));
            person.setId(id);
            personService.updatePerson(person);
            model.addObject("success", "Person is edited successfully.");
        } else {
            personService.addPerson(person);
            model.addObject("success", "Person is added successfully.");
        }
        model.addObject("roles", roleService.getRoles());
        model.addObject("contactTypes", ContactType.values());
        return model;
    }


}