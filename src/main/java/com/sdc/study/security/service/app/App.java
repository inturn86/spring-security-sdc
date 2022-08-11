package com.sdc.study.security.service.app;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "sys_app")
@Getter
@Setter
public class App {

	@Id
	protected String appId;

	protected String appName;
}
