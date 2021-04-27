package com.riccardonoviello.demoapi;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Slf4j
@EnableJpaRepositories("com.riccardonoviello.demoapi.repository")
@EntityScan("com.riccardonoviello.demoapi.entity")
@ComponentScan({"com.riccardonoviello.demoapi"})
@SpringBootApplication
public class DemoApiApplication {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

	@PostConstruct
	public void validateDatabaseSchema() {
		final Flyway flyway = Flyway.configure().dataSource(dataSource).load();
		flyway.validate();
		log.info("Validated database schema with Flyway");
	}
}
