package com.exist.service;

import com.exist.model.Role;
import com.exist.dao.RoleDao;
import com.exist.dto.RoleDto;
import java.util.List;
import java.util.ArrayList;

public class RoleService{
	private RoleDao roleDao;
	private List<RoleDto> roles = new ArrayList<>();

	public RoleService(RoleDao roleDao){
		this.roleDao = roleDao;
	}
	
	public RoleDto getRole(int roleId){
		return roleDao.getRoleById(roleId).toDto();
	}
	
	public List<RoleDto> getRoles(){
		roles = new ArrayList<>();
		for(Role role: roleDao.getRoles()){
			roles.add(role.toDto());
		}
		return roles;
	}
	
	private List<RoleDto> getRoleDto(){
		return new ArrayList<>(roles);
	}
	
	public void addRole(String roleName){
		Role role = new Role(roleName);
		roleDao.addRole(role);
	}
	
	public void updateRole(RoleDto role){
		roleDao.updateRole(convertToEntity(role));
	}
	
	public void deleteRole(RoleDto role){
		roleDao.deleteRole(convertToEntity(role));
	}
	
	private Role convertToEntity(RoleDto role){
		return new Role(role.getId(), role.getRoleName());
	}
	
	private boolean isRoleNameExist(String roleName){
		for (RoleDto role : roles){
			if(role.getRoleName().toLowerCase().equals(roleName.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}
