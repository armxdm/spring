package com.exist.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

    private int id;
    private String roleName;

    public RoleDto() {
    }

    public RoleDto(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        RoleDto obj2 = (RoleDto) obj;
        return (this.id == obj2.getId()) && (this.roleName.equals(obj2.getRoleName()));
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        tmp = (id + roleName).hashCode();
        return tmp;
    }
}
