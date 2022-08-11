package com.sdc.study.security.service.role;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sys_role")
public class Role {

	@Id
	protected String role;

	protected String roleName;
}
