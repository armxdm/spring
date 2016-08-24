package com.exist.model;

import com.exist.dto.RoleDto;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import static javax.persistence.GenerationType.AUTO;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(region="Role", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="role")
public class Role {
    
	
	@Id
    @Column(name="id", nullable=false)
	@SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq")
	@GeneratedValue(strategy = AUTO, generator = "role_id_seq")
    private int id;
	
	@Column(name="role_name")
    private String roleName;
	
	public Role(){
	}
	
	public Role(int id, String roleName){
		this.id = id;
		this.roleName = roleName;
	}

	public Role(String roleName){
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
	
	public RoleDto toDto(){
		return new RoleDto(this.id, this.roleName);
	}
	
	@Override
	public String toString(){
		return roleName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		Role obj2 = (Role)obj;
		if((this.id == obj2.getId()) && (this.roleName.equals(obj2.getRoleName()))){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int tmp = 0;
		tmp = ( id + roleName ).hashCode();
		return tmp;
	}
}
