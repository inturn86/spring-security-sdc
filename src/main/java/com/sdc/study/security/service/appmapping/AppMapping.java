package com.sdc.study.security.service.appmapping;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "sys_app_mapping")
@Getter
@Setter
public class AppMapping {

	@Id
	protected String appMappingId;

	protected String appId;

	protected String role;
}
