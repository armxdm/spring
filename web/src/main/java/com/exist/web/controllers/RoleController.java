package com.exist.web.controllers;

import com.exist.dto.RoleDto;
import com.exist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("role")
public class RoleController {

    private static final String ROLE_PAGE = "role";
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRolePage(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return ROLE_PAGE;
    }

    @RequestMapping(params = "action=edit")
    public String edit(@RequestParam("id") int id, Model model) {
        RoleDto role = roleService.getRole(id);
        model.addAttribute("role", role);
        return showRolePage(model);
    }

    @RequestMapping(params = {"action=edit", "submit=true"})
    public String editSave(@ModelAttribute("role") RoleDto role, Model model) {
        roleService.updateRole(role);
        model.addAttribute("editSuccess", "Role is edited successfully.");
        return showRolePage(model);
    }

    @RequestMapping(params = "action=add")
    public String add(@RequestParam("roleName") String roleName, Model model) {
        roleService.addRole(roleName);
        model.addAttribute("addSuccess", "Role is added successfully.");
        return showRolePage(model);
    }

    @RequestMapping(params = "action=delete")
    public String delete(@RequestParam("id") int id, Model model) {
        RoleDto role = roleService.getRole(id);
        roleService.deleteRole(role);
        model.addAttribute("deleteSuccess", "Role is deleted successfully.");
        return showRolePage(model);
    }
}