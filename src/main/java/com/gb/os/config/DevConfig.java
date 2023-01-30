package com.gb.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gb.os.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbservice;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean
	public boolean instanciaDB() {

		if (ddl.equals("create")) {
			this.dbservice.instanciaDB();
		}
		
		return false;
		
	}
}
